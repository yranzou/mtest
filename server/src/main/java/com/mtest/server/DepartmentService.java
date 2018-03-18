package com.mtest.server;

import com.mtest.dao.DepartmentDao;
import com.mtest.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yuri on 20.01.18.
 */
@Component
public class DepartmentService{
    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentService() {
        departmentDao = new DepartmentDao();
    }

    public void create(Department department) {
        departmentDao.persist(department);
    }

    public void create(String departmentName) {
        Department department = new Department();
        department.setName(departmentName);
        departmentDao.persist(department);
    }

    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    public List<Department> search (String searchIn, String searchValue) {
        return departmentDao.search(searchIn, searchValue);
    }

    public Department get(int id) {return departmentDao.get(id);}

    public void update(Department department) {
        departmentDao.update(department);
    }

    public void delete(int id) {departmentDao.delete(id);}

    public void delete(Department department) {
        departmentDao.delete(department);
    }
}
