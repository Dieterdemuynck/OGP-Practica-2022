package test;

import main.java.ingredient.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//TODO We moeten enkel testen gebruikt in Devices
// -> constructoren?
// -> heat & cool
// -> getQuantity
// -> getTemperature
// -> getState
// -> dingen voor Kettle
// -> dingen vergeten?

public class AlchemicIngredientTest {
    AlchemicIngredient water;
    AlchemicIngredient milk;
    AlchemicIngredient lizardsTail;

    @Before
    public void setUpFixture() {
        water = new AlchemicIngredient(1);
        long[] standardTemperatureMilk = {0, 4};
        milk = new AlchemicIngredient(1, standardTemperatureMilk, "Milk", State.Liquid);
        long[] standardTemperatureLizardsTale = {0, 20};
        lizardsTail = new AlchemicIngredient(1, standardTemperatureMilk, "Lizard's Tail", State.Powder);
    }

    //TEST Constructor
    @Test
    public void testConstructor_long_legalCase_oneWordName() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_long_legalCase_multipleWordsName() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something Something", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Something Something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_long_legalCase_withBrakets() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something (Something)", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Something (Something)", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test
    public void testConstructor_long_legalCase_withApostrophe() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something 'some", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("Something 'some", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    /*
    @Test
    public void testConstructor_long_illegalCase_falseUnitState() { //TODO maar is nog niet juist in opgave
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "something", State.Powder);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals("something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }*/

    @Test (expected = IllegalTemperatureException.class)
    public void testConstructor_long_illegalCase_falseTemperature_bothCooledAndHeated() {
        long[] standardTemperatureIngredient = {15, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalTemperatureException.class)
    public void testConstructor_long_illegalCase_falseTemperature_tooHot() {
        long[] standardTemperatureIngredient = {0, 10001};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals("Something", ingredient.getName());
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    /*
    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_oneWord_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "So", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_oneWord_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "So", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_multipleWords_tooShort() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "So S", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }


    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_allLowerCase() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "something", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "SomeThing", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_multipleWords_notAllStartingWithUpperCase() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something something", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_multipleWords_UpperCaseInWord() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something SomeThing", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_numbers() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something1", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_punctuationMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something!", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }

    @Test (expected = IllegalNameException.class)
    public void testConstructor_long_illegalCase_falseName_specialMarks() {
        long[] standardTemperatureIngredient = {0, 15};
        AlchemicIngredient ingredient = new AlchemicIngredient(15, Unit.Barrel, standardTemperatureIngredient, "Something*", State.Liquid);
        assertEquals(15, ingredient.getQuantity());
        assertEquals(Unit.Barrel, ingredient.getUnit());
        assertEquals(standardTemperatureIngredient[0], ingredient.getTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getTemperature()[1]);
        assertEquals(standardTemperatureIngredient[0], ingredient.getStandardTemperature()[0]);
        assertEquals(standardTemperatureIngredient[1], ingredient.getStandardTemperature()[1]);
        assertEquals(State.Liquid, ingredient.getState());
        assertEquals(State.Liquid, ingredient.getStandardState());
    }
    */

    //TEST changeState

    //TEST getTemperature

    //TEST heat

    //TEST cool


}

