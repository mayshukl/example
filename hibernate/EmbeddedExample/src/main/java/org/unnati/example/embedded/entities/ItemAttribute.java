package org.unnati.example.embedded.entities;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ItemAttribute {

    private String attributeName;
    private String getAttributeValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemAttribute that = (ItemAttribute) o;
        return Objects.equals(attributeName, that.attributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName);
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getGetAttributeValue() {
        return getAttributeValue;
    }

    public void setGetAttributeValue(String getAttributeValue) {
        this.getAttributeValue = getAttributeValue;
    }
}
