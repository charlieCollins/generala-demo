package com.totsp.generala;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cecollins on 6/23/16.
 */
public class Controller implements IController {

    // TODO constants, magic nums, etc

    private static final int UPPER_BONUS_THRESHOLD = 63;
    private static final int UPPER_BONUS_AMOUNT = 35;

    private Data data;

    public Controller() {
        this.data = new Data();
    }

    /*
    public void addChangeListener(final GenericChangeListener listener) {
        listeners.add(listener);
    }
    */

    //
    // accessors/mutators
    //

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    //
    // game intreface impl
    //

    public void resetData() {
        data = new Data();
        /*
        for (int i = 0; i < listeners.size(); i++) {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
        */
    }

    public void rollDice() {
        data.currentRoll++;

        if (data.currentRoll > 3) {
            System.out.println("can't roll more than 3 times per turn");
            return;
        }

        for (Die die: data.dieList) {
            if (!die.selected) {
                die.value = randomOneSix();
            }
        }

        // else exception, can't keep rolling after 3 per turn

        /*
        for (int i = 0; i < listeners.size(); i++) {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
        */
    }

    public String displayDice() {
        StringBuilder sb = new StringBuilder();
        sb.append("current dice state:\n");
        sb.append(data.displayDieState());
        return sb.toString();
    }

    public void selectDie(int position) {
        switch (position) {
            case 1:
                data.die1.selected = true;
                break;
            case 2:
                data.die2.selected = true;
                break;
            case 3:
                data.die3.selected = true;
                break;
            case 4:
                data.die4.selected = true;
                break;
            case 5:
                data.die5.selected = true;
                break;
        }
    }

    public void newTurn() {

        for (Die die : data.dieList) {
            die.selected = false;
            die.value = 0;
        }

        data.currentRoll = 0;

        /*
        for (int i = 0; i < listeners.size(); i++) {
            GenericChangeListener listener = (GenericChangeListener) listeners.get(i);
            listener.onChange(data);
        }
        */
    }

    // select a score AND mutate data score value (update) AND mark die selections
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

        // select die for chosen scoreType
        List<Integer> positions = getPositionsForDieMatching(scoreType.getValue());
        for (int position : positions) {
            selectDie(position);
        }
    }

    public int getTotalScore() {
        int total = 0;

        for (Score score : data.scoreListNonBonus) {
            total+= score.value;
        }

        checkAndSetUpperBonus();
        checkAndSetLowerBonus();

        total += data.upperbonus.value;
        total += data.lowerbonus.value;

        return total;
    }

    //
    // private utils to determine scores
    //

    private void checkAndSetUpperBonus() {
        if (!data.scoresSelected.contains(ScoreType.UPPERBONUS)) {
            if ((data.ones.value + data.twos.value + data.threes.value + data.fours.value
                    + data.fives.value + data.sixes.value) >= UPPER_BONUS_THRESHOLD) {
                data.upperbonus.value = UPPER_BONUS_AMOUNT;
                data.scoresSelected.add(ScoreType.UPPERBONUS);
                System.out.println("upper bonus SET");
            }
        }
    }

    private void checkAndSetLowerBonus() {
        // TODO lower bonus
    }

    private int getScoreValue(ScoreType scoreType) {

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

    private boolean fullHousePresent() {
        boolean result = false;

        int ones = getNumberOfDieMatching(1);
        int twos = getNumberOfDieMatching(2);
        int threes = getNumberOfDieMatching(3);
        int fours = getNumberOfDieMatching(4);
        int fives = getNumberOfDieMatching(5);
        int sixes = getNumberOfDieMatching(6);

        // TODO short circuit these
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
        if (data.die1.value == value) {
            result++;
        }
        if (data.die2.value == value) {
            result++;
        }
        if (data.die3.value == value) {
            result++;
        }
        if (data.die4.value == value) {
            result++;
        }
        if (data.die5.value == value) {
            result++;
        }
        return result;
    }

    private int getTotalScoreForDieMatching(int value) {
        int result = 0;
        if (data.die1.value == value) {
            result += data.die1.value;
        }
        if (data.die2.value == value) {
            result += data.die2.value;
        }
        if (data.die3.value == value) {
            result += data.die3.value;
        }
        if (data.die4.value == value) {
            result += data.die4.value;
        }
        if (data.die5.value == value) {
            result += data.die5.value;
        }
        return result;
    }

    private List<Integer> getPositionsForDieMatching(int value) {
        List<Integer> positions = new ArrayList<Integer>();
        if (data.die1.value == value) {
            positions.add(1);
        }
        if (data.die2.value == value) {
            positions.add(2);
        }
        if (data.die3.value == value) {
            positions.add(3);
        }
        if (data.die4.value == value) {
            positions.add(4);
        }
        if (data.die5.value == value) {
            positions.add(5);
        }
        return positions;
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