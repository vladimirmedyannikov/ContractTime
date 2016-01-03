package ru.medyannikov.dao.factory;

import ru.medyannikov.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;

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
        Connection connection = null;
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:firebirdsql://192.168.0.105:3050/ContractTime?lc_ctype=WIN1251",
                    "SYSDBA", "masterkey");
        }
        catch (Exception e){
            throw new DAOException("Connection", e);
        }
        return connection;
    }
}
