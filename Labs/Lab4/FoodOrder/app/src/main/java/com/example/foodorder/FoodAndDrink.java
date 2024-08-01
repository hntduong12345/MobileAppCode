package com.example.foodorder;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class FoodAndDrink implements Serializable {
    private String Name;
    private String Price;

    private String Type;
    private int Picture;

    public FoodAndDrink(String name, String price, String type, int picture) {
        Name = name;
        Price = price;
        Type = type;
        Picture = picture;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }
}
