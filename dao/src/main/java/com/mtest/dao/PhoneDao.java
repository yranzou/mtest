package com.mtest.dao;


import com.mtest.dao.exceptions.DaoException;
import com.mtest.model.Department;
import com.mtest.model.Employee;
import com.mtest.model.Phone;
import com.mtest.model.PhoneType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
@Repository
public class PhoneDao {

    private String driver;
    private Properties props;

    private static final String SELECT_BY_ID = "SELECT * FROM phone WHERE employee_id=?";
    private static final String DELETE_BY_ID = "DELETE FROM phone WHERE employee_id=?";
    private static final String INSERT = "INSERT INTO phone (`employee_id`, `number`, `type`) " +
            "VALUES (?, ?, ?)";


    private Connection connection;

    private Connection getConnection() {
        return ConnectionPool.getConnection();
    }

//    public PhoneDao() {
//        try {
//            props = new Properties();
//            props.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
//            driver = props.getProperty("database.driver");
//        } catch (IOException e) {
//
//        }
//
//    }

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;



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
            connection.setAutoCommit(false);
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

    public Phone get(int id) throws DaoException {
        try {
            return this.hibernateTemplate.get(Phone.class, id);
        } catch (RuntimeException e) {
            throw new DaoException(e);
        }
    }

//    public Set<Phone> get(int id) {
//        connection = getConnection();
//        try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_BY_ID)) {
//            prepareStatement.setInt(1, id);
//             try (ResultSet resultSet = prepareStatement.executeQuery()) {
//
//                 Set<Phone> phones = new TreeSet<>();
//                 while (resultSet.next()) {
//                     phones.add(createPhoneFromResult(resultSet));
//                 }
//                 return phones;
//             }
//
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public Set<Phone> get(int id) throws DaoException {
//        Session session = null;
//        try {
//            session = sessionFactory.getCurrentSession();
//            Set<Phone> phones = new HashSet<Phone>(session.createCriteria(Phone.class).list());
//            for (Phone phone: phones
//                 ) {
//                System.out.println(phone);
//            }
//            return phones;
//        } catch (RuntimeException e) {
//            throw new DaoException(e);
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }

    private Phone createPhoneFromResult(ResultSet resultSet)
            throws SQLException {
//        System.out.println(resultSet.getString("name"));
        Phone phone = new Phone();
        phone.setNumber(resultSet.getString("number"));
        phone.setType(PhoneType.valueOf(resultSet.getString("type")));
        return phone;
    }
}

