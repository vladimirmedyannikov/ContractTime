package ru.medyannikov.dao;

import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.StageProject;
import ru.medyannikov.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Vladimir on 03.01.2016.
 */
public class StageProjectDAO implements DAO<StageProject> {
    private DAOFactory daoFactory = new FirebirdDAOFactory();
    private static Logger Log = Logger.getLogger(StageProjectDAO.class.getName());

    @Override
    public StageProject insert(StageProject stageProject) {
        return null;
    }

    @Override
    public void delete(StageProject stageProject) {

    }

    @Override
    public void update(StageProject stageProject) {

    }

    @Override
    public StageProject getById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<StageProject> getAll() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select id_stage, id_project, name_stage, id_user, l_name, f_name, p_name, date_begin_plan, " +
                "date_end_plan, date_begin_prog, date_end_prog, date_begin_user, date_end_user, " +
                "status_stage, comment_user from stage_project " +
                "left join user_info u on u.user_id = stage_project.id_user;";
        List<StageProject> stageProjectList = new ArrayList<>();
        try{
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                StageProject stageProject = new StageProject();
                stageProject.setIdStage(resultSet.getInt("id_stage"));
                stageProject.setIdProject(resultSet.getInt("id_project"));
                stageProject.setNameStage(resultSet.getString("name_stage"));

                User user = new User();
                user.setId(resultSet.getInt("id_user"));
                user.setFirstName(resultSet.getString("f_name"));
                user.setSecondName(resultSet.getString("l_name"));
                user.setThirdName(resultSet.getString("p_name"));

                stageProject.setUser(user);
                stageProject.setDateBeginPlan(resultSet.getDate("date_begin_plan"));
                stageProject.setDateEndPlan(resultSet.getDate("date_end_plan"));
                stageProject.setDateBeginProg(resultSet.getDate("date_begin_prog"));
                stageProject.setDateEndProg(resultSet.getDate("date_end_prog"));
                stageProject.setDateBeginUser(resultSet.getDate("date_begin_user"));
                stageProject.setDateEndUser(resultSet.getDate("date_end_user"));
                stageProject.setStatusStage(resultSet.getInt("status_stage"));
                stageProject.setCommentUser(resultSet.getString("comment_user"));

                stageProjectList.add(stageProject);

            }
        }catch (Exception e){
            throw new DAOException("StageProject getAll",e);
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            }catch (SQLException e){
                throw new DAOException("SQL StageProject",e);
            }
        }
        return stageProjectList;
    }
}
