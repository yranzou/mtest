package com.mtest.dao;

import com.mtest.model.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *  Created by yuri on 26.11.17.
 */
public class EmployeeDao {

    private static final String SELECT_ALL = "SELECT * FROM employee";
    //    private static final String SELECT_ALL = "SELECT * FROM EMPLOYEE_TBL";
//
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=?";
    //    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE EMP_ID=?";
    private static final String DELETE_BY_ID = "DELETE FROM employee WHERE id=?";
//    private static final String DELETE_BY_ID = "DELETE FROM EMPLOYEE_TBL WHERE id=?";
    private static final String UPDATE = "UPDATE employee " +
        "SET name=?, middle_name=?, birthdate=?, address_home=?, " +
        "address_work=?, email=?, icq=?, skype=?, info=?, phone_private=?, " +
        "phone_work=?, chief_id=?, address=?, department_id=? " +
        "WHERE id=?";

    private Connection connection;

    public String str1 = "test";

    public EmployeeDao() {
        try {
            Properties props = new Properties();
            props.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
            String driver = props.getProperty("database.driver");

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            String url = props.getProperty("database.url");
            String user = props.getProperty("database.user");
            String password = props.getProperty("database.password");

            this.connection = DriverManager.getConnection(url, user, password);
            this.connection.setAutoCommit(false);
        } catch (IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Employee get(int id) {
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(SELECT_BY_ID))
        {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createEmployeeFromResult(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int id) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch  block
            e.printStackTrace();
        }
    }

    public void update(Employee employee) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Employee> getAll() {
        try (ResultSet resultSet = this.connection.createStatement()
                .executeQuery(SELECT_ALL)) {
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(createEmployeeFromResult(resultSet));
            }
            return employees;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private Employee createEmployeeFromResult(ResultSet resultSet)
            throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setPhone(resultSet.getString("phone_private"));
        return employee;
    }

}

