package com.example.semanticer.unstable.presentation;

import android.os.Bundle;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.GameImpl;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

import nucleus.presenter.RxPresenter;

/**
 * Created by semanticer on 15.01.2017.
 */
public class FinalPresenter extends RxPresenter<FinalView> {

    private Game game;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        game = GameImpl.createNew(6, 4);
        view().subscribe(view -> {
            if (view != null) {
                view.showWinner();
            }
        });
    }
}
