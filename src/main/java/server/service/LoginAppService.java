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
        return user;
    }

    public User login(String email, String password){
        return null;
    }

    public void logout(long token){

    }
    public void deleteUser(long token) throws RemoteException {

    }
}
