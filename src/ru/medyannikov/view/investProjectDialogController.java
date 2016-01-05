package ru.medyannikov.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.medyannikov.dao.DAOException;
import ru.medyannikov.dao.DepartmentDAO;
import ru.medyannikov.dao.UserDAO;
import ru.medyannikov.util.KeyValuePair;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Vladimir on 05.01.2016.
 */
public class investProjectDialogController {

    @FXML
    private TextField nameProject;
    @FXML
    private TextField numberProject;
    @FXML
    private ComboBox<KeyValuePair> departmentBox;
    @FXML
    private ComboBox<KeyValuePair> userBox;

    @FXML
    private DatePicker dateBeginPlan;
    @FXML
    private DatePicker dateEndPlan;
    @FXML
    private DatePicker dateBeginProg;
    @FXML
    private DatePicker dateEndProg;
    @FXML
    private TextArea aboutComment;

    @FXML
    private void initialize() throws DAOException {
        DepartmentDAO dao = new DepartmentDAO();
        departmentBox.setItems(FXCollections.observableList(dao.getDepatmentComboBox()));

        UserDAO userDAO = new UserDAO();
        userBox.setItems(FXCollections.observableList(userDAO.getUserComboBox()));

        dateBeginPlan.setValue(LocalDate.now());
        dateEndPlan.setValue(LocalDate.now().plusMonths(1));

        dateBeginProg.setValue(dateBeginPlan.getValue());
        dateEndProg.setValue(dateEndPlan.getValue());
    }


}
