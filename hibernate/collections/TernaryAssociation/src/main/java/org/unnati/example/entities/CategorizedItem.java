package org.unnati.example.entities;

import com.sun.istack.internal.NotNull;

import java.util.Date;
import javax.persistence.*;

@Embeddable
public class CategorizedItem {

    @ManyToOne
    @JoinColumn(name = "UserId", updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "CategoryId", updatable = false,nullable = false)
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;


}
