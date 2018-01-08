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
            "SET 'name'=?, chief_id=? WHERE id=?";
    private static final String INSERT = "INSERT INTO department " +
            "VALUES ('name'=?, cief_id=?)";

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
//            prepareStatement.setInt(15, id);
            prepareStatement.setString(1, department.getName());
            prepareStatement.setString(2, department.getChief_id());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
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

    public void update(Department department) {
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(UPDATE)) {
            prepareStatement.setString(1, department.getName());
            prepareStatement.setString(2, department.getChief_id());
            prepareStatement.setInt(3, department.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        department.setChief_id(resultSet.getString("chief_id"));

        return department;
    }

}
