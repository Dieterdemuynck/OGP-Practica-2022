package test;

import main.java.ingredient.*;
import main.java.ingredient.exception.IllegalNameException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A JUnit (4) test class for testing the non-private methods of the AlchemicIngredient Class.
 *
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class AlchemicIngredientTest {
    AlchemicIngredient water;
    AlchemicIngredient milk;
    AlchemicIngredient chocolate;

    @Before
    public void setUpFixture() {
        water = new AlchemicIngredient(1);
        milk = new AlchemicIngredient(1, Unit.Bottle, new long[] {0,4}, "Milk", State.Liquid);
        chocolate = new AlchemicIngredient(1, Unit.Box, new long[] {0,15}, "Chocolate", State.Powder);
    }

    /* *********************************************************
     * CONSTRUCTOR TESTS - for all devices
     * *********************************************************/

    /* ***********************
     * CONSTRUCTOR TESTS - AlchemicIngredient(quantity, unit, standardTemperature, name, standardState)
     * **********************/
    @Test
    public void testConstructor_long_legalCase_oneWordName() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Som", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Som", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_long_legalCase_multipleWordsName() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "So So", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("So So", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_long_legalCase_nameWithApostrophe() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Something's", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Something's", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructor_long_illegalCase_falseUnitState() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "Something", State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("something", ingredient.getName());
    }

    @Test
    public void testConstructor_long_illegalCase_falseTemperature_bothCooledAndHeated() {
        long[] standardTemperatureIngredient = {15, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Something", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_long_illegalCase_falseTemperature_tooHot() {
        long[] standardTemperatureIngredient = {0, 10001};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Something", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_oneWord_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "So", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_multipleWords_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "So S", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_allLowerCase() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "something", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_multipleWords_notAllStartingWithUpperCase() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Something something", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "SomeThing", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_multipleWords_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Something SomeThing", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_numbers() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Something1", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_punctuationMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Something!", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_specialMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient,
                "Something@", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    /* ***********************
     * CONSTRUCTOR TESTS - AlchemicIngredient(quantity, unit, standardTemperature, name, standardState, currentState)
     * **********************/
    @Test
    public void testConstructor_longWithCurrentState_legalCase_oneWordName() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                "Som", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Som", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithCurrentState_legalCase_multipleWordsName() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                "So So", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("So So", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithCurrentState_legalCase_nameWithApostrophe() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                "Something's", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Something's", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    /*
    @Test
    public void testConstructor_longWithCurrentState_illegalCase_falseUnitState() { //TODO NOT WRIGHT IN CODE
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("something", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }*/

    @Test
    public void testConstructor_longWithCurrentState_illegalCase_falseTemperature_bothCooledAndHeated() { //DEFAULT IS {0,20}
        long[] standardTemperatureIngredient = {15, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                "Something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithCurrentState_illegalCase_falseTemperature_tooHot() {
        long[] standardTemperatureIngredient = {0, 10001};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                "Something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_oneWord_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "So", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_multipleWords_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "So S", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_allLowerCase() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_multipleWords_notAllStartingWithUpperCase() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "Something something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "SomeThing", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_multipleWords_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "Something SomeThing", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_numbers() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "Something1", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_punctuationMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "Something!", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentState_illegalCase_falseName_specialMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                "Something@", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    /* ***********************
     * CONSTRUCTOR TESTS - AlchemicIngredient(quantity, unit, standardTemperature, name, standardState, currentState,
     *                          currentTemperature)
     * **********************/
    @Test
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_legalCase_oneWordName() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                currentTemperatureIngredient, "Som", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Som", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_legalCase_multipleWordsName() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                currentTemperatureIngredient, "So So", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("So So", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_legalCase_nameWithApostrophe() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something's", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Something's", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseUnitState() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("something", ingredient.getName());
    }


    @Test
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseStandardTemperature_bothCooledAndHeated() {
        long[] standardTemperatureIngredient = {15, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithCurrentStateAndCurrentState_illegalCase_falseStandardTemperature_tooHot() {
        long[] standardTemperatureIngredient = {0, 10001};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseCurrentTemperature_bothCooledAndHeated() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {10, 10};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithCurrentStateAndCurrentState_illegalCase_falseCurrentTemperature_tooHot() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 10001};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Box, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_oneWord_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "So", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_multipleWords_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "So S", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_allLowerCase() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_multipleWords_notAllStartingWithUpperCase() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something something", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "SomeThing", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_multipleWords_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something SomeThing", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_numbers() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something1", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_punctuationMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something!", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithCurrentStateAndCurrentTemperature_illegalCase_falseName_specialMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        long[] currentTemperatureIngredient = {0, 0};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle, standardTemperatureIngredient,
                currentTemperatureIngredient, "Something@", State.Liquid, State.Powder);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(currentTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(currentTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Powder, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    /* ***********************
     * CONSTRUCTOR TESTS - AlchemicIngredient(quantity, standardTemperature, name, standardState)
     * **********************/
    @Test
    public void testConstructor_longWithoutUnit_legalCase_oneWordName() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient, "Som",
                State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Som", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithoutUnit_legalCase_multipleWordsName() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient, "So So",
                State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("So So", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithoutUnit_legalCase_nameWithApostrophe() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "Something's", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Something's", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithoutUnit_illegalCase_falseTemperature_bothCooledAndHeated() {
        long[] standardTemperatureIngredient = {15, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "Something", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_longWithoutUnit_illegalCase_falseTemperature_tooHot() {
        long[] standardTemperatureIngredient = {0, 10001};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "Something", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_oneWord_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient, "So",
                State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_multipleWords_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient, "So S",
                State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_allLowerCase() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "something", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_multipleWords_notAllStartingWithUpperCase() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "Something something", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "SomeThing", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_multipleWords_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "Something SomeThing", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_numbers() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "Something1", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_punctuationMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "Something!", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test(expected = IllegalNameException.class)
    public void testConstructor_longWithoutUnit_illegalCase_falseName_specialMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, standardTemperatureIngredient,
                "Something@", State.Liquid);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    /* ***********************
     * CONSTRUCTOR TESTS - AlchemicIngredient(quantity, unit)
     * **********************/
    @Test
    public void testConstructor_short_legalCase() {
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Bottle);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Bottle, ingredient.getUnit());
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Water", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructor_short_illegalCase() {
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Box);
        assertEquals(15, ingredient.getAmount());
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Water", ingredient.getName());
    }


    /* ***********************
     * CONSTRUCTOR TESTS - AlchemicIngredient(quantity)
     * **********************/
    @Test
    public void testConstructor_shortWithoutUnit() {
        AlchemicIngredient ingredient = new AlchemicIngredient(15);
        assertEquals(15, ingredient.getAmount());
        assertEquals(Unit.Spoon, ingredient.getUnit());
        assertEquals(0, ingredient.getTemperature()[0]);
        assertEquals(20, ingredient.getTemperature()[1]);
        assertEquals(0, ingredient.getStandardTemperature()[0]);
        assertEquals(20, ingredient.getStandardTemperature()[1]);
        assertEquals("Water", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    /* *********************************************************
     * TEMPERATURE TESTS - for CoolBox, Oven and TemperatureDevice
     * *********************************************************/
    @Test
    public void testHeat_temperatureAboveZero() {
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(20, water.getTemperature()[1]);
        water.heat(100);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(120, water.getTemperature()[1]);
    }

    @Test
    public void testHeat_temperatureBelowZero() {
        //For this we assume that the cool method works, the tests follow below
        water.cool(30);
        assertEquals(10, water.getTemperature()[0]);
        assertEquals(0, water.getTemperature()[1]);
        water.heat(100);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(90, water.getTemperature()[1]);
    }

    @Test
    public void testHeat_illegalCase() {
        water.heat(10000);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(20, water.getTemperature()[1]);
    }

    @Test
    public void testCool_temperatureAboveZero() {
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(20, water.getTemperature()[1]);
        water.cool(100);
        assertEquals(80, water.getTemperature()[0]);
        assertEquals(0, water.getTemperature()[1]);
    }


    @Test //todo, dit mss nie nuttig...
    public void testCool_temperatureBelowZero() {
        //For this we assume that the cool method works to get the temperature below zero
        water.cool(20);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(0, water.getTemperature()[1]);
        water.cool(100);
        assertEquals(100, water.getTemperature()[0]);
        assertEquals(0, water.getTemperature()[1]);
    }

    @Test
    public void testCool_illegalCase() {
        water.cool(10100);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(20, water.getTemperature()[1]);
    }

    @Test
    public void testIsValidTemperature() {
        assertTrue(AlchemicIngredient.isValidTemperature(new long[] {0,20}));
        assertFalse(AlchemicIngredient.isValidTemperature(new long[] {10001,0}));
        assertFalse(AlchemicIngredient.isValidTemperature(new long[] {1,1}));
    }

    /* *********************************************************
     * MIX WITH TESTS - for Kettle
     * *********************************************************/
    @Test
    public void testMixWith_twoIngredientsCase(){
        AlchemicIngredient chocolateWithMilk = milk.mixWith(chocolate);
        assertEquals("Chocolate mixed with Milk", chocolateWithMilk.getName());
        assertEquals(State.Powder, chocolateWithMilk.getState());
        assertEquals(Unit.Spoon, chocolateWithMilk.getUnit());
        assertEquals(57, chocolateWithMilk.getAmount());
        assertEquals(0, chocolateWithMilk.getTemperature()[0]);
        assertEquals(12, chocolateWithMilk.getTemperature()[1]);
    }

    @Test
    public void testMixWith_moreIngredientsCase_caseOfThree(){
        List<AlchemicIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(milk);
        ingredientList.add(water);
        AlchemicIngredient chocolateWithMilkAndWater = chocolate.mixWith(ingredientList);
        assertEquals("Chocolate mixed with Milk and Water", chocolateWithMilkAndWater.getName());
        assertEquals(State.Liquid, chocolateWithMilkAndWater.getState());
        assertEquals(Unit.Spoon, chocolateWithMilkAndWater.getUnit());
        assertEquals(58, chocolateWithMilkAndWater.getAmount());
        assertEquals(0, chocolateWithMilkAndWater.getTemperature()[0]);
        assertEquals(12, chocolateWithMilkAndWater.getTemperature()[1]);
    }

    @Test
    public void testMixWith_moreIngredientsCase_caseOfFour(){
        AlchemicIngredient sugar = new AlchemicIngredient(2, Unit.Sachet, new long[] {0,18}, "Sugar",
                State.Powder);
        List<AlchemicIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(milk);
        ingredientList.add(water);
        ingredientList.add(sugar);
        AlchemicIngredient chocolateWithMilkSugarAndWater = chocolate.mixWith(ingredientList);
        assertEquals("Chocolate mixed with Milk, Sugar and Water", chocolateWithMilkSugarAndWater.getName());
        assertEquals(State.Liquid, chocolateWithMilkSugarAndWater.getState());
        assertEquals(Unit.Spoon, chocolateWithMilkSugarAndWater.getUnit());
        assertEquals(72, chocolateWithMilkSugarAndWater.getAmount());
        assertEquals(0, chocolateWithMilkSugarAndWater.getTemperature()[0]);
        assertEquals(13, chocolateWithMilkSugarAndWater.getTemperature()[1]);
    }


    /* *********************************************************
     * COPY ALL VAL(ue)S EXCEPT TESTS - for Transmogrifier
     * *********************************************************/

    @Test
    public void testCopyAllValsExcept_shortVersion(){
        AlchemicIngredient waterCopy = water.copyAllValsExcept(15,Unit.Bottle);
        assertEquals(15,waterCopy.getAmount());
        assertEquals(Unit.Bottle, waterCopy.getUnit());
        assertEquals(water.getState(), waterCopy.getState());
        assertEquals(water.getTemperature()[0], waterCopy.getTemperature()[0]);
        assertEquals(water.getTemperature()[1], waterCopy.getTemperature()[1]);
        assertEquals(water.getStandardState(), waterCopy.getStandardState());
        assertEquals(water.getStandardTemperature()[0], waterCopy.getStandardTemperature()[0]);
        assertEquals(water.getStandardTemperature()[1], waterCopy.getStandardTemperature()[1]);
    }


    @Test
    public void testCopyAllValsExcept_withCurrentState(){
        AlchemicIngredient waterCopy = water.copyAllValsExcept(15,Unit.Sachet, State.Powder);
        assertEquals(15,waterCopy.getAmount());
        assertEquals(Unit.Sachet, waterCopy.getUnit());
        assertEquals(State.Powder, waterCopy.getState());
        assertEquals(water.getTemperature()[0], waterCopy.getTemperature()[0]);
        assertEquals(water.getTemperature()[1], waterCopy.getTemperature()[1]);
        assertEquals(water.getStandardState(), waterCopy.getStandardState());
        assertEquals(water.getStandardTemperature()[0], waterCopy.getStandardTemperature()[0]);
        assertEquals(water.getStandardTemperature()[1], waterCopy.getStandardTemperature()[1]);
    }

    @Test
    public void testCopyAllValsExcept_withCurrentStateAndCurrentTemperature(){
        AlchemicIngredient waterCopy = water.copyAllValsExcept(15,Unit.Sachet, State.Powder, new long[] {0,5});
        assertEquals(15,waterCopy.getAmount());
        assertEquals(Unit.Sachet, waterCopy.getUnit());
        assertEquals(State.Powder, waterCopy.getState());
        assertEquals(0, waterCopy.getTemperature()[0]);
        assertEquals(5, waterCopy.getTemperature()[1]);
        assertEquals(water.getStandardState(), waterCopy.getStandardState());
        assertEquals(water.getStandardTemperature()[0], waterCopy.getStandardTemperature()[0]);
        assertEquals(water.getStandardTemperature()[1], waterCopy.getStandardTemperature()[1]);
    }

}

