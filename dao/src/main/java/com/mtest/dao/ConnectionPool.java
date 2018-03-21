package com.mtest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yuri on 25.02.18.
 */
@Component
public class ConnectionPool {

    private static DataSource dataSource;

    @Autowired
    public ConnectionPool(DataSource ds) {
        System.out.println("start autowire");
        System.out.println("datasource = " + dataSource);
        if (dataSource==null) {
            dataSource = ds;
        }
    }
    static Connection getConnection() {
        System.out.println("getConn!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(dataSource);
        // Obtain our environment naming context
//        try {
//            Context initCtx = new InitialContext();
//            Context envCtx = (Context) initCtx.lookup("java:comp/env");
//            // lookup our datasource
//            DataSource ds = (javax.sql.DataSource) envCtx.lookup("jdbc/phonebook");
//            // Allocate and use connection from the pool
//            return ds.getConnection();
//        } catch (NamingException | SQLException e)  {
//            e.printStackTrace();
//            return null;
//        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e)  {
            e.printStackTrace();
            return null;
        }
    }

}
