package test;

import main.java.ingredient.*;
import main.java.ingredient.exception.IllegalNameException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlchemicIngredientTest {
    AlchemicIngredient water;

    @Before
    public void setUpFixture() {
        water = new AlchemicIngredient(1);
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
     * TEMPERATURE TESTS - for Coolbox en Oven
     * todo moet getTemperature ook getest worden? Want das half getest in constructors en ook nu half met heat en cool... Geen idee hoe het grondiger zou kunnen
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
        //Hiervoor gaan we er vanuit dat de cool-methode werkt, de testen volgen hieronder.
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
        //Hiervoor gaan we er vanuit dat de cool-methode werkt om de temperatuur naar nul te krijgen.
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
}

