package ru.medyannikov.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ru.medyannikov.dao.DAOException;
import ru.medyannikov.dao.DepartmentDAO;
import ru.medyannikov.dao.UserDAO;
import ru.medyannikov.model.InvestProject;
import ru.medyannikov.util.ClassConverter;
import ru.medyannikov.util.KeyValuePair;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private InvestProject investProject;

    public void setInvestProject(InvestProject investProject){
        this.investProject = investProject;
        if (investProject != null) {
            nameProject.setText(investProject.getNameProject());
            numberProject.setText(investProject.getNumberProject());
            aboutComment.setText(investProject.getAboutProject());
            dateBeginPlan.setValue(ClassConverter.convertToLocalDate(investProject.getDateBegin()));
            dateEndPlan.setValue(ClassConverter.convertToLocalDate(investProject.getDateEnd()));
            dateBeginProg.setValue(ClassConverter.convertToLocalDate(investProject.getDateBeginProg()));
            dateEndProg.setValue(ClassConverter.convertToLocalDate(investProject.getDateEndProg()));
            userBox.setValue(new KeyValuePair(String.valueOf(investProject.getUser().getId()),investProject.getUser().getFullName()));
            departmentBox.setValue(new KeyValuePair(String.valueOf(investProject.getDepartment().getIdDepartment()),""));
        }

    }

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
