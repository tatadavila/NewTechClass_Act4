package com.edu.uac.co.grupo6_act4;

import java.io.Serializable;

public class Patient implements Serializable {

    public String id;
    public String name;
    public String address;
    public String attentionPlace;
    public String positiveDate;

    public Patient() {
    }

    public Patient(String id, String name, String address, String attentionPlace, String positiveDate) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.attentionPlace = attentionPlace;
        this.positiveDate = positiveDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAttentionPlace() {
        return attentionPlace;
    }

    public void setAttentionPlace(String attentionPlace) {
        this.attentionPlace = attentionPlace;
    }

    public String getPositiveDate() {
        return positiveDate;
    }

    public void setPositiveDate(String positiveDate) {
        this.positiveDate = positiveDate;
    }

    /*public String toString() {
        return "Patient{" +
                "Name='" + name + '\'' +
                ", Id='" + id + '\'' +
                ", Address='" + address + '\'' +
                ", AttentionPlace='" + attentionPlace + '\'' +
                ", PositiveTestDate='" + positiveDate + '\'' +
                '}';
    }*/

}
