package com.mtest.dao;

import com.mtest.dao.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

interface GenericDao<T> {
    T createFromResultSet(ResultSet rs) throws DaoException, SQLException;

    public void add(T obj);

    public void update(T obj);

    public void delete(int id);

    public void get(int id);


}
