@org.hibernate.annotations.FetchProfiles(
        @org.hibernate.annotations.FetchProfile(
                name="PurchaseOrderFetchProfile",
                fetchOverrides = @org.hibernate.annotations.FetchProfile.FetchOverride(
                        entity = PurchaseOrder.class,
                        association ="addedBy" ,
                        mode = FetchMode.JOIN
                )
        )
)
package org.unnati.example.entities;

import org.hibernate.annotations.FetchMode;