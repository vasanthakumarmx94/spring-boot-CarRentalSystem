package com.neosoft;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = "vasu123";
		String encodedPassword = passwordEncoder.encode(plainPassword);
		
		System.out.println(encodedPassword);
	}

}
