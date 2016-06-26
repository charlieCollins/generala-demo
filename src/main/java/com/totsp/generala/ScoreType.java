package com.totsp.generala;

public enum ScoreType {

    ONES(1), TWOS(2), THREES(3), FOURS(4), FIVES(5), SIXES(6),
    THREEKIND(7), FOURKIND(8), FULLHOUSE(9), SMALLSTRAIGHT(10), LARGESTRAIGHT(11),
    CHANCE(12), GENERALA(13), UPPERBONUS(-1);

    private int position;

    private ScoreType(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public static ScoreType getByPosition(int position) {
        for (ScoreType scoreType : values()) {
            if (scoreType.getPosition() == position) {
                return scoreType;
            }
        }
        return null;
    }

}
