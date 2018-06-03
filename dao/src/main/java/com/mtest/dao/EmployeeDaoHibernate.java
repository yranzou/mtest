package com.mtest.dao;


import com.mtest.dao.exceptions.DaoException;
import com.mtest.dao.utils.ConverterDate;
import com.mtest.model.Department;
import com.mtest.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by yuri on 26.11.17.
 */
@Component
public class EmployeeDaoHibernate {


    private static final String SELECT_ALL = "SELECT * FROM employee";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id=?";
//    private static final String SELECT_BY_ID_II = SELECT_ALL + " WHERE id=?";
    private static final String SELECT_BY_DEPARTMENT_ID = SELECT_ALL + " WHERE department_id=?";
    private static final String SELECT_BY_CHIEF_ID = SELECT_ALL + " WHERE chief_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM employee WHERE id=?";
    private static final String UPDATE = "UPDATE employee " +
            "SET `name`=?, surname=?, phone_private=?, department_id=?, chief_id=?, photo=? WHERE id=?";
    private static final String INSERT = "INSERT INTO employee (`name`, `surname`, `phone_private`, `photo`, `birthdate`) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_PHOTO = "UPDATE employee SET photo=? WHERE id=?";
    private static final String SELECT_ALL_LEFT_JOIN_DEP = "SELECT employee.*, department.* from employee left join department on employee.department_id = department.id";
    private static final String SELECT_ALL_DEPARTMENTS_CHIEFS = "SELECT employee.*, department.* FROM department RIGHT JOIN employee ON department.chief_id = employee.id";
    private static final String SELECT_DEPARTMENT_CHIEF = "SELECT employee.* FROM employee INNER JOIN department ON employee.id = department.chief_id WHERE department.id = ?";


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

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    public Employee get(int id) {
        try {
            session = sessionFactory.openSession();
            return (Employee) session.get(Employee.class, id);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private Connection getConnection() {
        return ConnectionPool.getConnection();
    }

    public Employee getDepartmentChief(int departmentId) {
        connection = getConnection();
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(SELECT_DEPARTMENT_CHIEF)) {
            prepareStatement.setInt(1, departmentId);
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
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void persist(Employee employee) throws DaoException {
        connection = getConnection();

        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(INSERT)) {
            connection.setAutoCommit(false);
            prepareStatement.setString(1, employee.getName());
            prepareStatement.setString(2, employee.getSurname());
            prepareStatement.setString(3, employee.getPhone());
            if (employee.getPhoto() == null) {
                prepareStatement.setNull(4, 0);
            } else {
                prepareStatement.setBlob(4, new SerialBlob(employee.getPhoto()));
            }
            if (employee.getBirthday() == null) {
                prepareStatement.setNull(5, 0);
            } else {
                prepareStatement.setDate(5, ConverterDate.toSqlDate(employee.getBirthday()));
            }
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
        finally {
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

    public synchronized void update(Employee employee) {
        connection = getConnection();
        try (PreparedStatement prepareStatement = this.connection
                .prepareStatement(UPDATE)) {
            connection.setAutoCommit(false);
            prepareStatement.setString(1, employee.getName());
            prepareStatement.setString(2, employee.getSurname());
            prepareStatement.setString(3, employee.getPhone());
            if (employee.getDepartmentId() == 0) {
                prepareStatement.setNull(4, employee.getDepartmentId());
            } else {
                prepareStatement.setInt(4, employee.getDepartmentId());
            }
            if (employee.getChiefId() == 0) {
                prepareStatement.setNull(5, employee.getChiefId());
            } else {
                prepareStatement.setInt(5, employee.getChiefId());
            }
            if (employee.getPhoto() == null) {
                prepareStatement.setNull(6, 0);
            } else {
                prepareStatement.setBlob(6, new SerialBlob(employee.getPhoto()));
            }
            prepareStatement.setInt(7, employee.getId());
            prepareStatement.executeUpdate();
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
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Employee> getAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return  session.createCriteria(Employee.class).list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Employee> getAllDepartmentsChiefs() {
        connection = getConnection();
        try (ResultSet resultSet = this.connection.createStatement()
                .executeQuery(SELECT_ALL_DEPARTMENTS_CHIEFS)) {
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(createEmployeeFromResult(resultSet));
            }
            return employees;
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

    public List<Employee> getCoworkers(Department department) {
        return getEmployees(SELECT_BY_DEPARTMENT_ID, department.getId());
    }

    public List<Employee> getSubordinates(Employee leader) {
        return getEmployees(SELECT_BY_CHIEF_ID, leader.getId());
    }


    public List<Employee> search(String searchIn, String searchValue) {
        connection = getConnection();
        searchValue = "%" + searchValue + "%";
//        try (PreparedStatement preparedStatement = this.connection.prepareStatement(Search.valueOf(searchIn).toString())) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(Search.valueOf(searchIn).toString());

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

    private List<Employee> getEmployees(String query, int id) {
        connection = getConnection();
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

    public void savePhoto(Employee employee, InputStream inputStream) {
        connection = getConnection();
        try (PreparedStatement prepareStatement = this.connection != null ? this.connection
                .prepareStatement(INSERT_PHOTO) : null) {
            assert prepareStatement != null;
            prepareStatement.setBlob(1, inputStream);
            prepareStatement.setInt(2, employee.getId());
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
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private Employee createEmployeeFromResult(ResultSet resultSet)
            throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setPhone(resultSet.getString("phone_private"));
//        employee.setPhone(resultSet.getString("phone"));
        employee.setChiefId(resultSet.getInt("chief_id"));
        employee.setDepartmentId(resultSet.getInt("department_id"));
        employee.setPhoto(resultSet.getBytes("photo"));
        employee.setBirthday(resultSet.getDate("birthdate"));
        return employee;
    }
}

