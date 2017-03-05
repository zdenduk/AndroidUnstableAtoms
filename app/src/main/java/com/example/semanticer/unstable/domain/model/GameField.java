package com.example.semanticer.unstable.domain.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by semanticer on 15.01.2017.
 */

@AutoValue
abstract public class GameField implements Parcelable{

    public static final int MAXIMUM_STABLE_ATOM_COUNT = 3;

    public static GameField create(int atomCount, Player player) {

        if (atomCount > 0 && player == Player.ANON)
            throw new IllegalArgumentException("Field with some atoms must define their player owner");
        if (atomCount == 0 && player != Player.ANON)
            throw new IllegalArgumentException("Player cannot own zero atoms field");
        if (atomCount >= 0 && atomCount > MAXIMUM_STABLE_ATOM_COUNT)
            throw new IllegalArgumentException("Cannot set atomCount to " + atomCount + ". Must fit to range 0 - " + MAXIMUM_STABLE_ATOM_COUNT);

        return new AutoValue_GameField(atomCount, player);
    }

    public static GameField createBlank() {
        return new AutoValue_GameField(0, Player.ANON);
    }

    public abstract int atomCount();
    public abstract Player player();
}
