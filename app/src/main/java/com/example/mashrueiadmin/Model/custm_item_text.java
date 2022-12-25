package com.example.mashrueiadmin.Model;

public class custm_item_text
{
    private String spinnertext;
    private int image;

    public custm_item_text(String spinnertext, int image) {
        this.spinnertext = spinnertext;
        this.image = image;
    }

    public custm_item_text(String spinnertext) {
        this.spinnertext = spinnertext;

    }


    public int getImage() {
        return image;
    }

    public String getSpinnertext() {
        return spinnertext;
    }

}
