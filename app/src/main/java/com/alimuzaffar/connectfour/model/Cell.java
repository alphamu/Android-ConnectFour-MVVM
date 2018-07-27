package com.alimuzaffar.connectfour.model;

import android.text.TextUtils;

public class Cell {

    private Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || TextUtils.isEmpty(player.getName());
    }

}
