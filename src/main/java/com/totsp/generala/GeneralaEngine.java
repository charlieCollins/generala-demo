package com.totsp.generala;

import java.util.ArrayList;
import java.util.List;

public class GeneralaEngine implements IGeneralaEngine {

    private static final int UPPER_BONUS_THRESHOLD = 63;
    private static final int UPPER_BONUS_VALUE = 35;
    private static final int FULL_HOUSE_VALUE = 25;
    private static final int SMALL_STRAIGHT_VALUE = 30;
    private static final int LARGE_STRAIGHT_VALUE = 40;
    private static final int GENERALA_VALUE = 50;

    private Data data;

    public GeneralaEngine() {
        this.data = new Data();
    }

    // new game
    public void resetData() {
        data = new Data();
    }

    //
    // accessors/mutators
    //

    public Data getData() {
        return data;
    }

    //
    // game intreface impl
    //

    public void rollDice() {

        if (data.getCurrentRoll() >= Data.NUM_DIE_ROLLS_PER_TURN) {
            System.err.println("Invalid roll, can't roll more than 3 times per turn");
            return;
        }

        data.incrementCurrentRoll();

        for (Die die: data.dieList) {
            if (!die.selected) {
                die.value = randomOneSix();
            }
        }
    }

    public String displayDice() {
        StringBuilder sb = new StringBuilder();
        sb.append("current dice:\n");
        sb.append(data.displayDieState());
        return sb.toString();
    }

    public String displayScoreCard() {
        StringBuilder sb = new StringBuilder();
        sb.append("current scorecard:\n");
        sb.append(data.displayScoreCard());
        return sb.toString();
    }

    public void toggleDieSelected(int position) {

        if (position < 1 || position > 5) {
            System.err.println("invalid die selection, range 1-5");
            return;
        }

        switch (position) {
            case 1:
                data.die1.selected = !data.die1.selected;
                break;
            case 2:
                data.die2.selected = !data.die2.selected;
                break;
            case 3:
                data.die3.selected = !data.die3.selected;
                break;
            case 4:
                data.die4.selected = !data.die4.selected;
                break;
            case 5:
                data.die5.selected = !data.die5.selected;
                break;
        }
    }

    public void newTurn() {

        checkAndSetUpperBonus();

        for (Die die : data.dieList) {
            die.selected = false;
            die.value = 0;
        }

        data.resetCurrentRoll();
    }

    public boolean chooseScore(ScoreType scoreType) {

        if (data.scoreTypesSelected.contains(scoreType)) {
            return false;
        }

        int scoreValue = getScoreValue(scoreType);
        Score score = data.getScore(scoreType);
        score.value = scoreValue;
        data.scoreTypesSelected.add(scoreType);
        return true;
    }

    public int getTotalScore() {
        return data.getTotalScore();
    }

    //
    // private utils to determine scores
    //

    private void checkAndSetUpperBonus() {
        if (!data.scoreTypesSelected.contains(ScoreType.UPPERBONUS)) {
            if (data.getUpperSectionScore() >= UPPER_BONUS_THRESHOLD) {
                data.setUpperBonusValue(UPPER_BONUS_VALUE);
                data.scoreTypesSelected.add(ScoreType.UPPERBONUS);
                System.out.println("upper bonus SET");
            }
        }
    }

    private int getScoreValue(ScoreType scoreType) {

        // 1-6 simples 1s-6s
        int scoreValue = 0;
        if ((scoreType == ScoreType.ONES) || (scoreType == ScoreType.TWOS)
                || (scoreType == ScoreType.THREES) || (scoreType == ScoreType.FOURS)
                || (scoreType == ScoreType.FIVES)  || (scoreType == ScoreType.SIXES)) {
            // can use position for 1-6 only for adding up values
            scoreValue = getTotalScoreForDieMatching(scoreType.getPosition());
        } else {
            switch (scoreType) {
                case THREEKIND: {
                    if (numberOfAnyKindPresent(3)) {
                        scoreValue = sumAllDie();
                    }
                    break;
                }
                case FOURKIND: {
                    if (numberOfAnyKindPresent(4)) {
                        scoreValue = sumAllDie();
                    }
                    break;
                }
                case FULLHOUSE: {
                    if (fullHousePresent()) {
                        scoreValue = FULL_HOUSE_VALUE;
                    }
                    break;
                }
                case SMALLSTRAIGHT: {
                    if (smallStraightPresent()) {
                        scoreValue = SMALL_STRAIGHT_VALUE;
                    }
                    break;
                }
                case LARGESTRAIGHT: {
                    if (largeStraightPresent()) {
                        scoreValue = LARGE_STRAIGHT_VALUE;
                    }
                    break;
                }
                case GENERALA: {
                    if (numberOfAnyKindPresent(5)) {
                        scoreValue = GENERALA_VALUE;
                    }
                    break;
                }
                case CHANCE: {
                    scoreValue = sumAllDie();
                    break;
                }
            }
        }
        return scoreValue;
    }

