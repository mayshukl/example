package org.unnati.example.entities;



import javax.persistence.*;

@Entity
public class CustomerProfile {

    @Id
    @org.hibernate.annotations.GenericGenerator(name="ForeignIdGenerator",strategy = "foreign",parameters =@org.hibernate.annotations.Parameter( name="property" ,value = "userProfile"))
    @GeneratedValue(generator="ForeignIdGenerator")
    private int id;


    @OneToOne
    @PrimaryKeyJoinColumn
    private UserProfile userProfile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
