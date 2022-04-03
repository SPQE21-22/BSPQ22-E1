package es.deusto.ingenieria.sd.google.server.services;

import es.deusto.ingenieria.sd.google.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.google.server.data.domain.User;

public class GoogleService {

	public boolean login(String mail, String password) {
		try{
			if(UserDAO.getInstance().find(mail)!=null) {
				if(UserDAO.getInstance().find(mail).checkPassword(password)) {
					return true;
				}
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}
	public void register(String mail, String password) {
		if(UserDAO.getInstance().find(mail)==null) {
			UserDAO.getInstance().save(new User(mail,password));
		}
	}
}