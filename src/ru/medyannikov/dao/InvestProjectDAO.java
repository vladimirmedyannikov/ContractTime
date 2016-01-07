package ru.medyannikov.dao;

import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.Department;
import ru.medyannikov.model.InvestProject;
import ru.medyannikov.model.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class InvestProjectDAO implements DAO<InvestProject> {
    private DAOFactory daoFactory = new FirebirdDAOFactory();
    private Logger Log = Logger.getLogger(InvestProjectDAO.class.getName());

    @Override
    public InvestProject insert(InvestProject investProject) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "execute procedure insert_invest_project (?, ?, ?, ?, ?, ?, ?);";
        try
        {
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,investProject.getNameProject());
            statement.setString(2, investProject.getNumberProject());
            statement.setInt(3, investProject.getDepartment().getIdDepartment());
            statement.setInt(4, investProject.getUser().getId());
            statement.setDate(5, new java.sql.Date(investProject.getDateBegin().getTime()));
            statement.setDate(6, new java.sql.Date(investProject.getDateEnd().getTime()));
            statement.setString(7, investProject.getAboutProject());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            if (resultSet != null){
                investProject.setIdProject(resultSet.getInt("id_project"));
            }

        }catch (Exception e){
            throw new DAOException("Insert Invest Project", e);
        }
        finally {
            try{
                if (resultSet != null) resultSet.close();
                if (statement != null)statement.close();
                if (connection != null)connection.close();
            }catch (SQLException e){
                //throw new DAOException("InvestProject getAll SQL",e);
            }
        }
        return investProject;
    }

    @Override
    public void delete(InvestProject investProject) {

    }

    @Override
    public void update(InvestProject investProject) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "update invest_project set name_project = ?, number_project = ?, id_dept = ?, id_user =?," +
                "date_begin_plan = ?, date_end_plan =?, date_begin_prog =?, date_end_prog =?, about_project =? " +
                " where id_project = ?;";
        try
        {
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,investProject.getNameProject());
            statement.setString(2, investProject.getNumberProject());
            statement.setInt(3, investProject.getDepartment().getIdDepartment());
            statement.setInt(4, investProject.getUser().getId());
            statement.setDate(5, new java.sql.Date(investProject.getDateBegin().getTime()));
            statement.setDate(6, new java.sql.Date(investProject.getDateEnd().getTime()));
            statement.setDate(7, new java.sql.Date(investProject.getDateBeginProg().getTime()));
            statement.setDate(8, new java.sql.Date(investProject.getDateEndProg().getTime()));
            statement.setString(9, investProject.getAboutProject());
            statement.setInt(10, investProject.getIdProject());
            statement.execute();
            /*resultSet = statement.getGeneratedKeys();
            resultSet.next();*/

        }catch (Exception e){
            throw new DAOException("Insert Invest Project", e);
        }
        finally {
            try{
                if (resultSet != null) resultSet.close();
                if (statement != null)statement.close();
                if (connection != null)connection.close();
            }catch (SQLException e){
                //throw new DAOException("InvestProject getAll SQL",e);
            }
        }

    }

    @Override
    public InvestProject getById(int id) {
        return null;
    }

    @Override
    public List<InvestProject> getAll() throws DAOException{
        List<InvestProject> investProjectList = new ArrayList<>();
        //DepartmentDAO departmentDAO = new DepartmentDAO();
        //UserDAO userDAO = new UserDAO();
        String sql = "select invest_project.*, l_name, f_name, p_name, dept_name from invest_project " +
                "left join depts on depts.dept_id = invest_project.id_dept " +
                "left join user_info u on u.id_user = invest_project.id_user";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try
        {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
            while (resultSet.next()){
                InvestProject investProject = new InvestProject();
                investProject.setIdProject(resultSet.getInt("id_project"));

                Department department = new Department();
                department.setNameDepartment(resultSet.getString("dept_name"));
                department.setIdDepartment(resultSet.getInt("id_dept"));
                investProject.setDepartment(department);
                //investProject.setDepartment(departmentDAO.getById(resultSet.getInt("id_dept")));

                investProject.setAboutProject(resultSet.getString("about_project"));
                investProject.setDateBegin(resultSet.getDate("date_begin_plan"));
                investProject.setDateEnd(resultSet.getDate("date_end_plan"));
                investProject.setDateBeginProg(resultSet.getDate("date_begin_prog"));
                investProject.setDateEndProg(resultSet.getDate("date_end_prog"));
                investProject.setNumberProject(resultSet.getString("number_project"));

                User user = new User();
                user.setFirstName(resultSet.getString("f_name"));
                user.setSecondName(resultSet.getString("l_name"));
                user.setThirdName(resultSet.getString("p_name"));
                user.setId(resultSet.getInt("id_user"));
                investProject.setUser(user);
                //investProject.setUser(userDAO.getById(resultSet.getInt("id_user")));
                investProject.setNameProject(resultSet.getString("name_project"));
                investProjectList.add(investProject);
            }
        }
        catch (Exception e){
            throw new DAOException("InvestProject getAll",e);
        }
        finally {
            try{
                if (resultSet != null) resultSet.close();
                if (statement != null)statement.close();
                if (connection != null)connection.close();
            }catch (SQLException e){
                //throw new DAOException("InvestProject getAll SQL",e);
            }
        }
        return investProjectList;
    }
}
