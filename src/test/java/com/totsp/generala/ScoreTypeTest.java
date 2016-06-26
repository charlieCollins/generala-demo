package com.totsp.generala;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTypeTest {

    @Test
    public void getPosition() throws Exception {
        assertEquals(ScoreType.ONES.getPosition(), 1);
        assertEquals(ScoreType.TWOS.getPosition(), 2);
        assertEquals(ScoreType.THREES.getPosition(), 3);
        assertEquals(ScoreType.FOURS.getPosition(), 4);
        assertEquals(ScoreType.FIVES.getPosition(), 5);
        assertEquals(ScoreType.SIXES.getPosition(), 6);
        assertEquals(ScoreType.THREEKIND.getPosition(), 7);
        assertEquals(ScoreType.FOURKIND.getPosition(), 8);
        assertEquals(ScoreType.FULLHOUSE.getPosition(), 9);
        assertEquals(ScoreType.SMALLSTRAIGHT.getPosition(), 10);
        assertEquals(ScoreType.LARGESTRAIGHT.getPosition(), 11);
        assertEquals(ScoreType.CHANCE.getPosition(), 12);
        assertEquals(ScoreType.GENERALA.getPosition(), 13);
        assertEquals(ScoreType.UPPERBONUS.getPosition(), -1);
    }

    @Test
    public void getByPosition() throws Exception {
        assertEquals(ScoreType.ONES, ScoreType.getByPosition(1));
        assertEquals(ScoreType.TWOS, ScoreType.getByPosition(2));
        assertEquals(ScoreType.THREES, ScoreType.getByPosition(3));
        assertEquals(ScoreType.FOURS, ScoreType.getByPosition(4));
        assertEquals(ScoreType.FIVES, ScoreType.getByPosition(5));
        assertEquals(ScoreType.SIXES, ScoreType.getByPosition(6));
        assertEquals(ScoreType.THREEKIND, ScoreType.getByPosition(7));
        assertEquals(ScoreType.FOURKIND, ScoreType.getByPosition(8));
        assertEquals(ScoreType.FULLHOUSE, ScoreType.getByPosition(9));
        assertEquals(ScoreType.SMALLSTRAIGHT, ScoreType.getByPosition(10));
        assertEquals(ScoreType.LARGESTRAIGHT, ScoreType.getByPosition(11));
        assertEquals(ScoreType.CHANCE, ScoreType.getByPosition(12));
        assertEquals(ScoreType.GENERALA, ScoreType.getByPosition(13));
        assertEquals(ScoreType.UPPERBONUS, ScoreType.getByPosition(-1));
    }

}