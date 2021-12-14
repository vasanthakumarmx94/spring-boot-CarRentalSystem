package com.neosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.neosoft.model.CarBean;
import com.neosoft.model.UserBean;
import com.neosoft.service.CarServiceImp;
import com.neosoft.service.UserServiceImp;

@Controller
public class AdminController {

	@Autowired
	private CarServiceImp carServiceImp;
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	// dashboard page
	// http://localhost:8080/admindashboard
		@GetMapping("/admin/admindashboard")
		public String admindashboard() {
			
			return "admindashboard";
		}
	
	// car listing all cars 
	@GetMapping("/admin/cars")
	public String listCars(Model model) {
		model.addAttribute("cars", carServiceImp.getAllCars());
		return "cars";
	}

	// add new car by by create_car 
	@GetMapping("/admin/cars/new")
	public String createCarForm(Model model) {
		CarBean car = new CarBean();
		model.addAttribute("car", car);
		return "Create_Car";
	}
	
	// save the car details after filling
	@PostMapping("/admin/cars")
	public String saveCar(@ModelAttribute("car") CarBean car) {
		carServiceImp.saveCars(car);
		return "redirect:/admin/cars";
	}

	// edit car in edit_car form
	@GetMapping("/admin/cars/edit/{id}")
	public String editCarForm(@PathVariable Long id, Model model) {
		model.addAttribute("car", carServiceImp.getCarById(id));
		return "edit_car";
	}

	// updating to that car details changed 
	@PostMapping("/admin/cars/{id}")
	public String UpdateCar(@PathVariable Long id, @ModelAttribute("car") CarBean car, Model model) {
		CarBean existingCar = carServiceImp.getCarById(id);
		existingCar.setCarid(id);
		existingCar.setCarname(car.getCarname());
		existingCar.setCarcompany(car.getCarcompany());
		existingCar.setCarcity(car.getCarcity());
		existingCar.setCarnumaber(car.getCarnumaber());
		existingCar.setCardescription(car.getCardescription());
		existingCar.setIssustatus(car.getIssustatus());
		existingCar.setPriceperday(car.getPriceperday());
		carServiceImp.updateCar(existingCar);
		return "redirect:/admin/cars";
	}
	
	@GetMapping("/admin/cars/{id}")
	public String deleteCar(@PathVariable Long id) {
		carServiceImp.deleteCarById(id);
		return "redirect:/admin/cars";
	}
	
	
// -----------------------------------------
	
	@GetMapping("/admin/users/{id}")
	public String deleteUser(@PathVariable Long id) {
		userServiceImp.deleteUserById(id);
		return "redirect:/admin/users";
	}	
	
	@GetMapping("/admin/users")
	public String listUsers(Model model) {
		model.addAttribute("users",userServiceImp.getAllUsers());
		return "users";
	}
	@GetMapping("/admin/users/new")
	public String createUserForm(Model model) {
		UserBean user = new UserBean();
		model.addAttribute("user", user);
		return "Create_User";
	}
	@PostMapping("/admin/save/users")
	public String saveUser(@ModelAttribute("user") UserBean user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userServiceImp.saveUsers(user);
		return "redirect:/admin/users";
	}
	
	@GetMapping("/admin/users/edit/{id}")
	public String editUserForm(@PathVariable Long id, Model model) {
		model.addAttribute("user",userServiceImp.getUserById(id));
		return "edit_user";
	}
	
	@PostMapping("/admin/users/{id}")
	public String updateUser(@PathVariable Long id, @ModelAttribute("user") UserBean user,	Model model) {
		UserBean existingUser = userServiceImp.getUserById(id);
		existingUser.setUserid(id);
		existingUser.setName(user.getName());
		existingUser.setMobile(user.getMobile());
		existingUser.setState(user.getState());
		existingUser.setCity(user.getCity());
		existingUser.setPincode(user.getPincode());
		existingUser.setAddress(user.getAddress());
		existingUser.setEmail(user.getEmail());		
		existingUser.setPassword(user.getPassword());
		userServiceImp.updateUser(existingUser);
		return "redirect:/admin/users";
	}
	
	
}
