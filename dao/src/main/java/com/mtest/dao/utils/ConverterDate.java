package com.mtest.dao.utils;

import com.mtest.dao.exceptions.DaoException;

public class ConverterDate {
    public static java.sql.Date toSqlDate(java.util.Date date) throws DaoException {
        if (date == null) {
            throw new DaoException("Date is null " + date);
        }
        return new java.sql.Date(date.getTime());
    }
    public static java.util.Date toUtilDate(java.sql.Date date) throws DaoException {
        if (date == null) {
            throw new DaoException("Date is null " + date);
        }
        return new java.util.Date(date.getTime());
    }
    public static java.util.Date toUtilDate(String date) throws DaoException {
        if (date == null) {
            throw new DaoException("Date is null " + date);
        }
        return toUtilDate(toSqlDate(date));
    }
    public static java.sql.Date toSqlDate(String date) throws DaoException {
        if (date == null) {
            throw new DaoException("Date is null " + date);
        }
        return java.sql.Date.valueOf(date);
    }

}
