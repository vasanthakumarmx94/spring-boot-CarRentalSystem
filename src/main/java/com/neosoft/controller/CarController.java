package com.neosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.neosoft.model.CarBean;
import com.neosoft.service.CarServiceImp;

@Controller
public class CarController {

	@Autowired
	private CarServiceImp carServiceImp;

	
	
	

	

	

}
