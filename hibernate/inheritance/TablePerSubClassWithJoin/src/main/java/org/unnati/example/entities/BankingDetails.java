package org.unnati.example.entities;



import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract  class BankingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


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
