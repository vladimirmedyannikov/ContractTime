package ru.medyannikov.dao;

import java.util.List;

/**
 * Created by Vladimir on 02.01.2016.
 */
public interface DAO<E> {
    //public E read(E e);
    public E insert(E e) throws DAOException;
    public void delete(E e) throws DAOException;
    public void update(E e) throws DAOException;
    public E getById(int id) throws DAOException;
    public List<E> getAll() throws DAOException;
}
