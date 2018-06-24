package com.mtest.server.common;

import com.mtest.dao.EmployeeDao;
import com.mtest.dao.PhoneDao;
import com.mtest.dao.exceptions.DaoException;
import com.mtest.model.Department;
import com.mtest.model.Employee;
import com.mtest.model.Phone;
import com.mtest.server.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * Created by yuri on 20.01.18.
 */
@Service
public class PhoneService {
    @Autowired
    private PhoneDao phoneDao;

//    @Autowired
//    public PhoneService() {
//        phoneDao = new PhoneDao();
//    }

    public Phone get(int id) throws ServerException {
        try {
            return phoneDao.get(id);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void create(Set<Phone> phones, int id) {
        phoneDao.persist(phones, id);
    }

    public void delete(int id) {phoneDao.delete(id);}

//    public void delete(Employee employee) {
//        employeeDao.delete(employee);
//    }

}
