package com.mtest.hservice;

/**
 *  Created by yuri on 03.12.17.
 */
import com.mtest.hmodel.Employee;
import com.mtest.jpadao.JpaEmployeeDao;

import java.util.List;



public class EmployeeService {

    private static JpaEmployeeDao jpaEmployeeDao;

    public EmployeeService() {
        jpaEmployeeDao = new JpaEmployeeDao();
    }

    public void persist(Employee entity) {
        jpaEmployeeDao.openCurrentSessionwithTransaction();
        jpaEmployeeDao.persist(entity);
        jpaEmployeeDao.closeCurrentSessionwithTransaction();
    }

    public void update(Employee entity) {
        jpaEmployeeDao.openCurrentSessionwithTransaction();
        jpaEmployeeDao.update(entity);
        jpaEmployeeDao.closeCurrentSessionwithTransaction();
    }

    public Employee findById(Integer id) {
        jpaEmployeeDao.openCurrentSession();
        Employee employee = jpaEmployeeDao.findById(id);
        jpaEmployeeDao.closeCurrentSession();
        return employee;
    }

    public void delete(Integer id) {
        jpaEmployeeDao.openCurrentSessionwithTransaction();
        Employee employee = jpaEmployeeDao.findById(id);
        jpaEmployeeDao.delete(employee);
        jpaEmployeeDao.closeCurrentSessionwithTransaction();
    }

    public List<Employee> findAll() {
        jpaEmployeeDao.openCurrentSession();
        List<Employee> employees = jpaEmployeeDao.findAll();
        jpaEmployeeDao.closeCurrentSession();
        return employees;
    }

    public void deleteAll() {
        jpaEmployeeDao.openCurrentSessionwithTransaction();
        jpaEmployeeDao.deleteAll();
        jpaEmployeeDao.closeCurrentSessionwithTransaction();
    }

    public JpaEmployeeDao JpaEmployeeDao() {
        return jpaEmployeeDao;
    }
}

