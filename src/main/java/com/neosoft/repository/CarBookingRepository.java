package com.neosoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neosoft.model.CarBookingBean;
import com.neosoft.model.UserBean;


public interface CarBookingRepository extends JpaRepository<CarBookingBean, Long>{
	
	
	@Query("SELECT u FROM CarBookingBean u WHERE u.userbean = ?1")
	public List<CarBookingBean> findByUserbean(UserBean user);
	
	
	
	
	

}