    private boolean fullHousePresent() {

        int ones = getNumberOfDieMatching(1);
        int twos = getNumberOfDieMatching(2);
        int threes = getNumberOfDieMatching(3);
        int fours = getNumberOfDieMatching(4);
        int fives = getNumberOfDieMatching(5);
        int sixes = getNumberOfDieMatching(6);

        if (ones == 3) {
            if (numberOfAnyOtherKindPresent(2, 1)) {
                return true;
            }
        } else if (twos == 3) {
            if (numberOfAnyOtherKindPresent(2, 2)) {
                return true;
            }
        } else if (threes == 3) {
            if (numberOfAnyOtherKindPresent(2, 3)) {
                return true;
            }
        } else if (fours == 3) {
            if (numberOfAnyOtherKindPresent(2, 4)) {
                return true;
            }
        } else if (fives == 3) {
            if (numberOfAnyOtherKindPresent(2, 5)) {
                return true;
            }
        } else if (sixes == 3) {
            if (numberOfAnyOtherKindPresent(2, 6)) {
                return true;
            }
        }
        return false;
    }

    private boolean largeStraightPresent() {
        boolean result = false;
        int[] dieValues = {data.die1.value, data.die2.value, data.die3.value, data.die4.value, data.die5.value};

        // 1,2,3,4,5 version
        if (intInArray(dieValues, 1) && intInArray(dieValues, 2) && intInArray(dieValues, 3)
                && intInArray(dieValues, 4) && intInArray(dieValues, 5)) {
            result = true;
        }
        // 2,3,4,5,6 version
        else if (intInArray(dieValues, 2) && intInArray(dieValues, 3) && intInArray(dieValues, 4)
                && intInArray(dieValues, 5) && intInArray(dieValues, 6)) {
            result = true;
        }
        return result;
    }

    private boolean smallStraightPresent() {
        boolean result = false;
        int[] dieValues = {data.die1.value, data.die2.value, data.die3.value, data.die4.value, data.die5.value};

        // 1,2,3,4 version
        if (intInArray(dieValues, 1) && intInArray(dieValues, 2) && intInArray(dieValues, 3)
                && intInArray(dieValues, 4)) {
            result = true;
        }
        // 2,3,4,5 version
        else if (intInArray(dieValues, 2) && intInArray(dieValues, 3) && intInArray(dieValues, 4)
                && intInArray(dieValues, 5)) {
            result = true;
        }
        // 3,4,5,6 version
        else if (intInArray(dieValues, 3) && intInArray(dieValues, 4) && intInArray(dieValues, 5)
                && intInArray(dieValues, 6)) {
            result = true;
        }

        return result;
    }

    private boolean numberOfAnyKindPresent(int threshold) {
        boolean result = false;
        if (getNumberOfDieMatching(1) >= threshold) {
            result = true;
        } else if (getNumberOfDieMatching(2) >= threshold) {
            result = true;
        } else if (getNumberOfDieMatching(3) >= threshold) {
            result = true;
        } else if (getNumberOfDieMatching(4) >= threshold) {
            result = true;
        } else if (getNumberOfDieMatching(5) >= threshold) {
            result = true;
        } else if (getNumberOfDieMatching(6) >= threshold) {
            result = true;
        }
        return result;
    }

    private boolean numberOfAnyOtherKindPresent(int threshold, int excludeNumber) {
        boolean result = false;
        if ((excludeNumber != 1) && (getNumberOfDieMatching(1) >= threshold)) {
            result = true;
        }
        if ((excludeNumber != 2) && (getNumberOfDieMatching(2) >= threshold)) {
            result = true;
        }
        if ((excludeNumber != 3) && (getNumberOfDieMatching(3) >= threshold)) {
            result = true;
        }
        if ((excludeNumber != 4) && (getNumberOfDieMatching(4) >= threshold)) {
            result = true;
        }
        if ((excludeNumber != 5) && (getNumberOfDieMatching(5) >= threshold)) {
            result = true;
        }
        if ((excludeNumber != 6) && (getNumberOfDieMatching(6) >= threshold)) {
            result = true;
        }
        return result;
    }

    private int getNumberOfDieMatching(int value) {
        int result = 0;
        for (Die die : data.dieList) {
            if (die.value == value) {
                result++;
            }
        }
        return result;
    }

    private int getTotalScoreForDieMatching(int value) {
        int result = 0;
        for (Die die : data.dieList) {
            if (die.value == value) {
                result += die.value;
            }
        }
        return result;
    }

    private int sumAllDie() {
        int result = 0;
        for (Die die : data.dieList) {
            result += die.value;
        }
        return result;
    }

    //
    // private util
    //

    private int randomOneSix() {
        return (int) ((Math.random() * 6) + 1);
    }

    // don't need binary search or collection contains or such, very small arrays that are fixed, just do it
    private boolean intInArray(int[] array, int value) {
        boolean result = false;
        for (int i = 0; i < array.length; i++) {
            int j = array[i];
            if (j == value) {
                result = true;
                break;
            }
        }
        return result;
    }
}