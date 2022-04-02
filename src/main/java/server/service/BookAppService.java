package server.service;

import server.data.dao.BookDAO;
import server.data.domain.Book;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class BookAppService {
    private static BookAppService instance;

    private static int bookID = 0;

    public static BookAppService getInstance() {
        if(instance == null) {
            instance = new BookAppService();
        }
        return instance;
    }

    public List<Book> getListedBooks(){
        List<Book> books = BookDAO.getInstance().getAll();
        books.removeIf(el -> !el.getAvailable());
        return books;
    }


    public Book getBookByName(String name){
        Book book = BookDAO.getInstance().find(name);
        return book;
    }


    public void addBook(String name, String author, Date publishDate, Boolean available){
        Book book = new Book(name,author,publishDate,available);
        BookDAO.getInstance().save(book);
    }


    public void deleteBook(String name){
        Book book = getBookByName(name);
        BookDAO.getInstance().delete(book);
    }
}
