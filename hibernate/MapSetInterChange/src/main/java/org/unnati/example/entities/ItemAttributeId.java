package org.unnati.example.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ItemAttributeId implements Serializable {

    @Column(name = "ItemId")
    private String itemId;
    @Column(name = "AttrId")
    private String attrId;
}
