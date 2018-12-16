package org.unnati.example.dao.jta.jpwh;

import bitronix.tm.resource.jdbc.PoolingDataSource;

public enum DatabaseProduct {
    HSQL(
            new DataSourceConfiguration() {
                @Override
                public void configure(PoolingDataSource ds, String connectionURL) {
                    ds.setClassName("org.hsqldb.jdbc.pool.JDBCXADataSource");
                    ds.getDriverProperties().put(
                            "URL",
                            connectionURL != null
                                    ? connectionURL :
                                    "jdbc:hsqldb:mem:testdb"
                    );

                    // Required for reading VARBINARY/LONG RAW columns easily, see
                    // http://stackoverflow.com/questions/10174951
                 }
            }   ,
            org.hibernate.dialect.HSQLDialect.class.getName()
    ),
    MYSQL(new DataSourceConfiguration() {
        @Override
        public void configure(PoolingDataSource ds, String connectionURL) {
            // TODO: MySQL XA support is completely broken, we use the BTM XA wrapper
            //ds.setClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
            ds.setClassName("bitronix.tm.resource.jdbc.lrc.LrcXADataSource");
            ds.getDriverProperties().put(
                    "url",
                    connectionURL != null
                            ? connectionURL :
                            "jdbc:mysql://localhost/test?sessionVariables=sql_mode='PIPES_AS_CONCAT'"
            );
            ds.getDriverProperties().put("driverClassName", "com.mysql.jdbc.Driver");
        }
    },
            // Yes, this should work with 5.6, no idea why Gail named it 5.7
            org.hibernate.dialect.MySQL57InnoDBDialect.class.getName());

    public DataSourceConfiguration configuration;
    public String hibernateDialect;

    private DatabaseProduct(DataSourceConfiguration configuration,
                            String hibernateDialect) {
        this.configuration = configuration;
        this.hibernateDialect = hibernateDialect;
    }


}
