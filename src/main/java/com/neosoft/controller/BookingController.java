package com.neosoft.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.neosoft.model.CarBean;
import com.neosoft.model.CarBookingBean;
import com.neosoft.model.UserBean;
import com.neosoft.service.BookingServiceImp;
import com.neosoft.service.CarServiceImp;
import com.neosoft.service.UserServiceImp;

@Controller
public class BookingController {

	@Autowired
	BookingServiceImp bookingServiceImp;
	
	@Autowired
	CarServiceImp carServiceImp;
	@Autowired
	UserServiceImp userServiceImp;
	
	@GetMapping("/admin/bookings")
	public String listBookings(Model model) {
		model.addAttribute("bookings",bookingServiceImp.getAllBookings());
		return "bookings";
	}
	
	@GetMapping("/mybookings")
	public String listmyBookings(Model model) {
		model.addAttribute("bookings",bookingServiceImp.getAllBookings());
		return "bookings";
	}
	
	
	
	
}
