package com.example.lab567.RecycleListView.Model;

public class HTModule {
    public String Name;
    public String Description;
    public String Category;
    public int Image;

    public HTModule(String name, String description, String category, int image) {
        Name = name;
        Description = description;
        Category = category;
        Image = image;
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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
