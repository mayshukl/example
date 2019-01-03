package org.unnati.example.entities.listeners;


import org.unnati.example.entities.Item;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.*;

public class GenericEntityListener {

    private static Logger logger=Logger.getLogger(GenericEntityListener.class.getName());

    static{
        Handler handler=new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter(){
            public synchronized String format(LogRecord record) {
                record.setMessage("GenericEntityListener : "+record.getMessage());
               return super.format(record);
            }
        });
        handler.setLevel(Level.ALL);

        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }


    @PrePersist
    public void prePersis(Object entity) {
        logger.fine(" Before Persist");
    }
    @PostPersist
    public void postPersis(Object entity){
        String id=null;
        try {
            id=entity.getClass().getMethod("getId").invoke(entity).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        logger.fine("Entity Saved with Id : "+id);
    }


    @PostLoad
    public void afterLoad(Object entity){
        logger.fine("after loading entity");
    }

    @PreUpdate
    public void preUpdate(Object entity){
        logger.fine("before flushing entity");
    }

    @PostUpdate
    public void postLoad(Object entity){
        logger.fine("after flushing entity");
    }

    @PreRemove
    public void preRemove(Object entity){
        logger.fine("before removing entity");
    }

    @PostRemove
    public void postRemove(Object entity){
        logger.fine("after removing entity");
    }

}
