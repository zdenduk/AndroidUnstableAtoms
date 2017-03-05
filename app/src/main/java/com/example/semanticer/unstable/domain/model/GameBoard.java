package com.example.semanticer.unstable.domain.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by semanticer on 15.01.2017.
 */
@AutoValue
public abstract class GameBoard implements Parcelable{
    public static GameBoard create(List<List<GameField>> fields) {
        if(fields == null || fields.size() == 0 || fields.get(0).size() == 0)
            throw new IllegalArgumentException("Illegal state fields");

        return new AutoValue_GameBoard(fields, fields.size(), fields.get(0).size());
    }

    public abstract List<List<GameField>> fields();
    public abstract int rows();
    public abstract int columns();
}
