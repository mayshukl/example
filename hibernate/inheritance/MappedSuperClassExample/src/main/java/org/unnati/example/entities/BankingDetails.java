package org.unnati.example.entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@MappedSuperclass
public abstract  class BankingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String Owner;


//    @ManyToOne
//    @JoinColumn(name="UerId")
//    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }


}
