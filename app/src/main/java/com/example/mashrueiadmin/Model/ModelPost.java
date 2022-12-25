package com.example.mashrueiadmin.Model;

public class ModelPost
{
    private String namePost;
    private String deckPost;
    private String uriImagePost;
    private String key;

    public ModelPost(String namePost, String deckPost, String uriImagePost, String key) {
        this.namePost = namePost;
        this.deckPost = deckPost;
        this.uriImagePost = uriImagePost;
        this.key = key;
    }


    public ModelPost() {
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public String getDeckPost() {
        return deckPost;
    }

    public void setDeckPost(String deckPost) {
        this.deckPost = deckPost;
    }

    public String getUriImagePost() {
        return uriImagePost;
    }

    public void setUriImagePost(String uriImagePost) {
        this.uriImagePost = uriImagePost;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
