package com.mtest.server.common;

import com.mtest.dao.EmployeeDao;
import com.mtest.dao.PhoneDao;
import com.mtest.model.Department;
import com.mtest.model.Employee;
import com.mtest.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * Created by yuri on 20.01.18.
 */
@Component
public class PhoneService {
    private PhoneDao phoneDao;

    @Autowired
    public PhoneService() {
        phoneDao = new PhoneDao();
    }

    public Set<Phone> get(int id) {return phoneDao.get(id);}

    public void create(Set<Phone> phones, int id) {
        phoneDao.persist(phones, id);
    }

    public void delete(int id) {phoneDao.delete(id);}

//    public void delete(Employee employee) {
//        employeeDao.delete(employee);
//    }

}
