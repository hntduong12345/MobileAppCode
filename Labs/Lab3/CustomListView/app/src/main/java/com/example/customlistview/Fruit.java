package com.example.customlistview;

public class Fruit {
    private String Name;
    private String Description;
    private int Picture;

    public Fruit(String name, String description, int picture) {
        Name = name;
        Description = description;
        Picture = picture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }
}
