package com.example.petrial.Models;

import java.io.Serializable;

public class Tacgia implements Serializable {
    public long IDTacgia;
    public String TenTacGia;
    public String Diachi;
    public String dienthoai;

    public Tacgia(long IDTacgia, String teeTacgia, String diachi, String dienthoai) {
        this.IDTacgia = IDTacgia;
        TenTacGia = teeTacgia;
        Diachi = diachi;
        this.dienthoai = dienthoai;
    }

    public Tacgia(String teeTacgia, String diachi, String dienthoai) {
        TenTacGia = teeTacgia;
        Diachi = diachi;
        this.dienthoai = dienthoai;
    }

    public long getIDTacgia() {
        return IDTacgia;
    }

    public void setIDTacgia(long IDTacgia) {
        this.IDTacgia = IDTacgia;
    }

    public String getTenTacgia() {
        return TenTacGia;
    }

    public void setTenTacgia(String teeTacgia) {
        TenTacGia = teeTacgia;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }
}
