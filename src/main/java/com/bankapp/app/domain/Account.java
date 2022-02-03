package com.bankapp.app.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="accounttable")

public class Account {
	
	@Id
	private String accountnumber;
	private int accountid;
	private String name;
	private String phonenumber;
	private String branchname;
	private String ifsccode;
	private String email;
	private String address;
	private int balance;

	private int setlimit;

	public int getSetlimit() {
		return setlimit;
	}

	public void setSetlimit(int setlimit) {
		this.setlimit = setlimit;
	}

	@Transient
	private String statusMessage;
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Account() {
		super();
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public String getIfsccode() {
		return ifsccode;
	}
	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Account(String accountnumber,String name, String phonenumber, String branchname, String ifsccode, String email,
			String address,int balance, int accountid, int setlimit) {
		super();
		this.accountnumber = accountnumber;
		this.accountid=accountid;
		this.balance=balance;
		this.name = name;
		this.phonenumber = phonenumber;
		this.branchname = branchname;
		this.ifsccode = ifsccode;
		this.email = email;
		this.address = address;
		this.setlimit= setlimit;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public Account orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
