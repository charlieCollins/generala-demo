package com.totsp.generala;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneralaEngineTest {

   @Test
   public void testRollDice() {
        IGeneralaEngine controller = new GeneralaEngine();
        controller.newTurn();;
        assertEquals(0, controller.getData().getCurrentRoll());
        controller.rollDice();
        assertEquals(1, controller.getData().getCurrentRoll());
        controller.rollDice();
        assertEquals(2, controller.getData().getCurrentRoll());
        controller.rollDice();
        assertEquals(3, controller.getData().getCurrentRoll());
        controller.rollDice();
        assertEquals(3, controller.getData().getCurrentRoll());
    }

    @Test
    public void testToggleDieSelected() {
        IGeneralaEngine controller = new GeneralaEngine();
        controller.newTurn();;
        controller.rollDice();
        controller.toggleDieSelected(1);
        assertTrue(controller.getData().dieList.get(0).selected);
        controller.toggleDieSelected(1);
        assertFalse(controller.getData().dieList.get(0).selected);
        controller.toggleDieSelected(1);
        assertTrue(controller.getData().dieList.get(0).selected);
        controller.rollDice();
        assertTrue(controller.getData().dieList.get(0).selected);
        assertFalse(controller.getData().dieList.get(1).selected);
    }

    @Test
    public void testChooseScore() {
        IGeneralaEngine controller = new GeneralaEngine();
        controller.newTurn();;
        controller.rollDice();
        controller.toggleDieSelected(1);
        controller.toggleDieSelected(2);
        controller.rollDice();
        assertTrue(controller.chooseScore(ScoreType.ONES));
        assertFalse(controller.chooseScore(ScoreType.ONES)); // can't choose same twice
        assertTrue(controller.getData().scoreTypesSelected.size() == 1);
    }

}