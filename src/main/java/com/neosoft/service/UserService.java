package com.neosoft.service;

import java.util.List;

import com.neosoft.model.UserBean;

public interface UserService {

	List<UserBean> getAllUsers();

	UserBean saveUsers(UserBean userbean);

	UserBean getUserById(Long id);

	UserBean updateUser(UserBean userbean);

	void deleteUserById(Long id);
	
	UserBean getUserByEmail(String email);

}
