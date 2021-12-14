package com.neosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.model.CarBean;



public interface CarRepository extends JpaRepository<CarBean, Long>{

}
