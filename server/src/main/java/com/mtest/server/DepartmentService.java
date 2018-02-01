package com.mtest.server;

import com.mtest.dao.DepartmentDao;
import com.mtest.model.Department;

/**
 * Created by yuri on 20.01.18.
 */
public class DepartmentService{
    private DepartmentDao departmentDao;
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


    public void update(Department department) {
        departmentDao.update(department);
    }


    public void delete(Department department) {
        departmentDao.delete(department);
    }
}
