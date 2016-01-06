package ru.medyannikov.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

/**
 * Created by Vladimir on 03.01.2016.
 */
public class StageProject {
    private int idStage;
    private int idProject;
    private int idParentStage;
    private StringProperty nameStage  = new SimpleStringProperty();
    private ObjectProperty<User> user = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dateBeginPlan = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dateEndPlan = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dateBeginProg = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dateEndProg = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dateBeginUser = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dateEndUser = new SimpleObjectProperty<>();
    private IntegerProperty statusStage = new SimpleIntegerProperty();
    private StringProperty commentUser = new SimpleStringProperty();

    private ObservableList<StageProject> subStage = FXCollections.emptyObservableList();

    public ObservableList<StageProject> getSubStage() {
        return subStage;
    }

    public void setSubStage(ObservableList<StageProject> subStage) {
        this.subStage = subStage;
    }

    public StageProject(){

    }


    public int getIdParentStage() {
        return idParentStage;
    }

    public void setIdParentStage(int idParentStage) {
        this.idParentStage = idParentStage;
    }

    public int getIdStage() {
        return idStage;
    }

    public void setIdStage(int idStage) {
        this.idStage = idStage;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getNameStage() {
        return nameStage.get();
    }

    public StringProperty nameStageProperty() {
        return nameStage;
    }

    public void setNameStage(String nameStage) {
        this.nameStage.set(nameStage);
    }

    public User getUser() {
        return user.get();
    }

    public ObjectProperty<User> userProperty() {
        return user;
    }

    public void setUser(User user) {
        this.user.set(user);
    }

    public Date getDateBeginPlan() {
        return dateBeginPlan.get();
    }

    public ObjectProperty<Date> dateBeginPlanProperty() {
        return dateBeginPlan;
    }

    public void setDateBeginPlan(Date dateBeginPlan) {
        this.dateBeginPlan.set(dateBeginPlan);
    }

    public Date getDateEndPlan() {
        return dateEndPlan.get();
    }

    public ObjectProperty<Date> dateEndPlanProperty() {
        return dateEndPlan;
    }

    public void setDateEndPlan(Date dateEndPlan) {
        this.dateEndPlan.set(dateEndPlan);
    }

    public Date getDateBeginProg() {
        return dateBeginProg.get();
    }

    public ObjectProperty<Date> dateBeginProgProperty() {
        return dateBeginProg;
    }

    public void setDateBeginProg(Date dateBeginProg) {
        this.dateBeginProg.set(dateBeginProg);
    }

    public Date getDateEndProg() {
        return dateEndProg.get();
    }

    public ObjectProperty<Date> dateEndProgProperty() {
        return dateEndProg;
    }

    public void setDateEndProg(Date dateEndProg) {
        this.dateEndProg.set(dateEndProg);
    }

    public Date getDateBeginUser() {
        return dateBeginUser.get();
    }

    public ObjectProperty<Date> dateBeginUserProperty() {
        return dateBeginUser;
    }

    public void setDateBeginUser(Date dateBeginUser) {
        this.dateBeginUser.set(dateBeginUser);
    }

    public Date getDateEndUser() {
        return dateEndUser.get();
    }

    public ObjectProperty<Date> dateEndUserProperty() {
        return dateEndUser;
    }

    public void setDateEndUser(Date dateEndUser) {
        this.dateEndUser.set(dateEndUser);
    }

    public int getStatusStage() {
        return statusStage.get();
    }

    public IntegerProperty statusStageProperty() {
        return statusStage;
    }

    public void setStatusStage(int statusStage) {
        this.statusStage.set(statusStage);
    }

    public String getCommentUser() {
        return commentUser.get();
    }

    public StringProperty commentUserProperty() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser.set(commentUser);
    }
}
