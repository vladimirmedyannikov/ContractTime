package ru.medyannikov.model;

import javafx.beans.property.StringProperty;

/**
 * Created by Vladimir on 02.01.2016.
 */
public class Firm {
    private int idFirm;
    private StringProperty nameFirm;

    public int getIdFirm() {
        return idFirm;
    }

    public void setIdFirm(int idFirm) {
        this.idFirm = idFirm;
    }

    public String getNameFirm() {
        return nameFirm.get();
    }

    public StringProperty nameFirmProperty() {
        return nameFirm;
    }

    public void setNameFirm(String nameFirm) {
        this.nameFirm.set(nameFirm);
    }
}
