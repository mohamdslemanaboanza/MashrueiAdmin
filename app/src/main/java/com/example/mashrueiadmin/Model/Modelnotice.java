package com.example.mashrueiadmin.Model;

public class Modelnotice {
    private String id;
    private String name;
    private String deck;

    public Modelnotice(String id, String name, String deck) {
        this.id = id;
        this.name = name;
        this.deck = deck;
    }

    public Modelnotice() {
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeck() {
        return deck;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }
}
