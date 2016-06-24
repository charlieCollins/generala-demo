package com.totsp.generala;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cecollins on 6/23/16.
 */
public class ControllerTest {

    @Test
    public void test() {

        System.out.println("hello test");

        Controller controller = new Controller();

        System.out.println("BEFORE PROCESS ROLL");
        controller.data.printDieState();

        System.out.println("PROCESS ROLL");
        controller.processRoll();
        controller.data.printDieState();

        System.out.println("score values 1-6");
        System.out.println("1sValue:" + controller.getScoreValue(ScoreType.ONES));
        System.out.println("2sValue:" + controller.getScoreValue(ScoreType.TWOS));
        System.out.println("3sValue:" + controller.getScoreValue(ScoreType.THREES));
        System.out.println("4sValue:" + controller.getScoreValue(ScoreType.FOURS));
        System.out.println("5sValue:" + controller.getScoreValue(ScoreType.FIVES));
        System.out.println("6sValue:" + controller.getScoreValue(ScoreType.SIXES));

        System.out.println("CHOOSE 6s score:");
        controller.chooseScore(ScoreType.SIXES);
        // this should SELECT and die that matched 6s
        controller.data.printDieState();

        System.out.println("");


        //System.out.println("controller data:" + controller.data.toString());


        System.out.println("TOTAL SCORE:" + controller.getTotalScore());


    }

}