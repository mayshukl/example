package org.unnati.example.entities;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class ItemAttribute {


    private String attributeName;

    private String attributeValue;


    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
