package com.example.administrator.recyclerviewdemo;


import java.io.Serializable;

public class Model implements Serializable {

    private int color;

    private int number;

    public Model(int color, int number) {

        this.color = color;
        this.number = number;
    }

    public int getColor() {

        return color;
    }

    public void setColor(int color) {

        this.color = color;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

}
