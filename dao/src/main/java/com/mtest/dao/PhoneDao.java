package com.mtest.dao;


import com.mtest.model.Department;
import com.mtest.model.Employee;
import com.mtest.model.Phone;
import com.mtest.model.PhoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * Created by yuri on 26.11.17.
 */
@Component
public class PhoneDao {

    private String driver;
    private Properties props;

    private static final String SELECT_BY_ID = "SELECT * FROM phone WHERE employee_id=?";
    private static final String DELETE_BY_ID = "DELETE * FROM phone WHERE employee_id=?";
    private static final String INSERT = "INSERT INTO phone (`employee_id`, `number`, `type`) " +
            "VALUES (?, ?, ?)";


    private Connection connection;

    private Connection getConnection() {
        return ConnectionPool.getConnection();
    }

    public PhoneDao() {
        try {
            props = new Properties();
            props.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
            driver = props.getProperty("database.driver");
        } catch (IOException e) {

        }

    }

    public void persist(Set<Phone> phones, int employeeId) {
        connection = getConnection();

        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(INSERT)) {
            connection.setAutoCommit(false);
            Iterator<Phone> iterator = phones.iterator();
            Phone phone;
            while (iterator.hasNext())
            {
                phone = iterator.next();
                prepareStatement.setInt(1, employeeId);
                prepareStatement.setString(2, phone.getNumber());
                prepareStatement.setString(3, phone.getType().toString());
                prepareStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        connection = getConnection();
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void delete(Employee employee) {
        delete(employee.getId());
    }

    public Set<Phone> get(int id) {
        connection = getConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_BY_ID)) {
            prepareStatement.setInt(1, id);
             try (ResultSet resultSet = prepareStatement.executeQuery()) {

                 Set<Phone> phones = new TreeSet<>();
                 while (resultSet.next()) {
                     phones.add(createPhoneFromResult(resultSet));
                 }
                 return phones;
             }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Phone createPhoneFromResult(ResultSet resultSet)
            throws SQLException {
//        System.out.println(resultSet.getString("name"));
        Phone phone = new Phone();
        phone.setNumber(resultSet.getString("number"));
        phone.setType(PhoneType.valueOf(resultSet.getString("type")));
        return phone;
    }
}

