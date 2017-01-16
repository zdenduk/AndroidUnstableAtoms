package com.example.semanticer.unstable.domain;

import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.GameField;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by semanticer on 15.01.2017.
 */

public class GameImpl implements Game {

    private GameBoard gameBoard;
    private Player playerOnTurn;

    public static GameImpl createNew(int rowCount, int columnCount) {
        return new GameImpl(rowCount, columnCount);
    }

    private GameImpl(int rowCount, int columnCount) {

        List<List<GameField>> fields = new ArrayList<>();

        // TODO fill up fields with empty GameFields

        gameBoard = getSampleBoard(); // TODO replace with GameBoard.create(fields);
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
        // TODO return new GameBoard after this move

        switchPlayerOnTurn();
        return  getSampleBoard(); // TODO replace with new and CORRECT GameBoard
    }

    @Override
    public boolean isMovePossible(int x, int y) {
        // TODO return if
        return true;
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }


    // TODO remove this example method
    public GameBoard getSampleBoard() {
        List<List<GameField>> fields =
            Arrays.asList(
                    Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.create(3, Player.SECOND_PLAYER), GameField.createBlank()),
                    Arrays.asList(GameField.create(2, Player.FIRST_PLAYER), GameField.createBlank(), GameField.create(1, Player.SECOND_PLAYER), GameField.createBlank()),
                    Arrays.asList(GameField.create(3, Player.FIRST_PLAYER), GameField.createBlank(), GameField.createBlank(), GameField.create(1, Player.FIRST_PLAYER)),
                    Arrays.asList(GameField.create(1, Player.SECOND_PLAYER), GameField.create(1, Player.SECOND_PLAYER), GameField.create(3, Player.SECOND_PLAYER), GameField.createBlank()),
                    Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.createBlank(), GameField.create(2, Player.SECOND_PLAYER)),
                    Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.create(1, Player.SECOND_PLAYER), GameField.createBlank())
            );

        for (List<GameField> row : fields) {
            Collections.shuffle(row);
        }
        return GameBoard.create(fields);
    }
}
