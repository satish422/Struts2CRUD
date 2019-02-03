package com.neosoft.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.neosoft.domain.User;

public class UserDAOImpl implements UserDAO {
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	/**
	 * Used to save or update a user.
	 */
	@Override
	public Boolean save(User user) {
		
		Query query = session.createQuery("from User where userName=:name");
		query.setString("name", user.getUserName());
		User username = (User) query.uniqueResult();
		if(username != null){
			System.out.println("User Already exists::"+user);
			return false;
		}
		else {
			try {
				session.save(user);
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			}
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUser() {
		List<User> users = null;
		try {
			users = session.createQuery("from User").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public User getUserByCredentials(String userName, String password) {
		
		Query query = session.createQuery("from User where userName=:name and password=:pwd");
		query.setString("name", userName); query.setString("pwd", password);
		User user = (User) query.uniqueResult();
		if(user != null){
			System.out.println("User Retrieved from DB::"+user);
		}
		return user;
	}

}
