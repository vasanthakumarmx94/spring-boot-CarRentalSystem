package com.neosoft.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neosoft.model.CarBean;
import com.neosoft.model.CarBookingBean;
import com.neosoft.model.UserBean;
import com.neosoft.service.BookingServiceImp;
import com.neosoft.service.CarServiceImp;
import com.neosoft.service.UserServiceImp;

@Controller
public class UserController {
	
	
	@Autowired
	 UserServiceImp userServiceImp;
	@Autowired
	CarServiceImp carServiceImp;
	
	@Autowired
	BookingServiceImp bookingServiceImp;
	
	
	// user DashBoard setting session 
	// http://localhost:8080/userdashboard
		@RequestMapping(value = "/user/userdashboard", method = RequestMethod.GET)
		//@GetMapping("/user/userdashboard")
		public String userdashboard(HttpServletRequest request,Principal principal,Model model) {
			HttpSession session=request.getSession();
			
			String email=principal.getName();
			 // store data in session
		    session.setAttribute("userName", email);
			return "userdashboard";
		}
	

//		@GetMapping("/user/cars")
//		public String listCars(Model model,HttpSession session) {
//			
//			if (session.getAttribute("userName") == null){
//		        System.out.println("session is not exists");
//		        //
//		        return "redirect:/index";
//		    }
//			model.addAttribute("cars", carServiceImp.getAllCars());
//			return "cars";
//		}
	
		@GetMapping("/user/bookcars")
		public String bookcars(Model model,HttpSession session) {
			if (session.getAttribute("userName") == null){
		        System.out.println("session is not exists");
		        // how to redirect user
		        return "redirect:/index";
		    }
			
			model.addAttribute("cars", carServiceImp.getAllCars());
			return "bookcars";
		}
		
		
		// displaying mybooking details along with return now link if not returned
		@GetMapping("/user/mybookings")
		public String listmyBookings(Principal principal,Model model,HttpSession session) {
			if (session.getAttribute("userName") == null){
		        System.out.println("session is not exists");
		        // how to redirect user
		        return "redirect:/index";
		    }
			String email=principal.getName();
			UserBean user=userServiceImp.getUserByEmail(email);
			model.addAttribute("bookings",bookingServiceImp.getByUserbean(user));
			return "mybookings";        
		}
	
	
		// after click bookNow create a form an fill details to booking 
		@GetMapping("/user/booknow/new/{id}")
		public String createCarForm(@PathVariable Long id,Model model,HttpSession session) {
			if (session.getAttribute("userName") == null){
		        System.out.println("session is not exists");
		        // how to redirect user
		        return "redirect:/index";
		    }
			CarBean car = carServiceImp.getCarById(id);
			CarBookingBean carbook=new CarBookingBean();
			carbook.setCarbean(car);
			String email=(String) session.getAttribute("userName");
			UserBean user=userServiceImp.getUserByEmail(email);
			carbook.setUserbean(user);
			model.addAttribute("carbook", carbook);
			model.addAttribute("userbean",user);
			return "Create_Carbook";	
		}
		
		// after submit save carbooking details also update car table 
		@PostMapping("/user/bookcars")		
		public String saveCarbook(@ModelAttribute("carbook") CarBookingBean carbook) throws ParseException {
				Date fdate=carbook.getFdate();
				Date todate=carbook.getTdate();
				long diff = fdate.getTime() - todate.getTime();
				int days=(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				CarBean car = carServiceImp.getCarById(carbook.getCarbean().getCarid());
				double amt=car.getPriceperday();
				carbook.setAmount(Math.abs(days*amt));
				carbook.setReturnstatus("No");
				bookingServiceImp.saveBookCars(carbook);
				
				// updating car availability 
				car.setIssustatus("Not Available");
				carServiceImp.updateCar(car);	
				return "redirect:/user/bookcars";
		}
		
		//user return the car and updating here
		@GetMapping("/user/return/{id}")
		public String ReturnMyCar(@PathVariable Long id,HttpSession session) {
			if (session.getAttribute("userName") == null){
		        System.out.println("session is not exists");
		        // how to redirect user
		        return "redirect:/index";
		    }
			
			//update return status in carbooking 
			CarBookingBean carbook=bookingServiceImp.getCarBookingById(id);
			carbook.setReturnstatus("Yes");
			bookingServiceImp.updateCarbook(carbook);
			
			//update issuestatus in  car table to Available 
			CarBean car = carServiceImp.getCarById(carbook.getCarbean().getCarid());
			car.setIssustatus("Available");
			carServiceImp.updateCar(car);
			return "redirect:/user/mybookings";
		}
		
		@GetMapping("/user/myaccount")
		public String myaccountprofile(Principal principal,Model model,HttpSession session) {
			if (session.getAttribute("userName") == null){
		        System.out.println("session is not exists");
		        // how to redirect user
		        return "redirect:/index";
		    }
			
			String email=principal.getName();
			model.addAttribute("user",userServiceImp.getUserByEmail(email));
			return "myaccount";      
		}
		@GetMapping("/user/payslip/{id}")
		public String payslip(@PathVariable Long id,Model model,HttpSession session) {
			if (session.getAttribute("userName") == null){
		        System.out.println("session is not exists");
		        // how to redirect user
		        return "redirect:/index";
		    }
			
			
			model.addAttribute("bookingdetail",bookingServiceImp.getCarBookingById(id));
			return "viewpayslip";        
		}
		
		
	
	
}
