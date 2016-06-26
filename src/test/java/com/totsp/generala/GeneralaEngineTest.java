package com.totsp.generala;

import org.junit.Assert;
import org.junit.Test;

public class GeneralaEngineTest {

   @Test
   public void testRollDice() {
        IGeneralaEngine controller = new GeneralaEngine();
        controller.newTurn();;
        Assert.assertEquals(0, controller.getData().getCurrentRoll());
        controller.rollDice();
        Assert.assertEquals(1, controller.getData().getCurrentRoll());
        controller.rollDice();
        Assert.assertEquals(2, controller.getData().getCurrentRoll());
        controller.rollDice();
        Assert.assertEquals(3, controller.getData().getCurrentRoll());
        controller.rollDice();
        Assert.assertEquals(3, controller.getData().getCurrentRoll());
    }

    @Test
    public void testToggleDieSelected() {
        IGeneralaEngine controller = new GeneralaEngine();
        controller.newTurn();;
        controller.rollDice();
        controller.toggleDieSelected(1);
        Assert.assertTrue(controller.getData().die1.selected);
        controller.toggleDieSelected(1);
        Assert.assertFalse(controller.getData().die1.selected);
        controller.toggleDieSelected(1);
        Assert.assertTrue(controller.getData().die1.selected);
        controller.rollDice();
        Assert.assertTrue(controller.getData().die1.selected);
        Assert.assertFalse(controller.getData().die2.selected);
    }

    @Test
    public void testChooseScore() {
        IGeneralaEngine controller = new GeneralaEngine();
        controller.newTurn();;
        controller.rollDice();
        controller.toggleDieSelected(1);
        controller.toggleDieSelected(2);
        controller.rollDice();
        Assert.assertTrue(controller.chooseScore(ScoreType.ONES));
        Assert.assertFalse(controller.chooseScore(ScoreType.ONES)); // can't choose same twice
        Assert.assertTrue(controller.getData().scoreTypesSelected.size() == 1);
    }

}