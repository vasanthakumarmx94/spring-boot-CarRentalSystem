package com.neosoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity							// Specifies that the class is an entity. This annotation is applied to the entity class
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserBeanFilter")  // dynamic filter service level
public class UserBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userid;
	
	private String name;
	
	@NotNull
	@Size(max=15)
	private String mobile;
	
	private String state;
	private String city;
	private String pincode;
	private String address;
	
	@NotNull
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	

}
