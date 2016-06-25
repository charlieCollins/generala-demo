package com.totsp.generala;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.Scanner;

/**
 * Created by cecollins on 6/23/16.
 */
public class MainTest {

    public static void main(String[] args) {
        System.out.println("");
        System.out.println("*** hello MainTest");

        test();


        /*
        // observable
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        // subscriber
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) { System.out.println(s); }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };

        //wiring
        myObservable.subscribe(mySubscriber);
        */
    }


    private static void test() {

        System.out.println("BEGIN MANUAL GAME TEST");

        IController controller = new Controller();
        controller.displayDice();

        // game loop
        while (true) {

            // if GAME is over (all scores selected), exit
            if (controller.getData().numScoresSelected == Data.NUM_SCORES_SELECTED_COMPLETE_GAME) {
                System.out.println("ALL SCORES SELECTED, GAME OVER");
                System.out.println("TOTAL SCORE:" + controller.getTotalScore());
                break;
            }

            // if TURN is over (3 die rolls) then select score and move to next turn
            if (controller.getData().currentRoll > Data.NUM_DIE_ROLLS_PER_TURN) {
                System.out.println("current turn is over, 3 rolls, please select a score");
                System.out.println("TODO ");
                controller.newTurn();
            }

            // if game is valid and turn is NOT over, roll dice
            int action = readInput("roll dice (press 1 to roll) " +
                    "\n(rolls remaining this turn:" + (3 - controller.getData().currentRoll) + ")");
            if (action == 1) {
                controller.rollDice();
                controller.displayDice();
            } else {
                System.out.println("invalid action, game over");
            }

            while (true) {
                int selected = readInput("select die (1-5, 0 for done)");
                if (selected == 0) {
                    break;
                }
                controller.selectDie(selected);
                System.out.println("selected " + selected);
            }
            // dice are selected
            controller.displayDice();

        }



        System.out.println("TOTAL SCORE:" + controller.getTotalScore());


    }

    private static int readInput(String prompt) {

        System.out.println(prompt + ":");

        int i = -1;
        Scanner console = new Scanner(System.in);
        String s = console.nextLine();
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.err.println("error parsing input:" + e.getMessage());
        }


        return i;
    }


}
