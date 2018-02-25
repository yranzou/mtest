package com.mtest.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by yuri on 25.02.18.
 */
public class LoginDao {
    public static boolean validate(String name,String pass){
        Properties props = new Properties();
        try {
            props.load(ClassLoader.class.getResourceAsStream("db.properties"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
               return (name.equals(props.getProperty("web.user")) && pass.equals(props.getProperty("web.password")));
    }
}