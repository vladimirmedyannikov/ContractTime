package ru.medyannikov.dao.factory;

import ru.medyannikov.dao.*;

import java.sql.Connection;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class FirebirdDAOFactory extends DAOFactory {
    @Override
    public DepartmentDAO getDepartmentDAO() {
        return null;
    }

    @Override
    public FirmDAO getFirmDAO() {
        return null;
    }

    @Override
    public InvestProjectDAO getInvestProjectDAO() {
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        return null;
    }

    @Override
    public Connection getConnection() throws DAOException {
        return null;
    }
}
