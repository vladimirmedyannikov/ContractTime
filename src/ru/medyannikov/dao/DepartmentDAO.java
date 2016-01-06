package ru.medyannikov.dao;

import javafx.scene.control.ChoiceBox;
import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.Department;
import ru.medyannikov.util.KeyValuePair;

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

    }

    public List<KeyValuePair> getDepatmentComboBox() throws DAOException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "Select dept_id, name_firm || ' - ' || dept_name as dept_name from depts " +
                "left join firm on firm.id_firm = depts.firm_id order by depts.firm_id, dept_name";
        List<KeyValuePair> keyValuePairs = new ArrayList<>();
        try{
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                KeyValuePair keyValuePair = new KeyValuePair(resultSet.getString("dept_id"), resultSet.getString("dept_name"));
                keyValuePairs.add(keyValuePair);
            }
        }catch (Exception e)
        {
            throw new DAOException("getDepartmentComboBox", e);
        }
        finally {
            try{
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            }catch (SQLException e){
                Log.info("SQLException finally block");
            }
        }
    return keyValuePairs;
    }

    @Override
    public Department getById(int id) throws DAOException {
        Connection connection = null;
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
                department.setIdDepartment(resultSet.getInt("dept_id"));
                department.setNameDepartment(resultSet.getString("dept_name"));
            }
        }
        catch (Exception e){
            throw new DAOException("Department getById "+ id, e);
        }
        finally {
            try{
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e){
                Log.info("GetById Department SQLException finally block");
            }
        }
        return department;
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
                dep.setNameDepartment(resultSet.getString("dept_name"));
                dep.setIdDepartment(resultSet.getInt("dept_id"));
                departmentList.add(dep);
            }
        }
        catch (Exception e){
            throw new DAOException("getListDepartment", e);
        }
        finally {
            try {
                if (resultSet != null)resultSet.close();
                if (statement != null)statement.close();
                if (connection != null)connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return departmentList;
    }
}
