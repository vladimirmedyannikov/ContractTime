package ru.medyannikov.application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.medyannikov.model.User;
import ru.medyannikov.view.InvestProjectFormController;

public class Main extends Application {

    private final ObservableList<User>  listUser = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{

        //initData();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ru/medyannikov/view/investProjectForm.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        InvestProjectFormController controller = loader.getController();
        controller.setMainApp();
        /*FirebirdDAOFactory daoFactory = new FirebirdDAOFactory();
        Connection connection = daoFactory.getConnection();
        System.out.println(connection.getClientInfo());*/

       // DepartmentDAO departmentDAO = new DepartmentDAO();
        //departmentDAO.getAll();

        /*UserDAO userDAO = new UserDAO();
        ObservableList<User> list = FXCollections.observableList(userDAO.getAll());

        InvestProjectDAO investProjectDAO = new InvestProjectDAO();
        ObservableList<InvestProject> investProjectList = FXCollections.observableList(investProjectDAO.getAll());*/
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
