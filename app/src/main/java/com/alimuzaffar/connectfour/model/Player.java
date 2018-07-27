package com.alimuzaffar.connectfour.model;

import androidx.annotation.DrawableRes;

public class Player {
    private String name;
    private int token;

    public Player(String name, @DrawableRes int token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getToken() {
        return token;
    }

    public void setToken(@DrawableRes int token) {
        this.token = token;
    }
}
