package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@NamedEntityGraph(
        name="MaterialReceiptEntityGraph",
        attributeNodes = {
                @NamedAttributeNode("items")
        }
)
@Entity
public class MaterialReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mrNo;

    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
  //  @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "materialReceipt",fetch = FetchType.EAGER)
    private Collection<MaterialReceiptItem> items =new ArrayList<>();

    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="modifiedBy")
    private UserProfile modifiedBy;

    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="addedBy",updatable = false)
    private UserProfile addedBy;

    public UserProfile getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserProfile modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public UserProfile getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserProfile addedBy) {
        this.addedBy = addedBy;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public Collection<MaterialReceiptItem> getItems() {
        return items;
    }

    public void setItems(Collection<MaterialReceiptItem> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
