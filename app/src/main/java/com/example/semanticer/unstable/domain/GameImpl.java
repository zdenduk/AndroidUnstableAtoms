package com.example.semanticer.unstable.domain;

import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.GameField;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semanticer on 15.01.20017.
 */

public class GameImpl implements Game {

    private GameBoard gameBoard;
    private Player playerOnTurn;

    public static GameImpl createNew(int rowCount, int columnCount) {
        return new GameImpl(rowCount, columnCount);
    }

    private GameImpl(int rowCount, int columnCount) {
        List<List<GameField>> fields = getClearBoard(rowCount, columnCount);
        gameBoard = GameBoard.create(fields);
        playerOnTurn = Player.FIRST_PLAYER;
    }


    private void switchPlayerOnTurn() {
        playerOnTurn = playerOnTurn == Player.FIRST_PLAYER ? Player.SECOND_PLAYER : Player.FIRST_PLAYER;
    }

    @Override
    public GameBoard onMoveMade(int x, int y) {
        if (!isMovePossible(x, y)) {
            throw new IllegalStateException("Impossible to make move to position x: " + x + " y: " + y);
        }
        switchPlayerOnTurn();
        GameBoard newBoard = alterGameBoard(x, y);
        return newBoard;
    }


    private GameBoard alterGameBoard(int x, int y) {

        if (gameBoard.fields().get(x).get(y).atomCount() < 3) {
            gameBoard.fields().get(x).set(y, GameField.create(gameBoard.fields().get(x).get(y).atomCount() + 1, playerOnTurn));
        } else {
            gameBoard.fields().get(x).set(y, GameField.createBlank());
            spreadFrom(x, y);
        }
        return gameBoard;
    }

    private void spreadFrom(int x, int y) {
        if (x > 0) {
            alterGameBoard(x - 1, y);
        }
        if (x < gameBoard.rows() - 1) {
            alterGameBoard(x + 1, y);
        }
        if (y > 0) {
            alterGameBoard(x, y - 1);
        }
        if (y < gameBoard.columns() - 1) {
            alterGameBoard(x, y + 1);
        }
    }

    @Override
    public boolean isMovePossible(int x, int y) {
        return gameBoard.fields().get(x).get(y).player() != playerOnTurn || gameBoard.fields().get(x).get(y).player() == Player.ANON;
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    public List<List<GameField>> getClearBoard(int x, int y) {
        List<List<GameField>> list = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            list.add(i, new ArrayList<>());
            for (int j = 0; j < y; j++) {
                list.get(i).add(j, GameField.createBlank());
            }
        }
        return list;
    }
}
