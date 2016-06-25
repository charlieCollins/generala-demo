package com.totsp.generala;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cecollins on 6/23/16.
 */
public class Data {

    public static final int NUM_SCORES_SELECTED_COMPLETE_GAME = 13;
    public static final int NUM_DIE_ROLLS_PER_TURN = 3;


    public ImmutableList<Die> dieList;
    public Die die1 = new Die();
    public Die die2 = new Die();
    public Die die3 = new Die();
    public Die die4 = new Die();
    public Die die5 = new Die();

    public ImmutableList<Score> scoreListNonBonus;
    public Score ones = new Score(ScoreType.ONES);
    public Score twos = new Score(ScoreType.TWOS);
    public Score threes = new Score(ScoreType.THREES);
    public Score fours = new Score(ScoreType.FOURS);
    public Score fives = new Score(ScoreType.FIVES);
    public Score sixes = new Score(ScoreType.SIXES);
    public Score threekind = new Score(ScoreType.THREEKIND);
    public Score fourkind = new Score(ScoreType.FOURKIND);
    public Score fullhouse = new Score(ScoreType.FULLHOUSE);
    public Score smallstraight = new Score(ScoreType.SMALLSTRAIGHT);
    public Score largestraight = new Score(ScoreType.LARGESTRAIGHT);
    public Score generala = new Score(ScoreType.GENERALA);
    public Score chance = new Score(ScoreType.CHANCE);

    public Score upperbonus = new Score(ScoreType.UPPERBONUS);
    public Score lowerbonus = new Score(ScoreType.LOWERBONUS);

    public Set<ScoreType> scoresSelected;

    public int totalScore;
    public int currentRoll;
    public int numScoresSelected;

    public Data() {
        scoresSelected = new HashSet<ScoreType>();
        dieList = ImmutableList.of(die1, die2, die3, die4, die5);
        scoreListNonBonus = ImmutableList.of(ones, twos, threes, fours, fives, sixes, threekind, fourkind,
                fullhouse, smallstraight, largestraight, generala, chance);
    }

    public String displayDieState() {
        System.out.println("DIE1:" + die1.value + " (selected:" + die1.selected + ")");
        System.out.println("DIE2:" + die2.value + " (selected:" + die2.selected + ")");
        System.out.println("DIE3:" + die3.value + " (selected:" + die3.selected + ")");
        System.out.println("DIE4:" + die4.value + " (selected:" + die4.selected + ")");
        System.out.println("DIE5:" + die5.value + " (selected:" + die5.selected + ")");

        StringBuilder sb = new StringBuilder();
        int position = 0;
        for (Die die : dieList) {
            position++;
            sb.append("DIE" + position + " " + die.value + " (selected:" + die.selected + ")\n" );
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("dieList", dieList + "\n")
                .add("die1", die1 + "\n")
                .add("die2", die2 + "\n")
                .add("die3", die3 + "\n")
                .add("die4", die4 + "\n")
                .add("die5", die5 + "\n")
                .add("scoreListNonBonus", scoreListNonBonus + "\n")
                .add("ones", ones + "\n")
                .add("twos", twos + "\n")
                .add("threes", threes + "\n")
                .add("fours", fours + "\n")
                .add("fives", fives + "\n")
                .add("sixes", sixes + "\n")
                .add("threekind", threekind + "\n")
                .add("fourkind", fourkind + "\n")
                .add("fullhouse", fullhouse + "\n")
                .add("smallstraight", smallstraight + "\n")
                .add("largestraight", largestraight + "\n")
                .add("generala", generala + "\n")
                .add("chance", chance + "\n")
                .add("upperbonus", upperbonus + "\n")
                .add("lowerbonus", lowerbonus + "\n")
                .add("scoresSelected", scoresSelected + "\n")
                .add("totalScore", totalScore + "\n")
                .add("currentRoll", currentRoll + "\n")
                .add("numScoresSelected", numScoresSelected + "\n")
                .toString();
    }
}


