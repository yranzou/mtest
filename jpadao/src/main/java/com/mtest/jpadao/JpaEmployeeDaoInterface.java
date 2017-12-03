package com.mtest.jpadao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yuri on 03.12.17.
 */
public interface JpaEmployeeDaoInterface<T, Id extends Serializable> {
    public void persist(T entity);

    public void update(T entity);

    public T findById(Id id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();
}
