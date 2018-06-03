package com.mtest.server.webapp.strategyfind;

import com.mtest.model.Employee;

public class FindBySecondName extends StrategyFind {
    public FindBySecondName(String parameter) {
        super(parameter);
    }

    @Override
    public boolean compare(Employee employee) {
        if (employee != null)
        {
            String name = employee.getSurname();//getSecondName
            if (name != null && parameter != null && name.contains(parameter)) {
                return true;
            }
        }
        return false;
    }
}
