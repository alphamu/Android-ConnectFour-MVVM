package com.alimuzaffar.connectfour.model;

import com.alimuzaffar.connectfour.R;

import androidx.lifecycle.MutableLiveData;

public class Game {

    public static final int BOARD_COLUMNS = 7; // X
    public static final int BOARD_ROWS = 6; // Y

    private Player player1 = new Player("Player One", R.drawable.token_red);
    private Player player2 = new Player("Player Two", R.drawable.token_yellow);

    private Player currentPlayer = player1;
    private Cell[][] cells = new Cell[BOARD_ROWS][BOARD_COLUMNS]; // Board is setup as X,Y

    private int moveCount = 1;
    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
        moveCount++;
    }

    private boolean isBoardFull() {
        return moveCount == BOARD_COLUMNS * BOARD_ROWS;
    }

    public boolean hasGameEnded() {
        if (has4InRow() || has4InColumn() || has4Diagonal()) {
            winner.setValue(currentPlayer);
            return true;
        } else if (isBoardFull()) {
            winner.setValue(new Player("No winner", 0));
            return true;
        }
        return false;
    }

    private boolean has4InColumn() {
        for (int c = 0; c < BOARD_COLUMNS; c++) {
            int matches = 1;
            Player previous = null;
            for (int r = 0; r < BOARD_ROWS; r++) {
                Cell cell = cells[r][c];
                if (cell == null || cell.getPlayer() == null) {
                    continue;
                }

                Player current = cell.getPlayer();
                if (current == previous) {
                    matches++;
                    if (matches == 4) {
                        return true;
                    }
                } else {
                    matches = 1;
                    previous = current;
                }
            }
        }
        return false;
    }

    private boolean has4InRow() {
        for (int r = 0; r < BOARD_ROWS; r++) {
            int matches = 1;
            Player previous = null;
            for (int c = 0; c < BOARD_COLUMNS; c++) {
                Cell cell = cells[r][c];
                if (cell == null || cell.getPlayer() == null) {
                    continue;
                }

                Player current = cell.getPlayer();
                if (current == previous) {
                    matches++;
                    if (matches == 4) {
                        return true;
                    }
                } else {
                    matches = 1;
                    previous = current;
                }
            }
        }
        return false;
    }

    private boolean has4Diagonal() {
        // Remember: We are checking top down
        // So this is like a tree, if you find a cell with a
        // bottom left or bottom right matching, you can
        // check if the same corner has a subsequent similar token
        // and keep going till you find 4, once all tokens have been checked
        // you're done, this can be recursively done.
        return false;

    }

    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    public void setCell(Player p, int r, int c) {
        this.cells[r][c] = new Cell(p);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void reset() {
        currentPlayer = player1;
        cells = new Cell[BOARD_ROWS][BOARD_COLUMNS]; // Board is setup as X,Y
        moveCount = 1;
        winner.setValue(null);
    }
}
