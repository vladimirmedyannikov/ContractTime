package ru.medyannikov.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class InvestProject {
    private int idProject;
    private StringProperty nameProject;
    private StringProperty numberProject;
    private ObjectProperty<Department> department;
    private ObjectProperty<User> user;
    private ObjectProperty<Date> dateBegin;
    private ObjectProperty<Date> dateEnd;
    private ObjectProperty<Date> dateBeginProg;
    private ObjectProperty<Date> dateEndProg;
    private StringProperty aboutProject;

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject.get();
    }

    public StringProperty nameProjectProperty() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject.set(nameProject);
    }

    public String getNumberProject() {
        return numberProject.get();
    }

    public StringProperty numberProjectProperty() {
        return numberProject;
    }

    public void setNumberProject(String numberProject) {
        this.numberProject.set(numberProject);
    }

    public Department getDepartment() {
        return department.get();
    }

    public ObjectProperty<Department> departmentProperty() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department.set(department);
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

    public Date getDateBegin() {
        return dateBegin.get();
    }

    public ObjectProperty<Date> dateBeginProperty() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin.set(dateBegin);
    }

    public Date getDateEnd() {
        return dateEnd.get();
    }

    public ObjectProperty<Date> dateEndProperty() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd.set(dateEnd);
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

    public String getAboutProject() {
        return aboutProject.get();
    }

    public StringProperty aboutProjectProperty() {
        return aboutProject;
    }

    public void setAboutProject(String aboutProject) {
        this.aboutProject.set(aboutProject);
    }
}
