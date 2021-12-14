package com.neosoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.model.CarBean;
import com.neosoft.repository.CarRepository;

@Service
public class CarServiceImp implements CarService{

	@Autowired
	private CarRepository carRepository;
	
	
	@Override
	public List<CarBean> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public CarBean saveCars(CarBean carBean) {
		return carRepository.save(carBean);
	}

	@Override
	public CarBean getCarById(Long id) {
		return carRepository.getById(id);
	}

	@Override
	public CarBean updateCar(CarBean carBean) {
		return carRepository.save(carBean);
	}

	@Override
	public void deleteCarById(Long id) {
		carRepository.deleteById(id);
		
	}

}
