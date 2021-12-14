package com.neosoft.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(2)
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {

	// create 2 users for demo (Authentication)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// {noop} to your password in order for the DelegatingPasswordEncoder

		// With hard coded values
		// auth.inMemoryAuthentication().withUser("admin@gmail.com").password("{noop}admin123").roles("ADMIN");

		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin123").roles("ADMIN");
	}

	// Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and()
		.authorizeRequests()
		.antMatchers("/admin/**").authenticated()
		.and().formLogin()
		.loginPage("/adminlogin").and().csrf().disable();

	}

}
