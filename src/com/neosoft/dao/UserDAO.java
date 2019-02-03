package com.neosoft.dao;

import java.util.List;

import com.neosoft.domain.User;

public interface UserDAO {

	public Boolean save(User user);
	
	public List<User> listUser();
	
	public User getUserByCredentials(String userName, String password);
}
