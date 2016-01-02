package ru.medyannikov.dao;

import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
        try {
            // Создаём класс, с помощью которого будут выполняться
            // SQL запросы.
            connection = daoFactory.getConnection();
            Statement stmt = connection.createStatement();

            //Выполняем SQL запрос.
            ResultSet rs = stmt.executeQuery("select * from Depts");

            // Смотрим количество колонок в результате SQL запроса.
            int nColumnsCount = rs.getMetaData().getColumnCount();

            // Выводим результат.
            while(rs.next())
            {
                System.out.println();
                for (int n=1;n < nColumnsCount+1;n++)
                {
                    Object obj = rs.getObject(n);
                    if (obj!=null)
                    {
                        System.out.print(obj+" | ");
                    }
                }
            }

            // Освобождаем ресурсы.
            stmt.close();

            connection.close();
        }
        catch (Exception e){
            throw new DAOException("getListDepartment", e);
        }
        return null;
    }
}
