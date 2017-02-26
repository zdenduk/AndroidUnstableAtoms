package com.example.semanticer.unstable.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.Game;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(FinalPresenter.class)
public class FinalActivity extends NucleusActivity<FinalPresenter> implements FinalView {

    @BindView(R.id.winner)
    TextView winner;

    @BindView(R.id.playAgain)
    Button playAgainField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        ButterKnife.bind(this);
    }

    @Override
    public void showWinner() {
        Intent intent = getIntent();
        boolean playerWon = intent.getExtras().getBoolean("playerWon");
        winner.setText(playerWon ? "Player1 win" : "Player2 win");
        playAgainField.setText("Play again");
    }

    @Override
    public void playAgain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDetails(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

}
