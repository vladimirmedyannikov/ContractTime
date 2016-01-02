package ru.medyannikov.dao;

import java.util.List;

/**
 * Created by Vladimir on 02.01.2016.
 */
public interface DAO<E> {
    //public E read(E e);
    public E insert(E e);
    public void delete(E e);
    public void update(E e);
    public E getById(int id);
    public List<E> getAll() throws DAOException;
}
