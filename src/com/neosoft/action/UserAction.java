package com.neosoft.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.neosoft.dao.UserDAO;
import com.neosoft.dao.UserDAOImpl;
import com.neosoft.domain.User;

public class UserAction extends ActionSupport implements ModelDriven<User>,SessionAware {

	private static final long serialVersionUID = -6659925652584240539L;

	private User user = new User();
	private List<User> userList = new ArrayList<User>();
	private UserDAO userDAO = new UserDAOImpl();
	private SessionMap<String, Object> sessionMap;

	@Override
	public User getModel() {
		return user;
	}

	public String saveUser() {
		boolean status = userDAO.save(user);
		if(status) {
		return SUCCESS;}
		else {
			return ERROR;
		}
	}

	public String list() {
		HttpSession session = ServletActionContext.getRequest().getSession(true);
		String getSessionAttr = (String) session.getAttribute("userName");
		
		if (getSessionAttr != null) {
			userList = userDAO.listUser();
			return SUCCESS;

		} else {
			return ERROR;
		}
	}

	public String login() {
		user = userDAO.getUserByCredentials(user.getUserName(), user.getPassword());
		
		if (user == null) {
			return ERROR;
		} else {
			sessionMap.put("userName", user.getUserName());
			return SUCCESS;
		}
	}
	
	public String logout() {
		
		sessionMap.remove("userName");
		sessionMap.invalidate();
		
		return "logout";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap<String, Object>) map; 
		
	}

}