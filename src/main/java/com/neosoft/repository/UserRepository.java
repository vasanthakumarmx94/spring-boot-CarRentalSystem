package com.neosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neosoft.model.UserBean;


public interface UserRepository extends JpaRepository<UserBean, Long>{

	@Query("SELECT u FROM UserBean u WHERE u.email = ?1")
	public UserBean findByEmail(String email);
	
	
	
	
	
}
