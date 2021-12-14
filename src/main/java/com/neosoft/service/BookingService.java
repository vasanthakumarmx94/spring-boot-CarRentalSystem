package com.neosoft.service;

import java.util.List;

import com.neosoft.model.CarBean;
import com.neosoft.model.CarBookingBean;
import com.neosoft.model.UserBean;



public interface BookingService {
	
	List<CarBookingBean> getAllBookings();
	CarBookingBean saveBookCars(CarBookingBean carbookBean);
	
	List<CarBookingBean> getByUserbean(UserBean user);
	
	CarBookingBean getCarBookingById(Long id);

	CarBookingBean updateCarbook(CarBookingBean carbookBean);
}
