package com.mtest.dao;


import com.mtest.model.Department;
import com.mtest.model.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yuri on 26.11.17.
 */
public class EmployeeDao {

    private static final String SELECT_ALL = "SELECT * FROM employee";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=?";
    private static final String SELECT_BY_DEPARTMENT_ID = SELECT_ALL + " WHERE department_id=?";
    private static final String SELECT_BY_CHIEF_ID = SELECT_ALL + " WHERE chief_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM employee WHERE id=?";
    private static final String UPDATE = "UPDATE employee " +
            "SET `name`=?, surname=?, phone_private=? WHERE id=?";
    private static final String INSERT = "INSERT INTO employee (`name`, `surname`, `phone_private`) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_CHIEF_ID_WITH_NULL = "UPDATE employee " +
            "SET chief_id=NULL WHERE chief_id=?";
    private static final String UPDATE_EMPLOYEE_DEPARTMENT_WITH_NULL = "UPDATE employee " +
            "SET department_id=NULL WHERE department_id=?";


    private Connection connection;

    public EmployeeDao() {
        try {
            Properties props = new Properties();
            props.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
            String driver = props.getProperty("database.driver");

            try {
                Class.forName(driver);
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
        setChiefIdToNull(id);
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.updateChiefIdWithNull(id);
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
//            prepareStatement.setInt(15, id);
            prepareStatement.setString(1, employee.getName());
            prepareStatement.setString(2, employee.getSurname());
            prepareStatement.setString(3, employee.getPhone());
            prepareStatement.setInt(4, employee.getId());
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
        employee.setChief_id(resultSet.getInt("chief_id"));
        return employee;
    }

    private void setChiefIdToNull(int id) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(UPDATE_CHIEF_ID_WITH_NULL)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch  block
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            System.out.println("Employee setChiefIdToNull is set");
            List<Employee> employees = getAll();
            for (Employee e : employees
                    ) {
                System.out.println(e);
            }
        }
    }

    void updateEmployeeDepartmentWithNull(int id) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(UPDATE_EMPLOYEE_DEPARTMENT_WITH_NULL)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch  block
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}

