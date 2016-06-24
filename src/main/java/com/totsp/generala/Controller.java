package com.totsp.generala;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cecollins on 6/23/16.
 */
public class Controller {

    // TODO constants, etc

    private static final int UPPER_BONUS_THRESHOLD = 63;
    private static final int UPPER_BONUS_AMOUNT = 35;

    Data data;

    public Controller() {
        this.data = new Data();
    }

    /*
    public void addChangeListener(final GenericChangeListener listener) {
        listeners.add(listener);
    }
    */

    public void reset() {
        data = new Data();
        /*
        for (int i = 0; i < listeners.size(); i++) {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
        */
    }

    // get value for specific score type (do not CHOOSE it, just offer it)
    public int getScoreValue(ScoreType scoreType) {

        // 1-6 simples 1s-6s
        int scoreValue = 0;
        if ((scoreType == ScoreType.ONES) || (scoreType == ScoreType.TWOS)
                || (scoreType == ScoreType.THREES) || (scoreType == ScoreType.FOURS)
                || (scoreType == ScoreType.FIVES)  || (scoreType == ScoreType.SIXES)) {

            scoreValue = getTotalScoreForDieMatching(scoreType.getValue());

        } else {
            switch (scoreType) {
                case THREEKIND: {
                    // 3k
                    if (numberOfAnyKindPresent(3)) {
                        scoreValue = sumAllDie();
                    }
                    break;
                }
                case FOURKIND: {
                    // 4k
                    if (numberOfAnyKindPresent(4)) {
                        scoreValue = sumAllDie();
                    }
                    break;
                }
                case FULLHOUSE: {
                    // fh
                    if (fullHousePresent()) {
                        scoreValue = 25;
                    }
                    break;
                }
                case SMALLSTRAIGHT: {
                    // ss
                    if (smallStraightPresent()) {
                        scoreValue = 30;
                    }
                    break;
                }
                case LARGESTRAIGHT: {
                    // ls
                    if (largeStraightPresent()) {
                        scoreValue = 40;
                    }
                    break;
                }
                case GENERALA: {
                    // y
                    if (numberOfAnyKindPresent(5)) {
                        scoreValue = 50;
                    }
                    break;
                }
                case CHANCE: {
                    // c
                    scoreValue = sumAllDie();
                    break;
                }
            }
        }
        return scoreValue;
    }


    // select a score AND mutate data score value (update) AND mark selections
    public void chooseScore(ScoreType scoreType) {

        if (data.scoresSelected.contains(scoreType)) {
            System.out.println("can't pick same scoreType twice");
            return;
        }

        data.numScoresSelected++;
        data.scoresSelected.add(scoreType);

        int score = getScoreValue(scoreType);

        switch (scoreType) {
            case ONES: {
                data.ones.value = score;
                break;
            }
            case TWOS: {
                data.twos.value = score;
                break;
            }
            case THREES: {
                data.threes.value = score;
                break;
            }
            case FOURS: {
                data.fours.value = score;
                break;
            }
            case FIVES: {
                data.fives.value = score;
                break;
            }
            case SIXES: {
                data.sixes.value = score;
                break;
            }
            case THREEKIND: {
                data.threekind.value = score;
                break;
            }
            case FOURKIND: {
                data.fourkind.value = score;
                break;
            }
            case FULLHOUSE: {
                data.fullhouse.value = score;
                break;
            }
            case SMALLSTRAIGHT: {
                data.smallstraight.value = score;
                break;
            }
            case LARGESTRAIGHT: {
                data.largestraight.value = score;
                break;
            }
            case GENERALA: {
                data.generala.value = score;
                break;
            }
            case CHANCE: {
                data.chance.value = score;
                break;
            }
        }

        if (!data.scoresSelected.contains(ScoreType.UPPERBONUS)) {
            if ((data.ones.value + data.twos.value + data.threes.value + data.fours.value
                    + data.fives.value + data.sixes.value) >= UPPER_BONUS_THRESHOLD) {
                data.upperbonus.value = UPPER_BONUS_AMOUNT;
                data.scoresSelected.add(ScoreType.UPPERBONUS);
            }
        }

        // select die
        List<Integer> positions = getPositionsForDieMatching(scoreType.getValue());
        for (int position : positions) {
            selectDie(position);
        }

        // manually do next turn for debug
        ///nextTurn();
    }

    // mark a die selected
    public void selectDie(int position) {
        switch (position) {
            case 1:
                data.die1Selected = true;
                break;
            case 2:
                data.die2Selected = true;
                break;
            case 3:
                data.die3Selected = true;
                break;
            case 4:
                data.die4Selected = true;
                break;
            case 5:
                data.die5Selected = true;
                break;
        }
    }

