package com.neosoft.model;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="carbooking")
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("CarBookingBeanFilter")  // dynamic filter service level
public class CarBookingBean {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Long bookingid;
	
	
	private String fullname;
	private String mobile;
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fdate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	private Date tdate;
	
	private String returnstatus;
	
	private double amount;
	
	@ManyToOne
	@JoinColumn(name="car_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private CarBean carbean;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserBean userbean;
	
	
	

}
