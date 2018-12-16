package org.unnati.example.dao.jta;

import bitronix.tm.TransactionManagerServices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BitronixTransactionManagerSetUp {
    private static final Logger logger =Logger.getLogger(BitronixTransactionManagerSetUp.class.getName());

    /**
     *
     * Context Will get loaded from resource/jndi.properties
     *
     * */
    protected static Context context;


    /**
     * This Setup is print Everything in screen
     * */
    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(Level.ALL);

        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }
    static {
        try {
            logger.fine("Initializing JNDI Context");
            context = new InitialContext();
            logger.fine("JNDI loaded from "+context.getClass());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

   public BitronixTransactionManagerSetUp(){

   }

   public static void init(){
        logger.fine("Trying to load DataSource configuration");
        TransactionManagerServices.getConfiguration().setResourceConfigurationFilename("./resources/datasources.properties");
        logger.fine("Trying to load DataSource configuration");
        logger.fine("Initializing DataSource pool");
        TransactionManagerServices.getResourceLoader().init();
       logger.fine("DataSource Pool completed");
    }

    public static UserTransaction createUserTransaction(){
        return TransactionManagerServices.getTransactionManager();
    }

}
