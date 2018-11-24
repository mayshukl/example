
@org.hibernate.annotations.TypeDef(
        name="usd_monetary_amount",
        typeClass = AmountCurrencyUserType.class,
        parameters = {
         @org.hibernate.annotations.Parameter(name = "CurrencyConversionFactor",value = "2")
        }
)
package org.unnati.example.entities;

import org.unnati.example.entities.usertypes.AmountCurrencyUserType;