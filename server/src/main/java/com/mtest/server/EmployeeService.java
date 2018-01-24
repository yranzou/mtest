package com.mtest.server;

import com.mtest.dao.EmployeeDao;
import com.mtest.model.Department;
import com.mtest.model.Employee;

import java.util.List;

/**
 * Created by yuri on 20.01.18.
 */
public class EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }

    public List<Employee> getCoworkers(Department department) {
        return employeeDao.getCoworkers(department);
    }

    public List<Employee> getSubordinates(Employee leader) {
        return employeeDao.getSubordinates(leader);
    }


    public void create(Employee employee) {
        employeeDao.persist(employee);
    }


    public void update(Employee employee) {
        employeeDao.update(employee);
    }


    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }
}
