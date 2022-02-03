package com.bankapp.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "usertable")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	private String accountnumber;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String securityquestion1;
	private String securityquestion2;
	@Transient
	private String statusMessage;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date createdtime;

	@PrePersist
	private void OnCreate() {
		createdtime = new Date();
	}

	public User() {

	}

	public User(String accountnumber, String username, String password, String firstname, String lastname,
			String phonenumber, String securityquestion1, String securityquestion2) {
		super();
		// this.userid=userid;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.accountnumber = accountnumber;
		this.securityquestion1 = securityquestion1;
		this.securityquestion2 = securityquestion2;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getSecurityquestion1() {
		return securityquestion1;
	}


	public void setSecurityquestion1(String securityquestion1) {
		this.securityquestion1 = securityquestion1;
	}


	public String getSecurityquestion2() {
		return securityquestion2;
	}


	public void setSecurityquestion2(String securityquestion2) {
		this.securityquestion2 = securityquestion2;
	}
}