package com.totsp.generala;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.*;

public class Data {

    public final ImmutableList<Die> dieList;
    public final Set<ScoreType> scoreTypesSelected;

    private final Die die1;
    private final Die die2;
    private final Die die3;
    private final Die die4;
    private final Die die5;

    private final ImmutableSet<ScoreType> scoreTypes;
    private final ImmutableList<Score> scoresNonBonus;

    private final Score ones;
    private final Score twos;
    private final Score threes;
    private final Score fours;
    private final Score fives;
    private final Score sixes;
    private final Score threekind;
    private final Score fourkind;
    private final Score fullhouse;
    private final Score smallstraight;
    private final Score largestraight;
    private final Score generala;
    private final Score chance;
    private final Score upperbonus;

    private int currentRoll;

    public Data() {

        die1 = new Die();
        die2 = new Die();
        die3 = new Die();
        die4 = new Die();
        die5 = new Die();

        ones = new Score(ScoreType.ONES);
        twos = new Score(ScoreType.TWOS);
        threes = new Score(ScoreType.THREES);
        fours = new Score(ScoreType.FOURS);
        fives = new Score(ScoreType.FIVES);
        sixes = new Score(ScoreType.SIXES);
        threekind = new Score(ScoreType.THREEKIND);
        fourkind = new Score(ScoreType.FOURKIND);
        fullhouse = new Score(ScoreType.FULLHOUSE);
        smallstraight = new Score(ScoreType.SMALLSTRAIGHT);
        largestraight = new Score(ScoreType.LARGESTRAIGHT);
        generala = new Score(ScoreType.GENERALA);
        chance = new Score(ScoreType.CHANCE);
        upperbonus = new Score(ScoreType.UPPERBONUS);

        scoreTypesSelected = Sets.newHashSet();
        dieList = ImmutableList.of(die1, die2, die3, die4, die5);
        scoreTypes = Sets.immutableEnumSet(EnumSet.allOf(ScoreType.class));
        scoresNonBonus = ImmutableList.of(ones, twos, threes, fours, fives, sixes, threekind, fourkind,
                fullhouse, smallstraight, largestraight, generala, chance);

    }

    //
    // accessors/mutators
    //

    public int getTotalScore() {
        int total = 0;
        for (Score score : scoresNonBonus) {
            total += score.value;
        }
        total += upperbonus.value;
        return total;
    }

    public int getUpperSectionScore() {
        int total = 0;
        total += ones.value;
        total += twos.value;
        total += threes.value;
        total += fours.value;
        total += fives.value;
        total += sixes.value;
        return total;
    }

    public void incrementCurrentRoll() {
        this.currentRoll++;
    }

    public void resetCurrentRoll() {
        this.currentRoll = 0;
    }

    public int getCurrentRoll() {
        return currentRoll;
    }

    public Score getScore(ScoreType scoreType) {
        for (Score score : scoresNonBonus) {
            if (score.scoreType == scoreType) {
                return score;
            }
        }
        if (scoreType == ScoreType.UPPERBONUS) {
            return upperbonus;
        }
        return null;
    }

    public void setUpperBonusValue(int value) {
        this.upperbonus.value = value;
    }

    //
    // display
    //

    public String displayDieState() {
        StringBuilder sb = new StringBuilder();
        int position = 0;
        for (Die die : dieList) {
            position++;
            sb.append("DIE" + position + " " + die.value + " (selected:" + die.selected + ")\n" );
        }

        return sb.toString();
    }

    public String displayScoreCard() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        for (ScoreType scoreType : scoreTypes) {
            Score score = getScore(scoreType);
            boolean scoreTypeSelected = scoreTypesSelected.contains(scoreType);
            formatter.format("%1$-3s %2$-15s %3$-12s %4$-10s\n",
                    scoreType.getPosition() + ")", scoreType.name(), "selected:" + scoreTypeSelected, score.value);
        }
        //sb.deleteCharAt(sb.length() - 1);
        sb.append("TOTAL:" + this.getTotalScore());
        return sb.toString();
    }

    //
    // object
    //


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dieList", dieList)
                .add("die1", die1)
                .add("die2", die2)
                .add("die3", die3)
                .add("die4", die4)
                .add("die5", die5)
                .add("scoreTypes", scoreTypes)
                .add("scoreTypesSelected", scoreTypesSelected)
                .add("scoresNonBonus", scoresNonBonus)
                .add("ones", ones)
                .add("twos", twos)
                .add("threes", threes)
                .add("fours", fours)
                .add("fives", fives)
                .add("sixes", sixes)
                .add("threekind", threekind)
                .add("fourkind", fourkind)
                .add("fullhouse", fullhouse)
                .add("smallstraight", smallstraight)
                .add("largestraight", largestraight)
                .add("generala", generala)
                .add("chance", chance)
                .add("upperbonus", upperbonus)
                .add("currentRoll", currentRoll)
                .toString();
    }
}


