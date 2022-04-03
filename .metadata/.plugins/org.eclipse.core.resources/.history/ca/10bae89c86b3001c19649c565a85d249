package es.deusto.ingenieria.sd.facebook.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.facebook.server.data.domain.User;


//This class implements Singleton and DAO patterns
public class UserDAO extends DataAccessObjectBase implements IDataAccessObject<User> {

	private static UserDAO instance;	
	
	private UserDAO() { }
	
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}		
		
		return instance;
	}	
	
	@Override
	public void save(User object) {
		super.saveObject(object);
	}

	@Override
	public void delete(User object) {
		super.deleteObject(object);
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
		} catch (Exception ex) {
			System.out.println("  $ Error querying all users: " + ex.getMessage());
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
		} catch (Exception ex) {
			System.out.println("  $ Error querying a User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
}