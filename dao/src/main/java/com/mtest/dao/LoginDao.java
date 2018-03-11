package com.mtest.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//
public class LoginDao {
    public static boolean validate(String name,String pass){
        Properties props = new Properties();
        try {
//            props.load(ClassLoader.class.getResourceAsStream("db.properties"));
            InputStream inputStream =
                    LoginDao.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(inputStream);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return (name.equals(props.getProperty("web.user")) && pass.equals(props.getProperty("web.password")));
    }

    public static String getFilePathToSave() {

        Properties prop = new Properties();
        String filePath = "";

        try {

            InputStream inputStream =
                    LoginDao.class.getClassLoader().getResourceAsStream("db.properties");

            prop.load(inputStream);
            filePath = prop.getProperty("json.filepath");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;

    }
}
