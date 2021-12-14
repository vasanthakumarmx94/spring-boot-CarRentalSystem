package com.neosoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neosoft.model.UserBean;
import com.neosoft.repository.UserRepository;

@Controller
public class MainController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("")
	public String viewindexpage() {
		return "index";
	}
	
	@GetMapping("/userregister")
	public String createUserForm(Model model) {
		model.addAttribute("user", new UserBean());
		return "Create_Registerform";
	}
	
	@PostMapping("/process_register")
	public String processRegister(UserBean user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		
	    return "register_success";
	}
	
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/index";
    }

	
	@GetMapping("/index")
	public String indexpage() {
		return "index";
	}
//	@GetMapping("/login")
//	public String loginpage() {
//		return "index";
//	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "index";
    }
	
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
    public String adminlogin(Model model) {
        
        return "adminlogin";
    }
	
	
	
	
	
	
	
	
}
