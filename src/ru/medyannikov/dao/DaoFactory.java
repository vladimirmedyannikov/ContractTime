package ru.medyannikov.dao;


import ru.medyannikov.dao.factory.FirebirdDAOFactory;

import java.sql.Connection;

/**
 * Created by Vladimir on 02.01.2016.
 */
public abstract class DAOFactory {

    public abstract DepartmentDAO getDepartmentDAO();
    public abstract FirmDAO getFirmDAO();
    public abstract InvestProjectDAO getInvestProjectDAO();
    public abstract UserDAO getUserDAO();

    public abstract Connection getConnection() throws DAOException;

    public static DAOFactory getDAOFactory(){
        return  new FirebirdDAOFactory();
    }
}
