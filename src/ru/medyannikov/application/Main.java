package ru.medyannikov.application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.medyannikov.dao.DepartmentDAO;
import ru.medyannikov.dao.UserDAO;
import ru.medyannikov.dao.UserDAO2;
import ru.medyannikov.dao.factory.FirebirdDAOFactory;
import ru.medyannikov.model.User;
import ru.medyannikov.model.User2;
import ru.medyannikov.view.SampleOverviewController;

import java.sql.Connection;
import java.util.List;

public class Main extends Application {

    private final ObservableList<User>  listUser = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{

        //initData();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ru/medyannikov/view/sampleView.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        SampleOverviewController controller = loader.getController();
        controller.setApp(this);
        FirebirdDAOFactory daoFactory = new FirebirdDAOFactory();
        Connection connection = daoFactory.getConnection();
        System.out.println(connection.getClientInfo());

       // DepartmentDAO departmentDAO = new DepartmentDAO();
        //departmentDAO.getAll();

        UserDAO userDAO = new UserDAO();
        ObservableList<User> list = FXCollections.observableList(userDAO.getAll());

        /*UserDAO userDAO2 = new UserDAO();
        List<User> list2 = userDAO2.getAll();*/

    }


    public static void main(String[] args) {

        launch(args);
    }

    private void initData(){
        for (int i=0; i < 500; i++){
            listUser.add(new User(i, "Name "+i,"email@" + i +"list.ru"));
        }
    }

    public ObservableList<User> getUsers() {
        return listUser;
    }
}
