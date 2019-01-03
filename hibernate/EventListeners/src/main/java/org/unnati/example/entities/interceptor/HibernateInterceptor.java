package org.unnati.example.entities.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;

public class HibernateInterceptor extends EmptyInterceptor{

    @Override
    public void onDelete(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {

        System.out.println("HibernateInterceptor : Called before an object is deleted");
    }

    @Override
    public boolean onFlushDirty(
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
        System.out.println("HibernateInterceptor :  Called when an object is detected to be dirty, during a flush");
        return false;
    }

    @Override
    public boolean onLoad(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        System.out.println("HibernateInterceptor : Called just before an object is initialized");
        return false;
    }

    @Override
    public boolean onSave(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        System.out.println("HibernateInterceptor : mCalled before an object is saved");
        return false;
    }

    @Override
    public void postFlush(Iterator entities) {
        System.out.println("HibernateInterceptor : Called after a flush that actually ends in execution of the SQL statements required to synchronize in-memory state with the database");
    }

    @Override
    public void preFlush(Iterator entities) {
        System.out.println("HibernateInterceptor : Called before a flush");
    }

    @Override
    public Boolean isTransient(Object entity) {
        System.out.println("HibernateInterceptor : Called to distinguish between transient and detached entities");
        return null;
    }

/**    @Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {

        return null;
    }

    @Override
    public int[] findDirty(
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
        return null;
    }

    @Override
    public String getEntityName(Object object) {
        return null;
    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        return null;
    }
**/
    @Override
    public void afterTransactionBegin(Transaction tx) {
        System.out.println("HibernateInterceptor : Called when a Hibernate transaction is begun via the Hibernate Transaction API.");
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        System.out.println("HibernateInterceptor : Called after a transaction is committed or rolled back");
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        System.out.println("HibernateInterceptor :  Called before a transaction is committed (but not before rollback)");
    }

 /**   @Override
    public String onPrepareStatement(String sql) {
        return sql;
    }

    @Override
    public void onCollectionRemove(Object collection, Serializable key) throws CallbackException {
    }

    @Override
    public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException {
    }

    @Override
    public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException {
    }
    **/

}
