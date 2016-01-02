package ru.medyannikov.view;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.medyannikov.application.Main;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import ru.medyannikov.dao.DAOException;
import ru.medyannikov.dao.UserDAO;
import ru.medyannikov.model.User;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class SampleOverviewController {

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, String> nameUserColumn;

    @FXML
    private TableColumn<User, String> emailUserColumn;

    @FXML
    private TableView<User> userTableAdv;

    @FXML
    private TableColumn<User, String> nameUserColumnAdv;

    @FXML
    private TableColumn<User, String> emailUserColumnAdv;


    private Main mainApp;

    public SampleOverviewController(){

    }

    @FXML
    private void initialize(){
        nameUserColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userStringCellDataFeatures) {
                return userStringCellDataFeatures.getValue().fullNameProperty();
            }
        });


        emailUserColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userStringCellDataFeatures) {
                return userStringCellDataFeatures.getValue().emailProperty();
            }
        });

        nameUserColumnAdv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userStringCellDataFeatures) {
                return userStringCellDataFeatures.getValue().fullNameProperty();
            }
        });

        emailUserColumnAdv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> userStringCellDataFeatures) {
                return userStringCellDataFeatures.getValue().emailProperty();
            }
        });

        userTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User user, User t1) {
                userTableAdv.setItems(t1.getListUser());
                //System.out.println(t1.getFullName());
            }
        });
    }

    public void setApp(Main mainApp){
        this.mainApp = mainApp;
        this.mainApp.getUsers();
        UserDAO userDAO = new UserDAO();
        //ObservableList<User> list = FXCollections.observableList(userDAO.getAll());
        try {
            userTableView.setItems(FXCollections.observableArrayList(userDAO.getAll()));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
