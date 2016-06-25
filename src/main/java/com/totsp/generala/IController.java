package com.totsp.generala;

/**
 * Created by cecollins on 6/24/16.
 */
public interface IController {


    void rollDice();
    String displayDice();
    void selectDie(int position);
    void chooseScore(ScoreType scoreType);
    int getTotalScore();
    void newTurn();

    // should not be exposed
    Data getData();

}
