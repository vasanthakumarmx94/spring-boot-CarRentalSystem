package com.neosoft.service;

import java.util.List;

import com.neosoft.model.CarBean;

public interface CarService {
	
	
	List<CarBean> getAllCars();

	CarBean saveCars(CarBean carBean);

	CarBean getCarById(Long id);

	CarBean updateCar(CarBean carBean);

	void deleteCarById(Long id);

	
	

}
