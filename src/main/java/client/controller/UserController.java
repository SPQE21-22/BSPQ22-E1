package client.controller;

import client.remote.ClientServiceLocator;
import server.data.domain.Book;
import server.data.domain.User;
import server.remote.*;

import java.rmi.RemoteException;
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

    public void register(String name, String email, String password, Date birthDate, List<Book> books) {
        try {
            this.serviceLocator.getService().register(name, email, password, birthDate, books);
        } catch (RemoteException e) {
            System.out.println("- Register error: " + e);
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