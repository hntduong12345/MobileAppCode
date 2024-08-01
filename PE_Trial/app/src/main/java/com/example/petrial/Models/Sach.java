package com.example.petrial.Models;

import java.io.Serializable;
import java.util.Date;

public class Sach implements Serializable {
    public long Masach;
    public String Tensach;
    public String NgayXB;
    public String Theloai;
    public String idTacgia;

    public Sach(long masach, String tensach, String ngayXB, String theloai, String idTacgia) {
        Masach = masach;
        Tensach = tensach;
        NgayXB = ngayXB;
        Theloai = theloai;
        this.idTacgia = idTacgia;
    }

    public Sach(String tensach, String ngayXB, String theloai, String idTacgia) {
        Tensach = tensach;
        NgayXB = ngayXB;
        Theloai = theloai;
        this.idTacgia = idTacgia;
    }

    public long getMasach() {
        return Masach;
    }

    public void setMasach(long masach) {
        Masach = masach;
    }

    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public String getNgayXB() {
        return NgayXB;
    }

    public void setNgayXB(String ngayXB) {
        NgayXB = ngayXB;
    }

    public String getTheloai() {
        return Theloai;
    }

    public void setTheloai(String theloai) {
        Theloai = theloai;
    }

    public String getIdTacgia() {
        return idTacgia;
    }

    public void setIdTacgia(String idTacgia) {
        this.idTacgia = idTacgia;
    }
}
