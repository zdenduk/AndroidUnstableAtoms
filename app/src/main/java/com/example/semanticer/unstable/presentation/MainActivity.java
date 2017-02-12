package com.example.semanticer.unstable.presentation;

import android.os.Bundle;
import android.widget.TextView;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusActivity<MainPresenter> implements MainView {

    @BindView(R.id.game_board_layout)
    GameBoardLayout gameBoardLayout;

    @BindView(R.id.score1)
    TextView player1score;

    @BindView(R.id.score2)
    TextView player2score;

    @BindView(R.id.winner)
    TextView winner;

    @BindView(R.id.currentPlayer)
    TextView currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gameBoardLayout.setOnMoveListener((x, y) -> getPresenter().onMoveMade(x, y));
    }

    @Override
    public void showGameBoard(GameBoard gameBoard) {
        gameBoardLayout.setBoard(gameBoard);
    }

    @Override
    public void showScore(Player player1, Player player2, Game game1) {
        player1score.setText(game1.getScore(game1.getBoard(), player1)+" ");
        player2score.setText(game1.getScore(game1.getBoard(), player2)+" ");
    }

    @Override
    public void showWinner(Player player) {
        winner.setText(player == Player.FIRST_PLAYER ? "player1 win" : "player2 win");
    }

    @Override
    public void showCurrentPlayer(Player player) {
        currentPlayer.setText(player == Player.FIRST_PLAYER ? "Player1" : "Player2");
    }
}
