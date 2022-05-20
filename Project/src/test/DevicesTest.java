package test;

import main.java.device.Transmogrifier;
import main.java.ingredient.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DevicesTest {
    AlchemicIngredient water;

    @Before
    public void setUpFixture() {
        water = new AlchemicIngredient(15); // 15 spoons = 1 bottle
    }

    /* *********************************************************
     * CONSTRUCTOR TESTS - transmogrifier
     * *********************************************************/

    /* ***********************
     * CONSTRUCTOR TESTS - Transmogrifier(State)
     * **********************/

    @Test
    public void testConstructor_state_legalCase(){
        Transmogrifier transmogrifier = new Transmogrifier(State.Liquid);
        assertEquals(State.Liquid, transmogrifier.getState());
    }
}
