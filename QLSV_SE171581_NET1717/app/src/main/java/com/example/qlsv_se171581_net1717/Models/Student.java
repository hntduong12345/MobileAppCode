package com.example.qlsv_se171581_net1717.Models;

import java.io.Serializable;

public class Student implements Serializable {
    public long ID;
    public  String name;
    public String date;
    public String gender;
    public String email;
    public String Address;
    public String idMajor;

    public Student(long ID, String name, String date, String gender, String email, String address, String idMajor) {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.email = email;
        Address = address;
        this.idMajor = idMajor;
    }

    public Student(String name, String date, String gender, String email, String address, String idMajor) {
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.email = email;
        Address = address;
        this.idMajor = idMajor;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getIdMajor() {
        return idMajor;
    }

    public void setIdMajor(String idMajor) {
        this.idMajor = idMajor;
    }
}
