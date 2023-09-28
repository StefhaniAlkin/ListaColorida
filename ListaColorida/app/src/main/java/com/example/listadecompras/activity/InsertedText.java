package com.example.listadecompras.activity;

import androidx.annotation.NonNull;

public class InsertedText {
    private int id;
    private String text;
    private String color;


    public InsertedText() {
    }

    public InsertedText(String text, String color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Texto: " + getText() + " Cor: " + getColor();
    }
}
