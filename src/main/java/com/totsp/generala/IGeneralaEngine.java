package com.totsp.generala;

public interface IGeneralaEngine {

    void newTurn();
    void rollDice();
    String displayDice();
    String displayScoreCard();
    void toggleDieSelected(int position);
    boolean chooseScore(ScoreType scoreType); // true if valid selection, false if already chosen
    int getTotalScore();

    // should not be exposed
    Data getData();

}
