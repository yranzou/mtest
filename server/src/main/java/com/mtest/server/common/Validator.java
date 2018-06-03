package com.mtest.server.common;

import com.mtest.dao.DepartmentDao;
import com.mtest.dao.EmployeeDao;
import com.mtest.dao.exceptions.DaoException;
import com.mtest.dao.utils.ConverterDate;
import com.mtest.model.Employee;
import com.mtest.server.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Validator {
    // todo service layer must contain more specific validation
    // like leader must work more than 3 month
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    public static Employee validateEmployee(List<String> parameters) throws ServerException {
        if (parameters.size() == 0 || parameters.size()>9 || parameters.size() <6) {
            throw new ServerException("Fatal error on server side during validation");
        }
        Employee employee = new Employee();
        String firstName = parameters.get(0);
        if (firstName == null) {
            throw new ServerException("firstName null");
        }
//        employee.setFirstName();
        try {
            java.util.Date date = ConverterDate.toUtilDate(parameters.get(3));
            employee.setBirthday(date);
        } catch (DaoException e) {
            throw new ServerException(e.getMessage());
        }
        return employee;
    }

}
