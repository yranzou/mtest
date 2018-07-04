package com.mtest.server.common;

import com.mtest.dao.DepartmentDao;
import com.mtest.dao.exceptions.DaoException;
import com.mtest.model.Department;
import com.mtest.server.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yuri on 20.01.18.
 */
@Component
public class DepartmentService{
    @Autowired
    private DepartmentDao departmentDao;

    public void create(Department department) {
        departmentDao.persist(department);
    }

    public void create(String departmentName) {
        Department department = new Department();
        department.setName(departmentName);
        departmentDao.persist(department);
    }

    public List<Department> getAll() throws ServerException {
        try {
            return departmentDao.getAll();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Department> search (String searchIn, String searchValue) {
        return departmentDao.search(searchIn, searchValue);
    }

    public Department get(int id) throws ServerException {
        try {
            return departmentDao.get(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void update(Department department) {
        departmentDao.update(department);
    }

    public void delete(int id) {departmentDao.delete(id);}

    public void delete(Department department) {
        departmentDao.delete(department);
    }
}
