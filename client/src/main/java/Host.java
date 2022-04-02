import controller.LibraryController;
import controller.UserController;
import data.domain.Book;
import data.dto.BookDTO;
import remote.ClientServiceLocator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Host{

    public static void main(String[] args){
        String hostname = args[0];
        String port = args[1];
        String name = args[2];

        ClientServiceLocator serviceLocator = new ClientServiceLocator();
        serviceLocator.setService(args[0], args[1], args[2]);

        UserController userController = new UserController(serviceLocator);
        LibraryController libraryController = new LibraryController(serviceLocator);

        System.out.println("Hola soy el cliente");

        //COSAS DE LA PESTAÑA DE LOGIN
        String email = "paco@gmail.com";
        String contrasena = "12345";
        if (userController.login(email, contrasena)) {
            System.out.println("Pasar pestaña de listado libros");
        }

        //COSAS DE LA PESTAÑA DE REGISTER
        String nombre = "Paco";
        Date birthDate = new Date();
        List<Book> books = new ArrayList<Book>();
        userController.register(nombre,email,contrasena, birthDate,books);
        System.out.println("Pasar pestaña de listado libros");


        //COSAS DE LA PESTAÑA DE LISTADO
        List<BookDTO> availableBooks  = libraryController.getListedBooks();
        System.out.println("Hacer un listado de los titulos de los libros tipo enlace");
        System.out.println("Boton para añadir libro");
        String titulo = "El camino de los reyes";
        String autor = "Brandon Sanderson";
        Date publishDate = new Date();
        boolean disponible = true;
        libraryController.addBook(titulo, autor, publishDate, disponible);
        System.out.println("Al hacer click pasar a ventana de libro");

        //COSAS DE LA PESTAÑA DE LIBROS
        System.out.println("Boton para reservar");
        BookDTO book= libraryController.getBookByName(titulo);
        libraryController.updateBook(book.getName(), book.getAuthor(), book.getPublishDate(), false);
        System.out.println("Te devuelve a la pestaña de listado en la que ya no se ve este libro");
    }
}
