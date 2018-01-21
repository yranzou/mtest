package com.mtest.server;

import com.mtest.dao.EmployeeDao;
import com.mtest.model.Department;
import com.mtest.model.Employee;

import java.util.List;

/**
 * Created by yuri on 20.01.18.
 */
public class EmployeeService implements Service {
    EmployeeDao employeeDao;

    List<Employee> getEmployees(Department department) {
        employeeDao = new EmployeeDao();
        employeeDao.
    }

    List<Employee> getSubordinates(Employee leader) {
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
