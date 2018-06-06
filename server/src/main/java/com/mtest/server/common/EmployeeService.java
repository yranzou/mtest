package com.mtest.server.common;

import com.mtest.dao.EmployeeDao;
import com.mtest.dao.EmployeeDaoHibernate;
import com.mtest.dao.exceptions.DaoException;
import com.mtest.model.Department;
import com.mtest.model.Employee;
import com.mtest.server.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * Created by yuri on 20.01.18.
 */
@Component
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeService() {
    }

    public List<Employee> getCoworkers(Department department) throws ServerException {
        try {
            return employeeDao.getCoworkers(department);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Employee> getSubordinates(Employee leader) throws ServerException {
        try {
            return employeeDao.getSubordinates(leader);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Employee> getAll() throws ServerException {
        try {
            return employeeDao.getAll();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Employee> getAllDepartmentsChiefs() throws ServerException {
        try {
            return employeeDao.getAllDepartmentsChiefs();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public Employee get(int id) throws ServerException {
        try {
            return employeeDao.get(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public Employee getDepartmentChief(int id) throws ServerException {
        try {
            return employeeDao.getDepartmentChief(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }


//    public List<Employee> search (String str) {
//        return employeeDao.search(str);
//    }

    public List<Employee> search (String searchIn, String searchValue) throws ServerException {
        try {
            return employeeDao.search(searchIn, searchValue);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void savePhoto(Employee employee, InputStream inputStream) {
        employeeDao.savePhoto(employee, inputStream);
    }



    public void create(Employee employee) throws ServerException {
        try {
            employeeDao.persist(employee);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void create(String name, String surname, String phone) throws ServerException {
//        System.out.println(next);
//        String[] args = next.split("\\s+");
        Employee employee = new Employee();
//
//        System.out.println(args[0] + " " + args.length);
//        System.out.println(args[0] + " " + args[1] + " " + args[2]);
//        employee.setName(args[0]);
//        employee.setSurname(args[1]);
//        employee.setPhone(args[2]);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPhone(phone);


        create(employee);
    }

    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    public void delete(int id) {employeeDao.delete(id);}

    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

}
