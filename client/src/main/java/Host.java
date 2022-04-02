import controller.LibraryController;
import controller.UserController;
import remote.ClientServiceLocator;

public class Host{

    public static void main(String[] args){
        String hostname = args[0];
        String port = args[1];
        String name = args[2];

        ClientServiceLocator serviceLocator = new ClientServiceLocator();
        serviceLocator.setService(args[0], args[1], args[2]);

        UserController userController = new UserController(serviceLocator);
        LibraryController libraryController = new LibraryController(serviceLocator);

        System.out.println(libraryController.getA());
        System.out.println("Hola soy el cliente");
    }
}
