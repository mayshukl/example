package org.unnati.example.entities.hibernate.eventlistener;

import org.hibernate.event.internal.DefaultPostLoadEventListener;
import org.hibernate.event.spi.PostLoadEvent;

public class CustomLoadListener extends DefaultPostLoadEventListener {

    public void onPostLoad(PostLoadEvent event) {
        System.out.println("CustomLoadListener : event Name "+event.toString());
        super.onPostLoad(event);
    }
}
