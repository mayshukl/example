package org.unnati.example.entities;

import javax.persistence.*;

@Entity

public class User {

   // @Id()
   // @GeneratedValue(generator = "NATIVE_ID_GENERATOR")
   // @GeneratedValue(generator = "SEQUENCE_ID_GENERATOR")
  //  @GeneratedValue(generator = "SEQUENCE_IDENTITY_ID_GENERATOR")
  //  @GeneratedValue(generator = "ENHANCED_SEQUENCE_ID_GENERATOR")
  // @GeneratedValue(generator = "SEQUENCE_HI_LOW_ID_GENERATOR")
 //  @GeneratedValue(generator = "ENHANCED_TABLE_ID_GENERATOR")
 //  @GeneratedValue(generator = "IDENTITY_ID_GENERATOR")
 //  @GeneratedValue(generator = "INCREMENT_ID_GENERATOR")
 //  @GeneratedValue(generator = "SELECT_ID_GENERATOR")
//    private  int id;
//    public int getId(){
//        return id;
//    }

    @Id
    @GeneratedValue(generator = "UUID_ID_GENERATOR")
   // @GeneratedValue(generator = "GUID_ID_GENERATOR")
    private String id;
    public String getId(){
        return id;
    }


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
