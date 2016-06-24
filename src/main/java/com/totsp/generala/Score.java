package com.totsp.generala;

/**
 * Created by cecollins on 6/23/16.
 */
public class Score {

    private final ScoreType scoreType;
    public int value;
    public boolean selected;

    public Score(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

}
