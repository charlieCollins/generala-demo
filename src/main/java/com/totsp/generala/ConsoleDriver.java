package com.totsp.generala;

import java.util.Scanner;

public class ConsoleDriver {

    public static void main(String[] args) {
        System.out.println("");

        consoleDriver();
    }

    private static void consoleDriver() {

        System.out.println("***GENERALA: GAME BEGIN***");

        IGeneralaEngine controller = new GeneralaEngine();

        // game loop
        while (true) {

            // if GAME is over (all scores selected), exit
            if (controller.getData().scoreTypesSelected.size() == GeneralaEngine.NUM_SCORES_SELECTED_COMPLETE_GAME) {
                System.out.println("ALL SCORES SELECTED, GAME OVER");
                System.out.println(controller.displayScoreCard());
                System.out.println("TOTAL SCORE:" + controller.getTotalScore());
                break;
            }

            // if game is valid and turn is NOT over, roll dice
            if (controller.getData().getCurrentRoll() == GeneralaEngine.NUM_DIE_ROLLS_PER_TURN) {
                //Data.NUM_DIE_ROLLS_PER_TURN - controller.getData().currentRoll == 0
                System.out.println("3 rolls up, turn over");
                System.out.println(controller.displayScoreCard());

                // select score at end of turn
                while (true) {
                    int action = readInput("select a score (using position number)");
                    ScoreType selectedScoreType = ScoreType.getByPosition(action);
                    if (selectedScoreType == null) {
                        System.err.println("invalid score type selection");
                    } else {
                        boolean validSelection = controller.chooseScore(selectedScoreType);
                        if (validSelection) {
                            System.out.println(controller.displayScoreCard());
                            System.out.println("score chosen, new turn starting");
                            controller.newTurn();
                            break;
                        } else {
                            System.out.println("invalid score chosen, already selected");
                        }
                    }
                }
            } else {
                // roll dice (and just loop if input is wrong)
                while(true) {
                    int action = readInput("\nroll dice (press 1 to roll) " +
                            "\n(rolls remaining this turn:"
                            + (GeneralaEngine.NUM_DIE_ROLLS_PER_TURN - controller.getData().getCurrentRoll()) + ")");
                    if (action == 1) {
                        controller.rollDice();
                        System.out.println(controller.displayDice());
                        // select dice
                        while (true) {
                            if (controller.getData().getCurrentRoll() == GeneralaEngine.NUM_DIE_ROLLS_PER_TURN) {
                                // third roll you don't need to select anything
                                break;
                            }
                            int selected = readInput("select/unselect die to hold (1-5, 0 for done)");
                            if (selected == 0) {
                                break;
                            }
                            controller.toggleDieSelected(selected);
                            System.out.println("selected/unselected " + selected);
                        }
                        // dice are selected
                        break;
                    } else {
                        System.out.println("invalid action (1 rolls dice)");
                    }
                }
            }

        }

        System.out.println("***GENERALA: GAME COMPLETE***");
    }

    private static int readInput(String prompt) {
        System.out.println(prompt);
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
