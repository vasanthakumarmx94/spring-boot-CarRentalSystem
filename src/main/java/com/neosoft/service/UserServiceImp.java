package com.neosoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.model.UserBean;
import com.neosoft.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<UserBean> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserBean saveUsers(UserBean userbean) {
		return userRepository.save(userbean);
	}

	@Override
	public UserBean getUserById(Long userid) {
		return userRepository.getById(userid);
	}

	@Override
	public UserBean updateUser(UserBean userbean) {
		return userRepository.save(userbean);
	}

	@Override
	public void deleteUserById(Long userid) {
		userRepository.deleteById(userid);
	}

	@Override
	public UserBean getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	

}
