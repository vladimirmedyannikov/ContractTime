package ru.medyannikov.dao;

import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.Department;
import ru.medyannikov.model.User;

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
public class UserDAO implements DAO<User> {
    private DAOFactory daoFactory = new FirebirdDAOFactory();
    private static Logger Log = Logger.getLogger(UserDAO.class.getName());

    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        DepartmentDAO departmentDAO = new DepartmentDAO();
        String sql = "Select id_user, id_dept, date_in, date_out, l_name, f_name, p_name, login, e_mail, sent_message, sent_date from user_info";
        List<User> userList = new ArrayList<User>();

        try{
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFullName(resultSet.getString(5));
                Department department = departmentDAO.getById(resultSet.getInt(2));
                user.setDepartment(department);
                userList.add(user);
            }

        }
        catch (Exception e){
            throw new DAOException("getAll User", e);
        }
        finally {
            try{
                resultSet.close();
                connection.close();
                statement.close();
            }
            catch (SQLException e){
                throw new DAOException("User getAll",e);
            }
        }
        return userList;
    }
}
