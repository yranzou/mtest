package com.mtest.dao;


import com.mtest.model.Department;
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
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=?";
    private static final String SELECT_BY_DEPARTMENT_ID = SELECT_ALL + " WHERE department_id=?";
    private static final String SELECT_BY_CHIEF_ID = SELECT_ALL + " WHERE chief_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM employee WHERE id=?";
    private static final String UPDATE = "UPDATE employee " +
            "SET `name`=?, surname=?, phone_private=?, department_id=?, chief_id=? WHERE id=?";
    private static final String INSERT = "INSERT INTO employee (`name`, `surname`, `phone_private`) " +
            "VALUES (?, ?, ?)";
    private static final String SELECT_ALL_LEFT_JOIN_DEP = "SELECT employee.*, department.* from employee left join department on employee.department_id = department.id";



    enum Search {
//        NAME("SELECT * FROM employee WHERE `name` LIKE ?"),
//        SURNAME("SELECT * FROM employee WHERE `surname` LIKE ?"),
//        PHONE("SELECT * FROM employee WHERE `phone_private` LIKE ?"),
//        DEPARTMENT("SELECT * FROM employee WHERE `department_id` IN " +
//                "(SELECT `id` FROM `department` WHERE `name` LIKE ?)"),
//        LEADER("SELECT * FROM employee WHERE `chief_id` IN " +
//                "(SELECT `id` from employee WHERE `name` LIKE ?)");

        NAME(SELECT_ALL_LEFT_JOIN_DEP + "WHERE `name` LIKE ?"),
        SURNAME(SELECT_ALL_LEFT_JOIN_DEP + "WHERE `surname` LIKE ?"),
        PHONE(SELECT_ALL_LEFT_JOIN_DEP + "WHERE `phone_private` LIKE ?"),
        DEPARTMENT(SELECT_ALL_LEFT_JOIN_DEP + "WHERE `department_id` IN " +
                           "(SELECT `id` FROM `department` WHERE `name` LIKE ?)"),
        LEADER(SELECT_ALL_LEFT_JOIN_DEP + "WHERE `chief_id` IN " +
                       "(SELECT `id` from employee WHERE `name` LIKE ?)");

        private final String query;

        Search(String query) {
            this.query = query;
        }


        @Override
        public String toString() {
            return query;
        }
    }


    private Connection connection;

    public EmployeeDao() {
        try {
            Properties props = new Properties();
            props.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
            String driver = props.getProperty("database.driver");

            try {
                System.out.println("try load driver jdbc");
                Class.forName(driver);
                System.out.println(driver + " loaded");
            } catch (ClassNotFoundException e) {
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
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(SELECT_BY_ID)) {
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

    public void persist(Employee employee) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(INSERT)) {
            prepareStatement.setString(1, employee.getName());
            prepareStatement.setString(2, employee.getSurname());
            prepareStatement.setString(3, employee.getPhone());
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void delete(int id) {
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
        }
    }

    public synchronized void delete(Employee employee) {
        delete(employee.getId());
    }

    public synchronized void update(Employee employee) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(UPDATE)) {
            prepareStatement.setString(1, employee.getName());
            prepareStatement.setString(2, employee.getSurname());
            prepareStatement.setString(3, employee.getPhone());
            if (employee.getDepartmentId() == 0) {
                prepareStatement.setNull(4, employee.getDepartmentId());
            } else {
                prepareStatement.setInt(4, employee.getDepartmentId());
            }
            if (employee.getChiefId() == 0)
            {
                prepareStatement.setNull(5,employee.getChiefId());
            } else {
                prepareStatement.setInt(5, employee.getChiefId());
            }
            prepareStatement.setInt(6, employee.getId());
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

    public List<Employee> getCoworkers(Department department)
    {
        return getEmployees(SELECT_BY_DEPARTMENT_ID, department.getId());
    }

    public List<Employee> getSubordinates(Employee leader) {
        return getEmployees(SELECT_BY_CHIEF_ID, leader.getId());
    }


    public List<Employee> search(String searchIn, String searchValue) {

        searchValue = "%"+searchValue+"%";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(Search.valueOf(searchIn).toString())) {

            preparedStatement.setString(1, searchValue);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Employee> employees = new ArrayList<>();
                while (resultSet.next()) {
                    employees.add(createEmployeeFromResult(resultSet));
                }
                return employees;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private List<Employee> getEmployees(String query, int id) {
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(query)) {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                List<Employee> employees = new ArrayList<>();
                while (resultSet.next()) {
                    employees.add(createEmployeeFromResult(resultSet));
                }
                return employees;
            }
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
        employee.setChiefId(resultSet.getInt("chief_id"));
        employee.setDepartmentId(resultSet.getInt("department_id"));
        return employee;
    }
}

