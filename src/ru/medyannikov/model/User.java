package ru.medyannikov.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class User {

    private int id;
    private StringProperty fullName;
    private StringProperty email;

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
        for (int i=0; i< 2; i++){
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
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
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
