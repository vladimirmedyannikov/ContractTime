package ru.medyannikov.dao;

import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class DepartmentDAO implements DAO<Department> {
    private DAOFactory daoFactory = new FirebirdDAOFactory();
    private static Logger Log = Logger.getLogger(DepartmentDAO.class.getName());
    private static List<Department> departmentList = new ArrayList<>();

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

    public DepartmentDAO() {
        try {
            departmentList = getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Department getById(int id) throws DAOException {

        /*Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select DEPT_ID, DEPT_NAME from Depts where DEPT_ID = ?;";
        Department department = new Department();
        try{
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                department.setIdDepartment(resultSet.getInt(1));
                department.setNameDepartment(resultSet.getString(2));
            }
        }
        catch (Exception e){
            throw new DAOException("Department getById "+ id, e);
        }
        finally {
            try{
                resultSet.close();
                connection.close();
                statement.close();
            }
            catch (SQLException e){
                throw new DAOException("SQL Department getById "+id,e);
            }
        }
        */
        for(Department d: departmentList){
            if (d.getIdDepartment() == id) return d;
        }
        return null;
        //return department;
    }

    @Override
    public List<Department> getAll() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select DEPT_ID, DEPT_NAME from Depts order by DEPT_ID";
        departmentList = new ArrayList<>();
        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                Department dep = new Department();
                dep.setNameDepartment(resultSet.getString(2));
                dep.setIdDepartment(resultSet.getInt(1));
                departmentList.add(dep);
                //System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2));
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
        return departmentList;
    }
}
