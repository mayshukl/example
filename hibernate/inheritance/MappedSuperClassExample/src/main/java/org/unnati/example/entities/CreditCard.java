package org.unnati.example.entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class CreditCard extends BankingDetails {



    @NotNull
    private Date expDate;

    @NotNull
    private int cvvNumber;

    @NotNull
    private Long CardNumber;

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public Long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        CardNumber = cardNumber;
    }
}
