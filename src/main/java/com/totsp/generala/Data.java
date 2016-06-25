package com.totsp.generala;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.*;

public class Data {

    public static final int NUM_SCORES_SELECTED_COMPLETE_GAME = 13;
    public static final int NUM_DIE_ROLLS_PER_TURN = 3;

    // TODO set accessibility private

    public ImmutableList<Die> dieList;
    public Die die1 = new Die();
    public Die die2 = new Die();
    public Die die3 = new Die();
    public Die die4 = new Die();
    public Die die5 = new Die();

    public ImmutableSet<ScoreType> scoreTypes;
    public Set<ScoreType> scoreTypesSelected;

    public ImmutableList<Score> scoresNonBonus;
    private Score ones = new Score(ScoreType.ONES);
    private Score twos = new Score(ScoreType.TWOS);
    private Score threes = new Score(ScoreType.THREES);
    private Score fours = new Score(ScoreType.FOURS);
    private Score fives = new Score(ScoreType.FIVES);
    private Score sixes = new Score(ScoreType.SIXES);
    private Score threekind = new Score(ScoreType.THREEKIND);
    private Score fourkind = new Score(ScoreType.FOURKIND);
    private Score fullhouse = new Score(ScoreType.FULLHOUSE);
    private Score smallstraight = new Score(ScoreType.SMALLSTRAIGHT);
    private Score largestraight = new Score(ScoreType.LARGESTRAIGHT);
    private Score generala = new Score(ScoreType.GENERALA);
    private Score chance = new Score(ScoreType.CHANCE);

    public Score upperbonus = new Score(ScoreType.UPPERBONUS);
    public Score lowerbonus = new Score(ScoreType.LOWERBONUS);

    private int currentRoll;

    public Data() {
        scoreTypesSelected = new HashSet<ScoreType>();
        dieList = ImmutableList.of(die1, die2, die3, die4, die5);
        scoreTypes = Sets.immutableEnumSet(EnumSet.allOf(ScoreType.class));
        scoresNonBonus = ImmutableList.of(ones, twos, threes, fours, fives, sixes, threekind, fourkind,
                fullhouse, smallstraight, largestraight, generala, chance);

    }

    // accessors/mutators

    public int getTotalScore() {
        int total = 0;
        for (Score score : scoresNonBonus) {
            total += score.value;
        }
        total += upperbonus.value;
        total += lowerbonus.value;

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

    public int getLowerSectionValue() {
        // TODO
        return 0;
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
        } else if (scoreType == ScoreType.LOWERBONUS) {
            return lowerbonus;
        }

        return null;
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
        return Objects.toStringHelper(this)
                .add("dieList", dieList + "\n")
                .add("die1", die1 + "\n")
                .add("die2", die2 + "\n")
                .add("die3", die3 + "\n")
                .add("die4", die4 + "\n")
                .add("die5", die5 + "\n")
                .add("scoreListNonBonus", scoresNonBonus + "\n")
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
                .add("scoresSelected", scoreTypesSelected + "\n")
                .add("currentRoll", currentRoll + "\n")
                .toString();
    }
}


