package ru.medyannikov.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ru.medyannikov.dao.DAOException;
import ru.medyannikov.dao.StageProjectDAO;
import ru.medyannikov.dao.UserDAO;
import ru.medyannikov.model.StageProject;
import ru.medyannikov.model.User;
import ru.medyannikov.util.ClassConverter;
import ru.medyannikov.util.KeyValuePair;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Consumer;

/**
 * Created by Vladimir on 10.01.2016.
 */
public class StageProjectDialogController {
    @FXML
    private TextField nameStage;
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
    private DatePicker dateBeginUser;
    @FXML
    private DatePicker dateEndUser;
    @FXML
    private TextArea stageAbout;

    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;

    private StageProject stageProject;
    private boolean addSubStage;
    private int idProject;

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public boolean isAddSubStage() {
        return addSubStage;
    }

    public void setAddSubStage(boolean addSubStage) {
        this.addSubStage = addSubStage;
    }

    public StageProject getStageProject() {
        return stageProject;
    }

    public void setStageProject(StageProject stageProject) {
        if (stageProject != null) {
            this.stageProject = stageProject;
            nameStage.setText(stageProject.getNameStage());
            userBox.setValue(new KeyValuePair(String.valueOf(stageProject.getUser().getId()),stageProject.getUser().getFullName()));
            stageAbout.setText(stageProject.getCommentUser());
            if (stageProject.getDateBeginPlan() != null) dateBeginPlan.setValue(ClassConverter.convertToLocalDate(stageProject.getDateBeginPlan()));
            if (stageProject.getDateEndPlan() != null) dateEndPlan.setValue(ClassConverter.convertToLocalDate(stageProject.getDateEndPlan()));
            if (stageProject.getDateBeginProg() != null) dateBeginProg.setValue(ClassConverter.convertToLocalDate(stageProject.getDateBeginProg()));
            if (stageProject.getDateEndProg() != null) dateEndProg.setValue(ClassConverter.convertToLocalDate(stageProject.getDateEndProg()));
            if (stageProject.getDateBeginUser() != null) dateBeginUser.setValue(ClassConverter.convertToLocalDate(stageProject.getDateBeginUser()));
            if (stageProject.getDateEndUser() != null) dateEndUser.setValue(ClassConverter.convertToLocalDate(stageProject.getDateEndUser()));
        }

    }

    @FXML
    private void initialize() throws DAOException{
        dateBeginPlan.setValue(LocalDate.now());
        dateEndPlan.setValue(LocalDate.now());
        dateBeginProg.setValue(LocalDate.now());
        dateEndProg.setValue(LocalDate.now());
        dateBeginUser.setValue(LocalDate.now());
        dateEndUser.setValue(LocalDate.now());
        UserDAO userDAO = new UserDAO();
        userBox.setItems(FXCollections.observableList(userDAO.getUserComboBox()));
    }

    public void cancelButton(){
        Scene scene = nameStage.getScene();
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

    @FXML
    public void saveButton() throws DAOException
    {
        StageProjectDAO stageProjectDAO = new StageProjectDAO();
        Alert alert = null;
        if (stageProject != null && !addSubStage){
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getById(Integer.parseInt(userBox.getSelectionModel().getSelectedItem().getKey()));
            stageProject.setNameStage(nameStage.getText());
            stageProject.setUser(user);
            stageProject.setDateBeginPlan(ClassConverter.convertToDate(dateBeginPlan.getValue().atStartOfDay(ZoneId.systemDefault())));
            stageProject.setDateEndPlan(ClassConverter.convertToDate(dateEndPlan.getValue().atStartOfDay(ZoneId.systemDefault())));
            stageProjectDAO.update(stageProject);
        }
        else if (stageProject == null){
            if (idProject != 0) {
                stageProject = new StageProject();
                stageProject.setIdProject(idProject);
                UserDAO userDAO = new UserDAO();
                User user = userDAO.getById(Integer.parseInt(userBox.getSelectionModel().getSelectedItem().getKey()));
                stageProject.setNameStage(nameStage.getText());
                stageProject.setUser(user);
                stageProject.setDateBeginPlan(ClassConverter.convertToDate(dateBeginPlan.getValue().atStartOfDay(ZoneId.systemDefault())));
                stageProject.setDateEndPlan(ClassConverter.convertToDate(dateEndPlan.getValue().atStartOfDay(ZoneId.systemDefault())));
                stageProject = stageProjectDAO.insert(stageProject);
                if (stageProject.getIdStage() != 0) {
                    alert = new Alert(Alert.AlertType.NONE, "Этап успешно создан", ButtonType.FINISH);
                } else {
                    alert = new Alert(Alert.AlertType.NONE, "Этап не был создан", ButtonType.FINISH);
                }
                alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
                    @Override
                    public void accept(ButtonType buttonType) {
                        nameStage.getScene().getWindow().hide();
                    }
                });
            }
        }
        else if (addSubStage == true){
            StageProject newStageProject = new StageProject();
            newStageProject.setIdProject(stageProject.getIdProject());
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getById(Integer.parseInt(userBox.getSelectionModel().getSelectedItem().getKey()));
            newStageProject.setNameStage(nameStage.getText());
            newStageProject.setIdParentStage(stageProject.getIdStage());
            newStageProject.setUser(user);
            newStageProject.setDateBeginPlan(ClassConverter.convertToDate(dateBeginPlan.getValue().atStartOfDay(ZoneId.systemDefault())));
            newStageProject.setDateEndPlan(ClassConverter.convertToDate(dateEndPlan.getValue().atStartOfDay(ZoneId.systemDefault())));
            stageProject = stageProjectDAO.insert(newStageProject);
            if (stageProject.getIdStage() != 0) {
                alert = new Alert(Alert.AlertType.NONE, "Этап успешно создан", ButtonType.FINISH);
            } else {
                alert = new Alert(Alert.AlertType.NONE, "Этап не был создан", ButtonType.FINISH);
            }
            alert.showAndWait().ifPresent(new Consumer<ButtonType>() {
                @Override
                public void accept(ButtonType buttonType) {
                    nameStage.getScene().getWindow().hide();
                }
            });
        }
    }

}
