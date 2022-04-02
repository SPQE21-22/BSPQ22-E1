package controller;

import data.domain.Book;
import data.domain.User;
import remote.*;
import data.dto.UserDTO;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class UserController {
    private final ClientServiceLocator serviceLocator;
    private long token = -1;

    public UserController(ClientServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public boolean login(String email, String password) {
        try {
            this.token = this.serviceLocator.getService().login(email, password);
            return true;
        } catch (RemoteException e) {
            System.out.println("- Login error: " + e);
            this.token = -1;
            return false;
        }
    }

    public void logout() {
        try {
            this.serviceLocator.getService().logout(this.token);
            this.token = -1;
        } catch (RemoteException e) {
            System.out.println("- Logout error: " + e);
        }
    }

    public long register(String name, String email, String password, Date birthDate, List<Book> books) {
        try {
            this.token = this.serviceLocator.getService().register(name, email, password, birthDate, books);
            return token;
        } catch (RemoteException e) {
            System.out.println("- Register error: " + e);
            return -1;
        }
    }

    public void deleteUser(User user) {
        try {
            this.serviceLocator.getService().deleteUser(user);
        } catch (RemoteException e) {
            System.out.println("- User retrieve error: " + e);
        }
    }
}