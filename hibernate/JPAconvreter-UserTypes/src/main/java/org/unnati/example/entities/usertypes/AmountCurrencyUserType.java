package org.unnati.example.entities.usertypes;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;
import org.unnati.example.entities.MonetaryAmount;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class AmountCurrencyUserType implements CompositeUserType, DynamicParameterizedType {

    private int currConFactor;

    //Next 2 methods will be used by Hibernate queryEngine
    @Override
    public String[] getPropertyNames() {
        return new String[]{"amount","currency"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{StandardBasicTypes.INTEGER,StandardBasicTypes.STRING};
    }

    @Override
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        return property==0?((MonetaryAmount)component).getAmount():((MonetaryAmount)component).getCurrency();
    }

    @Override
    public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
            throw new UnsupportedOperationException("Amount Currency is immutable");
    }

    @Override
    public Class returnedClass() {
        return MonetaryAmount.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x==y?true:x!=null?x.equals(y):false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;// Hibernate sometime needs to copy a object
    }

    @Override
    public boolean isMutable() {
        return false;// Mutable object improve the performance also
    }

    @Override
    public Serializable disassemble(Object value, SharedSessionContractImplementor session) throws HibernateException {
        return ((MonetaryAmount)value).toString();// This will be called to store the value at second level cache
    }

    @Override
    public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return MonetaryAmount.toAmountCurrency((String)cached); //This will be used to get the data from second level cache
    }

    @Override
    public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return original;  // This method will be used while EntityManager#merge
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        if(rs==null)
            return null;

        Integer amount=rs.getInt(names[0]);
        String currency=rs.getString(names[1]);
        MonetaryAmount monetaryAmount=new MonetaryAmount();
        monetaryAmount.setAmount(amount);
        monetaryAmount.setCurrency(currency);
        return monetaryAmount;
    }

    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
            if(value==null){
                statement.setNull(index,StandardBasicTypes.INTEGER.sqlType());
                statement.setNull(index+1,StandardBasicTypes.STRING.sqlType());
            }else{
                MonetaryAmount monetaryAmount=(MonetaryAmount)value;
                statement.setInt(index,monetaryAmount.getAmount()*currConFactor);
                statement.setString(index+1,monetaryAmount.getCurrency());
            }

    }

    @Override
    public void setParameterValues(Properties parameters) {
        currConFactor=Integer.parseInt((String)parameters.get("CurrencyConversionFactor"));
    }
}
