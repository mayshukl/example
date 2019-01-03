package org.unnati.example.entities.hibernate.eventlistener;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultPersistEventListener;
import org.hibernate.event.spi.PersistEvent;


public class CustomPersistListener extends DefaultPersistEventListener  {
    public void onPersist(PersistEvent event) throws HibernateException {
        System.out.println("CustomPersistListener : event Name "+event.toString());
        super.onPersist(event);
    }
}
