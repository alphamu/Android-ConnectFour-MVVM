package com.alimuzaffar.connectfour;

import android.os.Bundle;
import android.widget.Toast;

import com.alimuzaffar.connectfour.databinding.MainActivityBinding;
import com.alimuzaffar.connectfour.model.Player;
import com.alimuzaffar.connectfour.viewmodel.GameViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initDataBinding(savedInstanceState);
    }

    private void initDataBinding(Bundle savedInstanceState) {
        MainActivityBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        if (savedInstanceState == null) {
            gameViewModel.init();
        }
        activityGameBinding.setGame(gameViewModel);
        setupOnGameEndListener();

    }

    private void setupOnGameEndListener() {
        gameViewModel.getWinner().observe(this, new Observer<Player>() {
            @Override
            public void onChanged(Player winner) {
                if (winner != null) {
                    String winnerName = winner.getName();
                    Toast.makeText(MainActivity.this, "Game over, Winner: " + winnerName, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
