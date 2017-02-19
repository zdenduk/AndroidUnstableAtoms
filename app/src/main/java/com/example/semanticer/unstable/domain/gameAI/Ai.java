package com.example.semanticer.unstable.domain.gameAI;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;

import java.util.Random;

/**
 * Created by Zdenda on 19.02.2017.
 */

public class Ai {

    public static GameBoard makeTurn(Game game) {
        if (game.isntOver()) {
            return randomTurn(game.getBoard(), game);
        } else {
            return game.getBoard();
        }
    }

    private static GameBoard randomTurn(GameBoard board, Game game) {
        int x = new Random().nextInt(board.rows());
        int y = new Random().nextInt(board.columns());
        if (game.isMovePossible(x, y)) {
            return game.alterGameBoard(x, y);
        } else {
            return randomTurn(board, game);
        }
    }
}
