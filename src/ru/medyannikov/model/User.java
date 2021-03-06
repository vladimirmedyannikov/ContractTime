package ru.medyannikov.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class User {

    private int id;

    private StringProperty fullName = new SimpleStringProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty secondName = new SimpleStringProperty();
    private StringProperty thirdName = new SimpleStringProperty();
    private ObjectProperty<Date> dateBirthday = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dateIn = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dateOut = new SimpleObjectProperty<>();
    private StringProperty login = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private ObjectProperty<Department> department  = new SimpleObjectProperty<>();
    private StringProperty email = new SimpleStringProperty();

    public User() {

    }
    public User(int idUser){
        this.id = idUser;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getThirdName() {
        return thirdName.get();
    }

    public StringProperty thirdNameProperty() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName.set(thirdName);
    }

    public Date getDateBirthday() {
        return dateBirthday.get();
    }

    public ObjectProperty<Date> dateBirthdayProperty() {
        return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday.set(dateBirthday);
    }

    public Date getDateIn() {
        return dateIn.get();
    }

    public ObjectProperty<Date> dateInProperty() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn.set(dateIn);
    }

    public Date getDateOut() {
        return dateOut.get();
    }

    public ObjectProperty<Date> dateOutProperty() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut.set(dateOut);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
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

    private final ObservableList<User> listUser = FXCollections.observableArrayList();;

    public ObservableList<User> getListUser() {
        return listUser;
    }

    public User(int id, String name, String email){
        this.id = id;
        this.fullName = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        if (name.contains("ame")) initData();
    }

    private void initData(){
        for (int i=0; i< 350; i++){
            listUser.add(new User(i,String.valueOf( this.getId()),"emailka"));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return getSecondName() + " " + getFirstName() + " " + getThirdName();
    }

    public StringProperty fullNameProperty() {
        return new SimpleStringProperty(getSecondName() + " " + getFirstName() + " " + getThirdName());
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
