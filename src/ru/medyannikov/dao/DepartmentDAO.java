package ru.medyannikov.dao;

import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class DepartmentDAO implements DAO<Department> {
    private DAOFactory daoFactory = new FirebirdDAOFactory();
    private static Logger Log = Logger.getLogger(DepartmentDAO.class.getName());

    @Override
    public Department insert(Department department) {
        return null;
    }

    @Override
    public void delete(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public Department getById(int id) {
        return null;
    }

    @Override
    public List<Department> getAll() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = "select DEPT_ID, DEPT_NAME from Depts";
        List<Department> departmentList = new ArrayList<>();
        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                Department dep = new Department();
                dep.setNameDepartment(resultSet.getString(1));
                dep.setIdDepartment(resultSet.getInt(0));
                departmentList.add(dep);
            }
        }
        catch (Exception e){
            throw new DAOException("getListDepartment", e);
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
