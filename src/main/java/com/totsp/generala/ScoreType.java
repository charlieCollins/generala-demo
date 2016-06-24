package com.totsp.generala;

/**
 * Created by cecollins on 6/23/16.
 */
public enum ScoreType {


    ONES(1), TWOS(2), THREES(3), FOURS(4), FIVES(5), SIXES(6),
    THREEKIND(-1), FOURKIND(-1), FULLHOUSE(-1), SMALLSTRAIGHT(-1), LARGESTRAIGHT(-1),
    GENERALA(-1), CHANCE(-1), UPPERBONUS(-1), LOWERBONUS(-1);

    private int value;

    private ScoreType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
