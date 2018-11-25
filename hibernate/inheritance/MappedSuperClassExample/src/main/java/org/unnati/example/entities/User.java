package org.unnati.example.entities;

import org.hibernate.mapping.Bag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    public int getId(){
        return id;
    }


    private String name;


 //   @OneToMany(mappedBy = "user")
  //  List<BankingDetails> bankingDetails=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
