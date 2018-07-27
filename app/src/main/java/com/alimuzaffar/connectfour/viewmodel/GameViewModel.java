package com.alimuzaffar.connectfour.viewmodel;

import android.widget.ImageView;

import com.alimuzaffar.connectfour.model.Game;
import com.alimuzaffar.connectfour.model.Player;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, Integer> cells;
    private Game game;

    public void init() {
        game = new Game();
        cells = new ObservableArrayMap<>();
        // Initialize all cells with defaults
        for (int r = 0; r < Game.BOARD_ROWS; r++){
            for (int c = 0; c < Game.BOARD_COLUMNS; c++){
                cells.put(r + "," + c, 0);
            }
        }

    }

    public void onClickedCellAt(int r, int c) {
        if (game.getCell(r, c) == null) {
            game.setCell(game.getCurrentPlayer(), r, c);
            cells.put(r + "," + c, game.getCurrentPlayer().getToken());
            if (game.hasGameEnded()) {
                game.reset();
                cells = new ObservableArrayMap<>();
                for (int ro = 0; ro < Game.BOARD_ROWS; ro++){
                    for (int co = 0; co < Game.BOARD_COLUMNS; co++){
                        cells.put(ro + "," + co, 0);
                    }
                }
            }
            else {
                game.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}