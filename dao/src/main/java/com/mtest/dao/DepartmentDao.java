package com.mtest.dao;

import com.mtest.dao.exceptions.DaoException;
import com.mtest.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



/**
 *  Created by yuri on 04.01.18.
 */
@Repository
public class DepartmentDao {

    private String driver;
    private Properties props;
    private static final String SELECT_ALL = "SELECT * FROM department";

    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=?";

    private static final String SELECT_BY_CHIEF_ID = SELECT_ALL + " WHERE chief_id=?";


    private static final String DELETE_BY_ID = "DELETE FROM department WHERE id=?";

    private static final String UPDATE = "UPDATE department " +
            "SET `name`=?, chief_id=? WHERE id=?";
    private static final String INSERT = "INSERT INTO department (`name`, `chief_id`) " +
            "VALUES (?, ?)";

    private static final String SELECT_ALL_LEFT_JOIN_DEP = "SELECT department.* from employee left join department on employee.department_id = department.id";

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;


    enum Search {

        NAME(SELECT_ALL_LEFT_JOIN_DEP + " WHERE employee.name LIKE ?"),
        SURNAME(SELECT_ALL_LEFT_JOIN_DEP + " WHERE `surname` LIKE ?"),
        PHONE(SELECT_ALL_LEFT_JOIN_DEP + " WHERE `phone_private` LIKE ?"),
        DEPARTMENT(SELECT_ALL_LEFT_JOIN_DEP + " WHERE `department_id` IN " +
                "(SELECT `id` FROM `department` WHERE `name` LIKE ?)"),
        LEADER(SELECT_ALL_LEFT_JOIN_DEP + " WHERE `chief_id` IN " +
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

//    private Connection getConnection() {
//        try {
//            try {
//                System.out.println("try load driver jdbc");
//                Class.forName(driver);
//                System.out.println(driver + " loaded");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            String url = props.getProperty("database.url");
//            String user = props.getProperty("database.user");
//            String password = props.getProperty("database.password");
//
//            Connection connection = DriverManager.getConnection(url, user, password);
//            connection.setAutoCommit(false);
//            return connection;
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public DepartmentDao() {
        try {
            props = new Properties();
            props.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
            driver = props.getProperty("database.driver");
        } catch (IOException e) {

        }
        //////////////////////////////////////
//        try {
//
//            connection = getConnection();
//            if (connection == null)
//            {
//                System.out.println("HUIbb");
//            }
//            connection.setAutoCommit(false);
//        } catch (SQLException | NullPointerException e) {
//            e.printStackTrace();
//        }
    }

    public Department get(int id) throws DaoException {
        try {
            System.out.println(":::::::::::::::::::::::::::DEP " + id);
            return this.hibernateTemplate.get(Department.class, id);
        } catch (RuntimeException e) {
            throw new DaoException(e);
        }
    }

    private Connection getConnection() {
        return ConnectionPool.getConnection();
    }


    public void persist(Department department) {
        connection = getConnection();
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(INSERT)) {
            prepareStatement.setString(1, department.getName());
            if (department.getChiefId() == 0) {
                prepareStatement.setNull(2, department.getChiefId());
            }
            else {
                prepareStatement.setInt(2, department.getChiefId());
            }
            prepareStatement.executeUpdate();
            connection.commit();
//            return department;
        } catch (SQLException e) {
            // TODO Auto-generated catch  block
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
//            return null;
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

    public void delete(int id) {
        connection = getConnection();
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
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

    public void delete(Department department) {
        delete(department.getId());
    }

    public void update(Department department) {
        connection = getConnection();
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(UPDATE)) {
            prepareStatement.setString(1, department.getName());
            if (department.getChiefId() == 0) {
                prepareStatement.setNull(2, department.getChiefId());
            } else {
                prepareStatement.setInt(2, department.getChiefId());
            }
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

    public List<Department> getAll() throws DaoException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createCriteria(Department.class).list();
        } catch (RuntimeException e) {
            throw new DaoException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Department> search(String searchIn, String searchValue) {
        connection = getConnection();
        searchValue = "%"+searchValue+"%";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(Search.valueOf(searchIn).toString())) {

            preparedStatement.setString(1, searchValue);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Department> departments = new ArrayList<>();
                while (resultSet.next()) {
                    departments.add(createDepartmentFromResult(resultSet));
                }
                return departments;
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

    private Department createDepartmentFromResult(ResultSet resultSet)
            throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("department.name"));
        department.setChiefId(resultSet.getInt("chief_id"));
        return department;
    }


}
