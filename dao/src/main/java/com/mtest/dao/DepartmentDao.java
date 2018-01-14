package com.mtest.dao;

import com.mtest.model.Department;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *  Created by yuri on 04.01.18.
 */
public class DepartmentDao {
    private static final String SELECT_ALL = "SELECT * FROM department";

    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=?";

    private static final String DELETE_BY_ID = "DELETE FROM department WHERE id=?";

    private static final String UPDATE = "UPDATE department " +
            "SET `name`=?, chief_id=? WHERE id=?";
    private static final String UPDATE_CHIEF_ID_WITH_NULL = "UPDATE department " +
            "SET `chief_id`=NULL WHERE chief_id=?";
    private static final String INSERT = "INSERT INTO department (`name`, `chief_id`) " +
            "VALUES (?, ?)";

    private Connection connection;

    public DepartmentDao() {
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

    public Department get(int id) {
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(SELECT_BY_ID))
        {
            prepareStatement.setInt(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createDepartmentFromResult(resultSet);
                }
            }
            return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void persist(Department department) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(INSERT)) {
            prepareStatement.setString(1, department.getName());
            prepareStatement.setInt(2, department.getChief_id());
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

    public void delete(int id) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.updateEmployeeDepartmentWithNull(id);

        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            int tmp; // TODO clean duplicated code int tmp
            connection.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void delete(Department department) {
        delete(department.getId());
    }

    public void update(Department department) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(UPDATE)) {
            prepareStatement.setString(1, department.getName());
            prepareStatement.setInt(2, department.getChief_id());
            prepareStatement.setInt(3, department.getId());
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

    public void updateChiefIdWithNull(int chief_id) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(UPDATE_CHIEF_ID_WITH_NULL)) {
            prepareStatement.setInt(1, chief_id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            System.out.println("Department setChiefIdToNull is set");
            List<Department> departments = getAll();
            for (Department d: departments
                 ) {
                System.out.println(d);
            }
        }
    }

    public List<Department> getAll() {
        try (ResultSet resultSet = this.connection.createStatement()
                .executeQuery(SELECT_ALL)) {
            List<Department> departments = new ArrayList<>();
            while (resultSet.next()) {
                departments.add(createDepartmentFromResult(resultSet));
            }
            return departments;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private Department createDepartmentFromResult(ResultSet resultSet)
            throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));
        department.setChief_id(resultSet.getInt("chief_id"));
        return department;
    }


}
