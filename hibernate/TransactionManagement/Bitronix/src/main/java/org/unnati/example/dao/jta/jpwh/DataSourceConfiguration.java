package org.unnati.example.dao.jta.jpwh;

import bitronix.tm.resource.jdbc.PoolingDataSource;

public interface DataSourceConfiguration {
    void configure(PoolingDataSource ds, String connectionURL);
}
