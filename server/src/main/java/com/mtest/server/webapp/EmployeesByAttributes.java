package com.mtest.server.webapp;

import com.mtest.dao.EmployeeDao;
import com.mtest.dao.exceptions.DaoException;
import com.mtest.model.Employee;
import com.mtest.server.webapp.strategyfind.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeesByAttributes {

    public List<Employee> getByAttributes(String[] parameters) throws DaoException {
        List<StrategyFind> strategy = getStrategyFind(parameters);
        if (strategy.size() > 0) {
            // todo dao shoul not be created every time when method called
            EmployeeDao dao = new EmployeeDao();
            List<Employee> allEmployee = dao.getAll();
            List<Employee> employees = getEmployeesByAttribute(allEmployee, strategy);
            return employees;
        }
        // todo работу с базой данных делегировать
        // todo если все параметры пустые - вернуть всех сотрудников
        return Collections.emptyList();
    }

    private List<StrategyFind> getStrategyFind(String[] parameters) {
        List<StrategyFind> strategy = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            setParameter(parameters[i], i+1, strategy);
        }
        return strategy;
    }

    private List<Employee> getEmployeesByAttribute(List<Employee> allEmployee, List<StrategyFind> strategy) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee: allEmployee) {
            if (isContain(employee, strategy)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    private boolean isContain(Employee employee, List<StrategyFind> strategy) {
        for (StrategyFind find: strategy) {
            if (!find.compare(employee)) {
                return false;
            }
        }
        return true;
    }

    private void setParameter(String parameter, int n, List<StrategyFind> strategy) {
        if (parameter != null && !parameter.equals("")) {
            StrategyFind strategyFind;
            // todo remove long if
//            if (n==1) {
//                strategyFind = new FindByFirstName(parameter);
//            } else if (n==2) {
                strategyFind = new FindBySecondName(parameter);
//            } else if (n==3) {
//                strategyFind = new FindByMiddleName(parameter);
//            } else if (n==4) {
//                strategyFind = new FindByDepartment(parameter);
//            } else if (n==5) {
//                strategyFind = new FindByDateBirth(parameter);
//            } else if (n==6) {
//                strategyFind = new FindByLeader(parameter);
//            } else if (n==7) {
//                strategyFind = new FindByAddressHome(parameter);
//            } else if (n==8) {
//                strategyFind = new FindByAddressWork(parameter);
//            } else {
//                return;
//            }
            strategy.add(strategyFind);
        }
    }
}
