package com.bankapp.app.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="transactions", catalog="bankproject")
public class Transactions {

	 @Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	private int transactionId;
	private int amount;
	private String type;
    
    private String AccountNumber1;
    private String AccountNumber2;
    
    @Transient 
	private String statusMessage;
    
	
	public String getAccountNumber1() {
		return AccountNumber1;
	}

	public void setAccountNumber1(String AccountNumber1) {
		this.AccountNumber1 = AccountNumber1;
	}

	public String getAccountNumber2() {
		return AccountNumber2;
	}

	public void setAccountNumber2(String AccountNumber2) {
		this.AccountNumber2 = AccountNumber2;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	@Temporal(TemporalType.DATE)
	private Date date=new Date();
	    
	@Temporal(TemporalType.TIME)
	private Date time=new Date();

	      
	    public Transactions() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    
	    public Transactions(int amount, String AccountNumber1, String AccountNumber2) {
			super();
			this.amount = amount;
			//this.type = type;
			this.AccountNumber1 = AccountNumber1;
			this.AccountNumber2 = AccountNumber2;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		public int getTransactionId() {
	        return transactionId;
	    }
	    public void setId(int transactionId) {
	        this.transactionId = transactionId;
	    }
	    public int getAmount() {
	        return amount;
	    }
	    public void setAmount(int amount) {
	        this.amount = amount;
	    }
	    public String getType() {
	        return type;
	    }
	    public void setType(String type) {
	        this.type = type;
	    }
	    
}
	
