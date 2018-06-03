package com.mtest.dao;

import com.mtest.dao.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements GenericDao<T> {
    public abstract T createFromResultSet(ResultSet rs) throws DaoException, SQLException;
}
