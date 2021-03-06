package com.example.semanticer.unstable.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(GamePresenter.class)
public class GameActivity extends NucleusActivity<GamePresenter> implements GameView {

    @BindView(R.id.game_board_layout)
    GameBoardLayout gameBoardLayout;

    @BindView(R.id.score1)
    TextView player1score;

    @BindView(R.id.score2)
    TextView player2score;

    @BindView(R.id.currentPlayer)
    TextView currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        boolean gameType = getIntent().getBooleanExtra("Type", false);
        gameBoardLayout.setOnMoveListener((x, y) -> getPresenter().onMoveMade(x, y, gameType));
    }

    @Override
    public void showGameBoard(GameBoard gameBoard) {
        gameBoardLayout.setBoard(gameBoard);
    }

    @Override
    public void showScore(Player player1, Player player2, Game game1) {
        player1score.setText("player1 score: " + game1.getScore(game1.getBoard(), player1) + " ");
        player2score.setText("player2 score:" + game1.getScore(game1.getBoard(), player2) + " ");
    }

    @Override
    public void showCurrentPlayer(Player player) {
        currentPlayer.setText(player == Player.FIRST_PLAYER ? "Player1" : "Player2");
    }

    @Override
    public void warn(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToFinal(boolean winner, List<String> scores, List<GameBoard> gameBoards) {
        Intent intent = new Intent(this, FinalActivity.class);
        intent.putExtra("playerWon", winner);
        intent.putStringArrayListExtra("scores", (ArrayList<String>) scores);
        intent.putParcelableArrayListExtra("gameboards", (ArrayList<? extends Parcelable>) gameBoards);
        startActivity(intent);
    }

}
