package org.unnati.example.entities.listeners;

import org.unnati.example.entities.Item;

import javax.persistence.PostRemove;
import java.util.logging.*;

public class ItemListener {

    private static Logger logger=Logger.getLogger(ItemListener.class.getName());

    static{
        Handler handler=new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter(){
            public synchronized String format(LogRecord record) {
                record.setMessage("ItemListener : "+record.getMessage());
                return super.format(record);
            }
        });
        handler.setLevel(Level.ALL);

        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

    @PostRemove
    public void postRemove(Item entity){
        logger.fine("after removing Item");
    }
}
