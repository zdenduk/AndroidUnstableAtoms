package com.example.semanticer.unstable.domain;

import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.GameField;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by semanticer & zdeněk on 15.01.20017.
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
    public GameBoard onMoveMade(int x, int y, boolean type) {
        return isMovePossible(x, y) ? (type ? playerTurn(x, y) : gameBoard) : gameBoard;
    }

    private GameBoard playerTurn(int x, int y) {
        GameBoard newGame = alterGameBoard(x, y);
        switchPlayerOnTurn();
        return newGame;
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
        return gameBoard.fields().get(x).get(y).player() == playerOnTurn || gameBoard.fields().get(x).get(y).player() == Player.ANON;
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    @Override
    public int getScore(GameBoard board, Player player) {
        int counter = 0;
        for (List<GameField> cols : board.fields()) {
            for (GameField rows : cols) {
                if (rows.player() == player) {
                    counter += rows.atomCount();
                }
            }
        }
        return counter;
    }

    @Override
    public boolean isntOver() { //checks if neither player has 0 atoms and if other has atleast one (because of start of the game)
        return !((getScore(gameBoard, Player.FIRST_PLAYER) == 0 && getScore(gameBoard, Player.SECOND_PLAYER) > 1) || (getScore(gameBoard, Player.SECOND_PLAYER) == 0 && getScore(gameBoard, Player.FIRST_PLAYER) > 1));
    }

    @Override
    public Player getPlayer() {
        return playerOnTurn;
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
