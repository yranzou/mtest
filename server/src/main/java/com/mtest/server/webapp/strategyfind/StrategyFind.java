package com.mtest.server.webapp.strategyfind;

import com.mtest.model.Employee;

public abstract class StrategyFind {
    protected String parameter;

    public StrategyFind(String parameter) {
        this.parameter = parameter;
    }

    abstract public boolean compare(Employee employee);
}
