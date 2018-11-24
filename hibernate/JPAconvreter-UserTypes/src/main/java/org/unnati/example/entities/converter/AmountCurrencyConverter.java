package org.unnati.example.entities.converter;

import org.unnati.example.entities.AmountCurrency;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AmountCurrencyConverter implements AttributeConverter<AmountCurrency,String> {
    @Override
    public String convertToDatabaseColumn(AmountCurrency attribute) {
        return attribute.toString();
    }

    @Override
    public AmountCurrency convertToEntityAttribute(String dbData) {
        return  AmountCurrency.toAmountCurrency(dbData);
    }
}
