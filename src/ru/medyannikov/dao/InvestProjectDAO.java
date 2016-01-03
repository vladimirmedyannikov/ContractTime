package ru.medyannikov.dao;

import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.InvestProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public InvestProject insert(InvestProject investProject) {
        return null;
    }

    @Override
    public void delete(InvestProject investProject) {

    }

    @Override
    public void update(InvestProject investProject) {

    }

    @Override
    public InvestProject getById(int id) {
        return null;
    }

    @Override
    public List<InvestProject> getAll() throws DAOException{
        List<InvestProject> investProjectList = new ArrayList<>();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        UserDAO userDAO = new UserDAO();
        String sql = "select * from invest_project";
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
                investProject.setDepartment(departmentDAO.getById(resultSet.getInt("id_dept")));
                investProject.setAboutProject(resultSet.getString("about_project"));
                investProject.setDateBegin(resultSet.getDate("date_begin_plan"));
                investProject.setDateEnd(resultSet.getDate("date_end_plan"));
                investProject.setDateBeginProg(resultSet.getDate("date_begin_prog"));
                investProject.setDateEndProg(resultSet.getDate("date_end_prog"));
                investProject.setNumberProject(resultSet.getString("number_project"));
                investProject.setUser(userDAO.getById(resultSet.getInt("id_user")));
                investProject.setNameProject(resultSet.getString("name_project"));
                investProjectList.add(investProject);
            }
        }
        catch (Exception e){
            throw new DAOException("InvestProject getAll",e);
        }
        finally {
            try{
                resultSet.close();
                statement.close();
                connection.close();
            }catch (SQLException e){
                throw new DAOException("InvestProject getAll SQL",e);
            }
        }
        return investProjectList;
    }
}
