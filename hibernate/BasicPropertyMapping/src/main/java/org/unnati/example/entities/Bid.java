package org.unnati.example.entities;


import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(insertable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    @org.hibernate.annotations.CreationTimestamp
    private Date createdOn;

    @Column(insertable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    @org.hibernate.annotations.UpdateTimestamp
    private Date updatedOn;

    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    @org.hibernate.annotations.ColumnDefault(value = "1")
    private double initialBidAmount;

    public long getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public double getInitialBidAmount() {
        return initialBidAmount;
    }

    public void setInitialBidAmount(double initialBidAmount) {
        this.initialBidAmount = initialBidAmount;
    }
}
