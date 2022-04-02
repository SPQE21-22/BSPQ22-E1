package server.data.dao;

import server.data.domain.Book;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DAOBase implements IDAO<Book> {
    private static BookDAO instance;
    private BookDAO() { }

    // Singleton
    public static BookDAO getInstance() {
        if (instance == null) {
            instance = new BookDAO();
        }

        return instance;
    }


    @Override
    public void save(Book book) {
        super.saveObject(book);
    }

    @Override
    public void delete(Book book) {
        super.deleteObject(book);
    }

    @Override
    public List<Book> getAll() {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        List<Book> books = new ArrayList<>();

        try {
            tx.begin();
            Extent<Book> extent = pm.getExtent(Book.class, true);

            for (Book book : extent) {
                books.add(book);
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println("- Error: could not retrieve all challenges: " + e.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return books;
    }

    @Override
    public Book find(String param) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        Book challenge = null;

        try {
            tx.begin();

            Query<?> query = pm.newQuery("SELECT FROM " + Book.class.getName() + " WHERE id == " + param);
            query.setUnique(true);
            challenge = (Book) query.execute();

            tx.commit();
        } catch (Exception e) {
            System.out.println("- Error: could not retrieve a challenge: " + e.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return challenge;
    }
}