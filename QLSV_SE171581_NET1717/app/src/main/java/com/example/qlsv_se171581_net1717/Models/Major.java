package com.example.qlsv_se171581_net1717.Models;

import java.io.Serializable;

public class Major implements Serializable {
    public long IDMajor;
    public String nameMajor;

    public Major(long IDMajor, String nameMajor) {
        this.IDMajor = IDMajor;
        this.nameMajor = nameMajor;
    }

    public Major(String nameMajor) {
        this.nameMajor = nameMajor;
    }

    public long getIDMajor() {
        return IDMajor;
    }

    public void setIDMajor(long IDMajor) {
        this.IDMajor = IDMajor;
    }

    public String getNameMajor() {
        return nameMajor;
    }

    public void setNameMajor(String nameMajor) {
        this.nameMajor = nameMajor;
    }
}
