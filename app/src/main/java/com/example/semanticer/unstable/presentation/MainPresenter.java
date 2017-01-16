package com.example.semanticer.unstable.presentation;

import android.os.Bundle;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.GameImpl;
import com.example.semanticer.unstable.domain.model.GameBoard;

import nucleus.presenter.RxPresenter;

/**
 * Created by semanticer on 15.01.2017.
 */
public class MainPresenter extends RxPresenter<MainView> {

    private Game game;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        game = GameImpl.createNew(6, 4);
        view().subscribe(view -> view.showGameBoard(game.getBoard()));

    }

    public void onMoveMade(int x, int y) {
        GameBoard newGameBoard = game.onMoveMade(x, y);
        view().subscribe(view -> view.showGameBoard(newGameBoard));
    }
}
