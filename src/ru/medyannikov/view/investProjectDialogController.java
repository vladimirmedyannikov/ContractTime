package ru.medyannikov.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import ru.medyannikov.dao.DAOException;
import ru.medyannikov.dao.DepartmentDAO;
import ru.medyannikov.dao.InvestProjectDAO;
import ru.medyannikov.dao.UserDAO;
import ru.medyannikov.model.Department;
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

    public InvestProject getInvestProject(){
        return investProject;
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
                try {
                    saveProject();
                } catch (DAOException e) {

                }
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

    private void saveProject() throws DAOException {
        InvestProjectDAO investProjectDAO = new InvestProjectDAO();
        Alert alert = null;
        if(isValidInput()) {
            if (investProject != null) {
                UserDAO userDAO = new UserDAO();
                DepartmentDAO departmentDAO = new DepartmentDAO();
                investProject.setNameProject(nameProject.getText());
                investProject.setNumberProject(numberProject.getText());
                User user = userDAO.getById(Integer.parseInt(userBox.getSelectionModel().getSelectedItem().getKey()));
                investProject.setUser(user);
                investProject.setDepartment(departmentDAO.getById(Integer.parseInt(departmentBox.getSelectionModel().getSelectedItem().getKey())));
                investProject.setDateBegin(new Date().from(
                        Instant.from(
                                dateBeginPlan.getValue().atStartOfDay(ZoneId.systemDefault()))));
                investProject.setDateEnd(new Date().from(
                        Instant.from(
                                dateEndPlan.getValue().atStartOfDay(ZoneId.systemDefault()))));
                investProject.setDateBeginProg(new Date().from(
                        Instant.from(
                                dateBeginProg.getValue().atStartOfDay(ZoneId.systemDefault()))));
                investProject.setDateEndProg(new Date().from(
                        Instant.from(
                                dateEndProg.getValue().atStartOfDay(ZoneId.systemDefault()))));
                investProject.setAboutProject(aboutComment.getText());
                investProjectDAO.update(investProject);
                alert = new Alert(Alert.AlertType.NONE, "Проект обновлен", ButtonType.FINISH);
                alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
                    @Override
                    public void accept(ButtonType buttonType) {
                    nameProject.getScene().getWindow().hide();
                }
                });
            } else {
                investProject = new InvestProject();
                UserDAO userDAO = new UserDAO();
                DepartmentDAO departmentDAO = new DepartmentDAO();

                investProject.setNameProject(nameProject.getText());
                investProject.setUser(userDAO.getById(Integer.parseInt(userBox.getSelectionModel().getSelectedItem().getKey())));
                investProject.setDepartment(departmentDAO.getById(Integer.parseInt(departmentBox.getSelectionModel().getSelectedItem().getKey())));
                investProject.setNumberProject(numberProject.getText());
                investProject.setDateBegin(new Date().from(
                        Instant.from(
                                dateBeginPlan.getValue().atStartOfDay(ZoneId.systemDefault()))
                ));
                investProject.setDateEnd(new Date().from(Instant.from(dateEndPlan.getValue().atStartOfDay(ZoneId.systemDefault()))));
                investProject.setDateBeginProg(investProject.getDateBegin());
                investProject.setDateEndProg(investProject.getDateEnd());

                investProject.setAboutProject(aboutComment.getText());
                investProject = investProjectDAO.insert(investProject);
                if (investProject.getIdProject() != 0) {
                    alert = new Alert(Alert.AlertType.NONE, "Проект успешно создан", ButtonType.FINISH);
                } else {
                    alert = new Alert(Alert.AlertType.NONE, "Проект не был создан", ButtonType.FINISH);
                }
                alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
                    @Override
                    public void accept(ButtonType buttonType) {
                        nameProject.getScene().getWindow().hide();
                    }
                });
            }
        }
    }

    private boolean isValidInput() {
        boolean valid = true;
        Alert alert = null;
        String error = "";
        if (nameProject.getText() == null || nameProject.getText().length() <= 2){
            nameProject.setPromptText("Введите название длиной более 2х символов");
            error +="Введите название длиной более 2х символов\n";
            valid = false;
        }
        if (numberProject.getText() == null || numberProject.getText().length() == 0){
            numberProject.setPromptText("Введите номер проекта");
            error += "Введите номер проекта\n";
            valid = false;
        }
        if (userBox.getSelectionModel().getSelectedIndex() == -1){
            userBox.setPromptText("Выберите ответственного сотрудника за проект");
            error+="Выберите ответственного сотрудника за проект\n";
            valid = false;
        }
        if (departmentBox.getSelectionModel().getSelectedIndex() == -1){
            departmentBox.setPromptText("Выберите подразделение");
            error += "Выберите подразделение\n";
            valid = false;
        }
        if (dateEndPlan.getValue().compareTo(dateBeginPlan.getValue()) < 0){
            dateEndPlan.setPromptText("Дата окончания не должна быть меньше даты начала");
            error += "Дата окончания не должна быть меньше даты начала\n";
            valid = false;
        }
        if (!valid) {
            alert = new Alert(Alert.AlertType.ERROR, "Ошибки:\n"+ error, ButtonType.OK);
            alert.setResizable(false);
            alert.showAndWait();
        }
        return valid;
    }


}
