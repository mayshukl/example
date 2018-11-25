package org.unnati.example.entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "CC")
@SecondaryTable(name="CreditCard",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "Id")
)
public class CreditCard extends BankingDetails {



    @NotNull
    @Column(table = "CreditCard" , nullable = false)
    private Date expDate;

    @NotNull
    @Column(table = "CreditCard" , nullable = false)
    private int cvvNumber;

    @NotNull
    @Column(table = "CreditCard" , nullable = false)
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
