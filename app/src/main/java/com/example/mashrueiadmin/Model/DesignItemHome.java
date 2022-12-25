package com.example.mashrueiadmin.Model;

public class DesignItemHome {


    static String category;

    private int image;
    private String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        DesignItemHome.category = category;
    }

}
