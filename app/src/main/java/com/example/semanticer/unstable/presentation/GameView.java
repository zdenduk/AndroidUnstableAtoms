package com.example.semanticer.unstable.presentation;

import android.view.View;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

/**
 * Created by semanticer on 15.01.2017.
 */

public interface GameView {
    void showGameBoard(GameBoard gameBoard);

    void showScore(Player player1, Player player2, Game game1);

    void showWinner(Player player);

    void showCurrentPlayer(Player player);

    void hideWinnerText();

    void warn(String message);

    void playAgain(View view);
}
