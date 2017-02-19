package com.example.semanticer.unstable.presentation;

import android.os.Bundle;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.GameImpl;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import nucleus.presenter.RxPresenter;

/**
 * Created by semanticer on 15.01.2017.
 */
public class GamePresenter extends RxPresenter<GameView> {

    private Game game;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        game = GameImpl.createNew(6, 4);
        view().subscribe(view -> view.showGameBoard(game.getBoard()));
        view().subscribe(view -> view.showCurrentPlayer(Player.FIRST_PLAYER));
        view().subscribe(view -> view.showScore(Player.FIRST_PLAYER, Player.SECOND_PLAYER, game));
        view().subscribe(view -> view.hideWinnerText());
    }

    public void onMoveMade(int x, int y, boolean type) {
        if (game.isntOver()) {
            if (game.isMovePossible(x, y)) {
                GameBoard newGameBoard = game.onMoveMade(x, y, type);
                view().subscribe(view -> view.showGameBoard(newGameBoard));
                view().subscribe(view -> view.showCurrentPlayer(game.getPlayer()));
                view().subscribe(view -> view.showScore(Player.FIRST_PLAYER, Player.SECOND_PLAYER, game));
                if (!game.isntOver()) {
                    view().subscribe(view -> view.showWinner(game.getScore(game.getBoard(), Player.FIRST_PLAYER) > 0 ? Player.FIRST_PLAYER : Player.SECOND_PLAYER));
                }
            } else {
                view().subscribe(view -> view.warn("Unable to make move"));
            }
        }
    }
}
