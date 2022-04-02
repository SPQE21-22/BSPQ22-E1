package controller;

import remote.ClientServiceLocator;
import data.dto.BookDTO;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class LibraryController {
    private final ClientServiceLocator serviceLocator;

    public LibraryController(ClientServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public List<BookDTO> getListedBooks() {
        try {
            return this.serviceLocator.getService().getListedBooks();
        } catch (RemoteException e) {
            System.out.println(" - Error: could not retrieve books. " + e);
            return null;
        }
    }

    public BookDTO getBookByName(String name) {
        try {
            return this.serviceLocator.getService().getBookByName(name);
        } catch (RemoteException e) {
            System.out.println(" - Error: could not retrieve book '" + name + "'. " + e);
            return null;
        }
    }

    public void addBook(String name, String author, Date publishDate, Boolean available) {
        try {
            this.serviceLocator.getService().addBook(name, author, publishDate, available);
        } catch (RemoteException e) {
            System.out.println(" - Error: could not add book. " + e);
        }
    }

    public void deleteBook(String name) {
        try {
            this.serviceLocator.getService().deleteBook(name);
        } catch (RemoteException e) {
            System.out.println(" - Error: could not delete book. " + e);
        }
    }

    public void updateBook(String name, String author, Date publishDate, Boolean available) {
        try {
            this.serviceLocator.getService().updateBook(name, author, publishDate, available);
        } catch (RemoteException e) {
            System.out.println(" - Error: could not delete book. " + e);
        }
    }

    public String getA() {
        try {
            return this.serviceLocator.getService().getA();
        } catch (RemoteException e) {
            System.out.println(" - Error: could not delete book. " + e);
            return "";
        }
    }

}