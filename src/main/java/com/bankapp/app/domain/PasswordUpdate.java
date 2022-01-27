package com.bankapp.app.domain;

public class PasswordUpdate {

    private String currentPassword;
    private String newPassword;
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public PasswordUpdate(String currentPassword, String newPassword, String accountNumber) {
        super();
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.accountNumber = accountNumber;
    }

    public PasswordUpdate() {
        super();
    }

    @Override
    public String toString() {
        return "PasswordUpdate{" +
                "currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
