package com.sap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAO {

	private static final Logger logger = Logger.getLogger(UserDAO.class);

	private SessionFactory sessionFactory;
	
	public UserDAO(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println(u);
		session.persist(u);
		logger.info("User saved successfully: " + u);
	}

	public User getUserByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, name);
		logger.info("User loaded successfully: " + u);

		return u;
	}
	
	public boolean checkCredentials(User user) {
		
		Session session = this.sessionFactory.getCurrentSession();

		User u = (User) session.get(User.class, user.getUsername());
		if (u != null && u.getPassword().equals(user.getPassword())){
			return true;
		}
		
		logger.info("User loaded successfully: " + u);

		return false;
	}
}
