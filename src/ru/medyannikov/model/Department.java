package ru.medyannikov.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class Department {
    private int idDepartment;
    private StringProperty nameDepartment = new SimpleStringProperty();
    private ObjectProperty<Firm> firmDepartment = new SimpleObjectProperty<>();
    private ObjectProperty<Department> parentDepartment = new SimpleObjectProperty<>();

    public Department(){

    }

    public Department(int idDepartment){
        this.idDepartment = idDepartment;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment.get();
    }

    public StringProperty nameDepartmentProperty() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment.set(nameDepartment);
    }

    public Firm getFirmDepartment() {
        return firmDepartment.get();
    }

    public ObjectProperty<Firm> firmDepartmentProperty() {
        return firmDepartment;
    }

    public void setFirmDepartment(Firm firmDepartment) {
        this.firmDepartment.set(firmDepartment);
    }

    public Department getParentDepartment() {
        return parentDepartment.get();
    }

    public ObjectProperty<Department> parentDepartmentProperty() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment.set(parentDepartment);
    }
}
