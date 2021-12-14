package com.neosoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.model.CarBookingBean;
import com.neosoft.model.UserBean;
import com.neosoft.repository.CarBookingRepository;

@Service
public class BookingServiceImp implements BookingService{

	@Autowired
	CarBookingRepository carBookingRepository;
	
	@Override
	public List<CarBookingBean> getAllBookings() {
		return carBookingRepository.findAll();
	}

	@Override
	public CarBookingBean saveBookCars(CarBookingBean carbookBean) {
		return carBookingRepository.save(carbookBean);
	}

	@Override
	public List<CarBookingBean> getByUserbean(UserBean user) {
		
		return carBookingRepository.findByUserbean(user);
	}

	@Override
	public CarBookingBean getCarBookingById(Long id) {
		return carBookingRepository.getById(id);
	}

	@Override
	public CarBookingBean updateCarbook(CarBookingBean carbookBean) {
		return carBookingRepository.save(carbookBean);
	}

	

	

}
