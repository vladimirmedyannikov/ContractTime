package ru.medyannikov.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ru.medyannikov.dao.DAOException;
import ru.medyannikov.dao.DepartmentDAO;
import ru.medyannikov.dao.InvestProjectDAO;
import ru.medyannikov.dao.UserDAO;
import ru.medyannikov.model.InvestProject;
import ru.medyannikov.model.User;
import ru.medyannikov.util.ClassConverter;
import ru.medyannikov.util.KeyValuePair;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Consumer;

/**
 * Created by Vladimir on 05.01.2016.
 */
public class InvestProjectDialogController {

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

    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonCancel;

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

        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveProject();
            }
        });

        buttonCancel.setOnAction(event -> cancelProject());
    }

    private void cancelProject() {
        Scene scene = nameProject.getScene();
        Alert alert = new Alert(Alert.AlertType.NONE,"Вы действитеьно хотите отметить изменения?",ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType buttonType) {
                if (buttonType == ButtonType.OK){
                    scene.getWindow().hide();
                }
            }
        });

    }

    private void saveProject() {
        InvestProjectDAO dao = new InvestProjectDAO();

        if (investProject != null){
            dao.update(investProject);
        }
        else
        {
            investProject = new InvestProject();
            investProject.setNameProject(nameProject.getText());
            investProject.setUser(new User());
            investProject.setNumberProject(numberProject.getText());
            investProject.setDateBegin(new Date().from(
                    Instant.from(
                            dateBeginPlan.getValue().atStartOfDay(ZoneId.systemDefault()))
            ));
            investProject.setDateEnd(new Date().from(Instant.from(dateEndPlan.getValue().atStartOfDay(ZoneId.systemDefault()))));
            dao.insert(investProject);
        }
    }


}
