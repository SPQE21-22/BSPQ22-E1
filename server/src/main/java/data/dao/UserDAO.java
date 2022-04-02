package data.dao;

import data.domain.Book;
import data.domain.User;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAOBase implements IDAO<User> {
    private static UserDAO instance;
    private UserDAO() { }

    // Singleton
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }

        return instance;
    }

    @Override
    public void save(User user) {
        super.saveObject(user);
    }

    @Override
    public void delete(User user) {
        super.deleteObject(user);
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public List<User> getAll() {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        List<User> users = new ArrayList<>();

        try {
            tx.begin();

            Extent<User> userExtent = pm.getExtent(User.class, true);

            for (User user : userExtent) {
                users.add(user);
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println("- Error retrieving all users: " + e.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return users;
    }

    @Override
    public User find(String param) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        User result = null;

        try {
            tx.begin();

            Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '" + param + "'");
            query.setUnique(true);
            result = (User) query.execute();

            tx.commit();
        } catch (Exception e) {
            System.out.println("- Error retrieving a user: " + e.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return result;
    }
}