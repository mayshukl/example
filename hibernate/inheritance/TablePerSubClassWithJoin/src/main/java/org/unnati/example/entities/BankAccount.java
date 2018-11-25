package org.unnati.example.entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class BankAccount extends BankingDetails {


    @NotNull
    private long accountNumber;

    @NotNull
    private String ifscCode;

    @NotNull
    private String bankName;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
