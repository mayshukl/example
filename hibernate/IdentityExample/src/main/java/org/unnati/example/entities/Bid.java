package org.unnati.example.entities;

import javax.persistence.*;

@Entity
@TableGenerator(name="ID_GENERATOR",table = "TABLE_SEQUENCE",initialValue = 100 ,allocationSize = 1)
//@SequenceGenerator(name = "ID_GENERATOR",initialValue = 1000,allocationSize = 1)
public class Bid {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private String id;
}
