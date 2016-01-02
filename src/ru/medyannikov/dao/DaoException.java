package ru.medyannikov.dao;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class DAOException extends Exception {
    public DAOException(Exception e) {
        super("DAO", e);
    }

    public DAOException(String name, Exception e) {
        super(name, e);
    }
}
