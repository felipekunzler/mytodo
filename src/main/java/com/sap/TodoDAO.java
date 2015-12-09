package com.sap;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TodoDAO {

	private static final Logger logger = Logger.getLogger(TodoDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addTodo(Todo t) {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println(t);
		session.persist(t);
		logger.info("Todo saved successfully: " + t);
	}

	public void updateTodo(Todo t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(t);
		logger.info("Todo updated successfully: " + t);
	}

	@SuppressWarnings("unchecked")
	public List<Todo> getTodoListByUser(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Todo> todoList = session.createQuery("from Todo where id_user = " + userId).list();
		logger.info("Todo List loaded.");

		return todoList;
	}

	public Todo getTodoById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Todo t = (Todo) session.load(Todo.class, new Integer(id));
		logger.info("Todo loaded successfully: " + t);

		return t;
	}

	public void removeTodo(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Todo t = (Todo) session.load(Todo.class, new Integer(id));
		if (null != t) {
			session.delete(t);
		}
		
		logger.info("Todo deleted successfully: " + t);
	}
}