    // rolls all non seleced dice
    public void processRoll() {
        data.currentRoll++;

        if (data.currentRoll > 3) {
            System.out.println("can't roll more than 3 times per turn");
            return;
        }

        if (!data.die1Selected) {
            //data.die1Selected = true;
            data.die1Value = roll();
        }

        if (!data.die2Selected) {
            //data.die2Selected = true;
            data.die2Value = roll();
        }

        if (!data.die3Selected) {
            //data.die3Selected = true;
            data.die3Value = roll();
        }

        if (!data.die4Selected) {
            //data.die4Selected = true;
            data.die4Value = roll();
        }

        if (!data.die5Selected) {
            //data.die5Selected = true;
            data.die5Value = roll();
        }

        // else exception, can't keep rolling after 3 per turn

        /*
        for (int i = 0; i < listeners.size(); i++) {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
        */
    }

    // resets die states for a new turn
    public void nextTurn() {

        data.die1Value = 0;
        data.die1Selected = false;
        data.die2Value = 0;
        data.die2Selected = false;
        data.die3Value = 0;
        data.die3Selected = false;
        data.die4Value = 0;
        data.die4Selected = false;
        data.die5Value = 0;
        data.die5Selected = false;
        data.currentRoll = 0;

        /*
        for (int i = 0; i < listeners.size(); i++) {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
        */
    }

    public int getTotalScore() {
        int score = 0;
        score += data.ones.value;
        score += data.twos.value;
        score += data.threes.value;
        score += data.fours.value;
        score += data.fives.value;
        score += data.sixes.value;
        score += data.fullhouse.value;
        score += data.smallstraight.value;
        score += data.largestraight.value;
        score += data.threekind.value;
        score += data.fourkind.value;
        score += data.generala.value;
        score += data.chance.value;
        score += data.upperbonus.value;
        score += data.lowerbonus.value;

        return score;
        //label.setText(String.valueOf(score));
    }



    //
    // private utils to determine scores
    //

    private boolean fullHousePresent() {
        boolean result = false;

        int ones = getNumberOfDieMatching(1);
        int twos = getNumberOfDieMatching(2);
        int threes = getNumberOfDieMatching(3);
        int fours = getNumberOfDieMatching(4);
        int fives = getNumberOfDieMatching(5);
        int sixes = getNumberOfDieMatching(6);

        if (ones == 3) {
            if (numberOfAnyOtherKindPresent(2, 1)) {
                result = true;
            }
        } else if (twos == 3) {
            if (numberOfAnyOtherKindPresent(2, 2)) {
                result = true;
            }
        } else if (threes == 3) {
            if (numberOfAnyOtherKindPresent(2, 3)) {
                result = true;
            }
        } else if (fours == 3) {
            if (numberOfAnyOtherKindPresent(2, 4)) {
                result = true;
            }
        } else if (fives == 3) {
            if (numberOfAnyOtherKindPresent(2, 5)) {
                result = true;
            }
        } else if (sixes == 3) {
            if (numberOfAnyOtherKindPresent(2, 6)) {
                result = true;
            }
        }
        return result;
    }

    private boolean largeStraightPresent() {
        boolean result = false;
        int[] dieValues = {data.die1Value, data.die2Value, data.die3Value, data.die4Value, data.die5Value};

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
        int[] dieValues = {data.die1Value, data.die2Value, data.die3Value, data.die4Value, data.die5Value};

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

    private int getNumberOfDieMatching(int value) {
        int result = 0;
        if (data.die1Value == value) {
            result++;
        }
        if (data.die2Value == value) {
            result++;
        }
        if (data.die3Value == value) {
            result++;
        }
        if (data.die4Value == value) {
            result++;
        }
        if (data.die5Value == value) {
            result++;
        }
        return result;
    }

    private int getTotalScoreForDieMatching(int value) {
        int result = 0;
        if (data.die1Value == value) {
            result += data.die1Value;
        }
        if (data.die2Value == value) {
            result += data.die2Value;
        }
        if (data.die3Value == value) {
            result += data.die3Value;
        }
        if (data.die4Value == value) {
            result += data.die4Value;
        }
        if (data.die5Value == value) {
            result += data.die5Value;
        }
        return result;
    }

    private List<Integer> getPositionsForDieMatching(int value) {
        List<Integer> positions = new ArrayList<Integer>();
        if (data.die1Value == value) {
            positions.add(1);
        }
        if (data.die2Value == value) {
            positions.add(2);
        }
        if (data.die3Value == value) {
            positions.add(3);
        }
        if (data.die4Value == value) {
            positions.add(4);
        }
        if (data.die5Value == value) {
            positions.add(5);
        }
        return positions;
    }

    private int sumAllDie() {
        int result = 0;
        result += data.die1Value;
        result += data.die2Value;
        result += data.die3Value;
        result += data.die4Value;
        result += data.die5Value;
        return result;
    }

    //
    // util
    //

    private int roll() {
        int i = 0;
        // return 1-6
        i = (int) ((Math.random() * 6) + 1);
        return i;
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