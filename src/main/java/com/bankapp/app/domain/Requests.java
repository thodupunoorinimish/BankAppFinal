package com.bankapp.app.domain;

import javax.persistence.*;

@Entity
@Table(name = "requests", catalog = "bankproject")
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int requestId;
    private String fromAccount;
    private String toAccount;
    private int amount;
    private String status = "PENDING";

    public Requests() {}

    public Requests(int requestId, String fromAccount, String toAccount, int amount, String status) {
        this.requestId = requestId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "requestId=" + requestId +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
	
