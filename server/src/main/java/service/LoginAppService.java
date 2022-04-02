package service;

import data.dao.BookDAO;
import data.dao.UserDAO;
import data.domain.Book;
import data.domain.User;

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
    public User register(String name, String email, String password, Date birthDate, List<Book> books) throws RemoteException {
        User user = new User(name, email, password, birthDate, books);
        System.out.println(" - Added new user: " + name + " - " + email);
        UserDAO.getInstance().save(user);
        return user;
    }

    public User login(String email, String password){
        User user = UserDAO.getInstance().find(email);
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

    public void deleteUser(User user) throws RemoteException {
        UserDAO.getInstance().delete(user);
    }
}
