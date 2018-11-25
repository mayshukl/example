package org.unnati.example.entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "BD_TYPE")
@org.hibernate.annotations.DiscriminatorFormula(
        "case when CardNumber is not NULL then 'CC' else 'BA' end"
)
public abstract  class BankingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String Owner;


    @ManyToOne
    @JoinColumn(name="UerId")
    private User user;

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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
