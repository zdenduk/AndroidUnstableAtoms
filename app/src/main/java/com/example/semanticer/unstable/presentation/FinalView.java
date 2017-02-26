package com.example.semanticer.unstable.presentation;

import android.view.View;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.Player;

public interface FinalView {
    void showWinner();
    void playAgain(View view);
    void showDetails(View view);
}
