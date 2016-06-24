package com.totsp.generala;

import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cecollins on 6/23/16.
 */
public class Data {

    public int currentRoll;

    // TODO refactor a die class, values, selected state, etc
    public boolean die1Selected;
    public int die1Value;
    public boolean die2Selected;
    public int die2Value;
    public boolean die3Selected;
    public int die3Value;
    public boolean die4Selected;
    public int die4Value;
    public boolean die5Selected;
    public int die5Value;

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

    public int totalScore;

    public Set<ScoreType> scoresSelected;
    public int numScoresSelected;

    public Data() {
        scoresSelected = new HashSet<ScoreType>();
    }

    public void printDieState() {
        System.out.println("DIE1:" + die1Value + " (selected:" + die1Selected + ")");
        System.out.println("DIE2:" + die2Value + " (selected:" + die2Selected + ")");
        System.out.println("DIE3:" + die3Value + " (selected:" + die3Selected + ")");
        System.out.println("DIE4:" + die4Value + " (selected:" + die4Selected + ")");
        System.out.println("DIE5:" + die5Value + " (selected:" + die5Selected + ")");
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("currentRoll", currentRoll + "\n")
                .add("die1Selected", die1Selected + "\n")
                .add("die1Value", die1Value + "\n")
                .add("die2Selected", die2Selected + "\n")
                .add("die2Value", die2Value + "\n")
                .add("die3Selected", die3Selected + "\n")
                .add("die3Value", die3Value + "\n")
                .add("die4Selected", die4Selected + "\n")
                .add("die4Value", die4Value + "\n")
                .add("die5Selected", die5Selected + "\n")
                .add("die5Value", die5Value + "\n")
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
                .add("totalScore", totalScore + "\n")
                .add("scoresSelected", scoresSelected + "\n")
                .add("numScoresSelected", numScoresSelected + "\n")
                .toString();
    }
}


