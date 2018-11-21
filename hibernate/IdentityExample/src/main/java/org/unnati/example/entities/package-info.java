@org.hibernate.annotations.GenericGenerator(
        name = "NATIVE_ID_GENERATOR",
        //This will select sequence or identity strategy depending on Sql Database dialect
        strategy = "native"
       )
@org.hibernate.annotations.GenericGenerator(
        name = "SEQUENCE_ID_GENERATOR",
        //This will use a database sequence to generate id . Sequence will be called before insert
        strategy = "sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name ="sequence_name",
                        value="USER_ID_SEQUENCE"
                ),
                @org.hibernate.annotations.Parameter(
                        name ="initial_value",
                        value="100"
                ),
        }

)
@org.hibernate.annotations.GenericGenerator(
        name = "SEQUENCE_IDENTITY_ID_GENERATOR",
        //This will use a database sequence to generate id . Sequence will be called WHILE inserting the data  As Hibernate: insert into User(id, name) values (next value for hibernate_sequence, ?)
        strategy = "sequence-identity",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name ="sequence_name",
                        value="USER_ID_SEQUENCE"
                ),
                @org.hibernate.annotations.Parameter(
                        name ="initial_value",
                        value="100"
                ),
        }

)
@org.hibernate.annotations.GenericGenerator(
        name = "ENHANCED_SEQUENCE_ID_GENERATOR",
        //This will generate a database sequence or if database does not support sequence , it will create a table with one row with one column. this will be called before insert.This provides some optimization Technique
        strategy = "enhanced-sequence"
)
@org.hibernate.annotations.GenericGenerator(
        //This strategy can not be configured in GenericGenerator.could not test
        name = "SEQUENCE_HI_LOW_ID_GENERATOR",
        strategy = "seqhilo"
)
@org.hibernate.annotations.GenericGenerator(
        //could not test . Not supported by HSQL and MySql
        name = "SEQUENCE_HI_LOW_ID_GENERATOR",
        strategy = "seqhilo"
)
@org.hibernate.annotations.GenericGenerator(
        // This is equivalent to GenerationType.TABLE
        name = "ENHANCED_TABLE_ID_GENERATOR",
        strategy = "enhanced-table"
)
@org.hibernate.annotations.GenericGenerator(
        // Because of Hibernate's quirk , this can not be configured through GenericGenerator. This is equivalent to GenerationType.IDENTITY . Value will be fetched at the time of insert.
        name = "IDENTITY_ID_GENERATOR",
        strategy = "identity"
)
@org.hibernate.annotations.GenericGenerator(
        //Highly efficient in non-clustered hibernate environment. This fetch the maximum value of the table at time of start up
        name = "INCREMENT_ID_GENERATOR",
        strategy = "increment"
)
@org.hibernate.annotations.GenericGenerator(
        // Very in-efficient strategy . This expects a trigger in table  to insert id in table. Have a mandatory parameter "KEY". Should be used with only old JDBC driver
        name = "SELECT_ID_GENERATOR",
        strategy = "select",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name="key",
                        value="id"
                )
        }
)
@org.hibernate.annotations.GenericGenerator(
        name = "UUID_ID_GENERATOR",
        strategy = "uuid2"
)
@org.hibernate.annotations.GenericGenerator(
        name = "GUID_ID_GENERATOR",
        strategy = "guid"
)
package org.unnati.example.entities;
