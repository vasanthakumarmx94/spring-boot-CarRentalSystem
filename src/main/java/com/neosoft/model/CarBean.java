package com.neosoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="cars")
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("CarsBeanFilter")  // dynamic filter service level
public class CarBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="car_id")
	private Long carid;
	
	private String carname;
	private String carcompany;
	private String carcity;
	private String carnumaber;
	private String cardescription;
	private String issustatus;
	private double priceperday;
	
	
	
	
	
}
