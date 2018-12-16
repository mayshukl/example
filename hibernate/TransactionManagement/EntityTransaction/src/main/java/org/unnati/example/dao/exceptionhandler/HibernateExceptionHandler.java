package org.unnati.example.dao.exceptionhandler;

import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

public class HibernateExceptionHandler {

        public static void handleRollbackException(RollbackException rollbackException){

            Throwable cause=rollbackException.getCause();

            if(cause instanceof PersistenceException){
                handlePersistenceException(cause);
            }

        //    rollbackException.printStackTrace();
        }

    public static void handlePersistenceException(Throwable persistenceException){

        Throwable cause=persistenceException.getCause();

        if(cause instanceof ConstraintViolationException){
            System.out.println("ConstraintViolationException : indicates some form of integrity constraint violation");
        }else if(cause  instanceof GenericJDBCException){
            System.out.println("GenericJDBCException :  a generic exception in database(like trigger fail) which did not fall into any of the other categories");
        } else if(cause instanceof SQLGrammarException){
            System.out.println("SQLGrammarException :  indicates a grammar or syntax problem with the issued SQL");
        }else if(cause instanceof JDBCConnectionException){
            System.out.println("JDBCConnectionException :  indicates an error with the underlying JDBC communication");
        }else if(cause instanceof StaleObjectStateException ){
            System.out.println("org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction");
        }else{
            persistenceException.printStackTrace();
        }
    }

}
