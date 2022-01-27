package com.bankapp.app.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="beneficiary_table")
public class Beneficiary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String receiverAccNo;
	private String name;
	private String nick_name;

	private String myAccountNumber;
	
	
	public String getMyAccountNumber() {
		return myAccountNumber;
	}
	public void setMyAccountNumber(String myAccountNumber) {
		this.myAccountNumber = myAccountNumber;
	}
	
	public String getReceiverAccNo() {
		return receiverAccNo;
	}
	public void setReceiverAccNo(String receiverAccNo) {
		this.receiverAccNo = receiverAccNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public Beneficiary(String receiverAccNo, String name, String nick_name, String myAccountNumber) {
		super();
		this.receiverAccNo = receiverAccNo;
		this.name = name;
		this.nick_name = nick_name;
		this.myAccountNumber = myAccountNumber;
	}
	public Beneficiary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
