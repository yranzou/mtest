package com.mtest.server;

import com.mtest.dao.EmployeeDao;
import com.mtest.dao.EmployeeDaoHibernate;
import com.mtest.model.Department;
import com.mtest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * Created by yuri on 20.01.18.
 */
@Component
public class EmployeeService {
//    private EmployeeDaoHibernate employeeDao;
    private EmployeeDao employeeDao;

//    private EmployeeDaoHibernate employeeDaoHibernate;

    @Autowired
    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }

    public List<Employee> getCoworkers(Department department) {
        return employeeDao.getCoworkers(department);
    }

    public List<Employee> getSubordinates(Employee leader) {
        return employeeDao.getSubordinates(leader);
    }

    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    public List<Employee> getAllDepartmentsChiefs() {
        return employeeDao.getAllDepartmentsChiefs();
    }

    public Employee get(int id) {return employeeDao.get(id);}

    public Employee getDepartmentChief(int id) {return employeeDao.getDepartmentChief(id);}


//    public List<Employee> search (String str) {
//        return employeeDao.search(str);
//    }

    public List<Employee> search (String searchIn, String searchValue) {
        return employeeDao.search(searchIn, searchValue);
    }

    public void savePhoto(Employee employee, InputStream inputStream) {
        employeeDao.savePhoto(employee, inputStream);
    }



    public void create(Employee employee) {
        employeeDao.persist(employee);
    }

    public void create(String name, String surname, String phone) {
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
