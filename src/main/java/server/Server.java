package server;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import server.data.domain.Book;
import server.data.domain.User;
import server.sql.DB;
import server.sql.DBException;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	private List<User> usersList;
	private List<Book> booksList;
	private Connection con;
	private DB db = new DB();

	public Server() throws SQLException {

		try {
			con = DB.initBD();
			DB.createTables(con);
			DB.addUser(con, "Alex", "a@mail", "1234",new Date(2022, 1, 10));
			DB.addUser(con, "Aida", "a@mail", "1234", new Date(2001, 2, 10));
			DB.addBook(con, "El nombre del Viento", "Path",  new Date(2006, 3, 15), true);
			usersList = DB.getUsersList(con);
			booksList = DB.getBooksList(con);
		} catch (DBException e) {
			e.printStackTrace();
		}
		for (User u:
				usersList) {
			System.out.println(u);
		}

		for (Book b:
				booksList) {
			System.out.println(b);
		}

		System.out.println("Servidor Iniciado");
//        listaUsuarios= new ArrayList<User>();
//        listaUsuarios.add(new User("Alex", "a@mail", "1234", new Date(), new ArrayList<Book>()));
//        listaUsuarios.add(new User("Ruben", "r@mail", "1234", new Date(), new ArrayList<Book>()));
//
//        listaLibros= new ArrayList<Book>();
//        listaLibros.add(new Book("El camino", "BD", new Date(), true));
//        listaLibros.add(new Book("El imperio", "BD", new Date(), true));
	};

	@POST
	@Path("/login")
	public Response login(String email) {
		User found = null;
		for (int i = 0; i < usersList.size(); i++) {
			if (usersList.get(i).getEmail().equalsIgnoreCase(email) ) {
				found = usersList.get(i);
			}
		}
		if (found == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("User not found").build();
		} else {
			System.out.println("Login correcto");
			return Response.ok(found).build();
		}
	}

	@POST
	@Path("/createUser")
	public Response createUser(User userRequest) throws DBException {
		DB.addUser(con, userRequest.getName(), userRequest.getEmail(), userRequest.getPassword(), new Date(userRequest.getBirthDate().getYear(), userRequest.getBirthDate().getYear(), userRequest.getBirthDate().getDay()));
		this.usersList.add(userRequest);
		System.out.println("Usuario creado correctamente");
		return Response.ok(userRequest).build();

	}

	@GET
	@Path("/books")
	public Response getBooks() {
		System.out.println("Devolviendo listado de libros");
		User admin = new User("","","",null,this.booksList);
		return Response.ok(admin).build();
	}

	@POST
	@Path("/addBook")
	public Response addBook(Book book) throws DBException {
		DB.addBook(con, book.getName(), book.getAuthor(),  new Date(book.getPublishDate().getYear(), book.getPublishDate().getMonth(), book.getPublishDate().getDay()), true);
		this.booksList.add(book);
		System.out.println("Libro añadido correctamente");
		return Response.ok(book).build();

	}

	@GET
	@Path("/response")
	public Response getResp() {
		String s = "Response";
		return Response.ok(s).build();
	}

}