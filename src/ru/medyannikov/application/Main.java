package ru.medyannikov.application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.medyannikov.model.User;
import ru.medyannikov.view.SampleOverviewController;

public class Main extends Application {

    private final ObservableList<User>  listUser = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{

        initData();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ru/medyannikov/view/sampleView.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        SampleOverviewController controller = loader.getController();
        controller.setApp(this);

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
