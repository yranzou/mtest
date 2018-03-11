package com.mtest.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yuri on 25.02.18.
 */
public class ConnectionProvider {
    public static Connection getConnection() {
        // Obtain our environment naming context
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // lookup our datasource
            DataSource ds = (javax.sql.DataSource) envCtx.lookup("jdbc/phonebook");
            // Allocate and use connection from the pool
            return ds.getConnection();
        } catch (NamingException | SQLException e)  {
            e.printStackTrace();
            return null;
        }
    }

}
