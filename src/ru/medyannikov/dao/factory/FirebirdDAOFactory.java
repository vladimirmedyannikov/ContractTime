package ru.medyannikov.dao.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import ru.medyannikov.dao.*;

import java.io.*;
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
            //String path = new File(DAOFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsoluteFile().getParentFile().toString();
            String path =new File("").getAbsoluteFile().getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(path + "\\conf.ini"));
            //new Alert(Alert.AlertType.NONE, path, ButtonType.OK).show();
            String url = br.readLine();
            String login = br.readLine();
            String password = br.readLine();

            Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
            connection = DriverManager.getConnection(
                    url, login, password);
        }
        catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            throw new DAOException("Connection", e);
        }
        return connection;
    }
}
