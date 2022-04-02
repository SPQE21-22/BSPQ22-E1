package server.service;

import server.data.domain.Book;
import server.data.domain.User;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class LoginAppService {
    private static LoginAppService instance;

    public static LoginAppService getInstance() {
        if(instance == null) {
            instance = new LoginAppService();
        }
        return instance;
    }
    public User register(int id, String name, String email, String password, Date birthDate, List<Book> books) throws RemoteException {
        User user = new User(id, name, email, password, birthDate, books);
        System.out.println(" - Added new user: " + name + " - " + email);
        //UserDAO.getInstance().save(user);
        return user;
    }

    public User login(String email, String password){
        User user = new User(0,"Paco","paco@gmail.com","12345",null,null); //TODO sacar de la BD
        boolean check;

        if (user.getEmail() == email && user != null) {
            check = user.getPassword().equals(password);
        } else {
            check = false;
        }

        if (check) {
            System.out.println("Successful login attempt for '" + email + "'");
            return user;
        }
        else {
            System.out.println("failed login attempt for '" + email + "'");
            return null;
        }
    }

    public void logout(long token){

    }
    public void deleteUser(long token) throws RemoteException {

    }
}
