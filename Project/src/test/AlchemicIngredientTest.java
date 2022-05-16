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
        milk = new AlchemicIngredient(1, Unit.Bottle, standardTemperatureMilk, "Milk", State.Liquid);
        long[] standardTemperatureLizardsTale = {0, 1};
        lizardsTail = new AlchemicIngredient(1, Unit.Sack, standardTemperatureLizardsTale, "Lizard's Tail", State.Powder);
    }

    /* *********************************************************
     * CONSTRUCTOR TESTS - voor alle devices
     * *********************************************************/
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

    /* *********************************************************
     * CHANGE STATE TESTS - voor Transmogrifier
     * *********************************************************/
    @Test
    public void testChangeState(){
        assertEquals(State.Liquid, water.getState());
        water.changeState(State.Powder);
        assertEquals(State.Powder, water.getState());
    }

    /* *********************************************************
     * TEMPERATURE TESTS - voor Coolbox en Oven4
     * todo moet getTemperature ook getest worden? Want das half getest in constructors en ook nu half met heat en cool... Geen idee hoe het grondiger zou kunnen
     * *********************************************************/
    @Test
    public void testHeat_temperatureAboveZero(){
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(20, water.getTemperature()[1]);
        water.heat(100);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(120, water.getTemperature()[1]);
    }

    @Test
    public void testHeat_temperatureBelowZero(){
        //Hiervoor gaan we er vanuit dat de cool-methode werkt, de testen volgen hieronder.
        lizardsTail.cool(20);
        assertEquals(19, lizardsTail.getTemperature()[0]);
        assertEquals(0, lizardsTail.getTemperature()[1]);
        lizardsTail.heat(100);
        assertEquals(0, lizardsTail.getTemperature()[0]);
        assertEquals(81, lizardsTail.getTemperature()[1]);
    }

    @Test
    public void testHeat_illegalCase(){
        water.heat(10000);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(20, water.getTemperature()[1]);
    }

    @Test
    public void testCool_temperatureAboveZero(){
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(20, water.getTemperature()[1]);
        water.cool(100);
        assertEquals(80, water.getTemperature()[0]);
        assertEquals(0, water.getTemperature()[1]);
    }


    @Test //todo, das mss nie nuttig...
    public void testCool_temperatureBelowZero(){
        //Hiervoor gaan we er vanuit dat de cool-methode werkt om de temperatuur naar nul te krijgen.
        water.cool(20);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(0, water.getTemperature()[1]);
        water.cool(100);
        assertEquals(100, water.getTemperature()[0]);
        assertEquals(0, water.getTemperature()[1]);
    }

    @Test
    public void testCool_illegalCase(){
        water.cool(10100);
        assertEquals(0, water.getTemperature()[0]);
        assertEquals(20, water.getTemperature()[1]);
    }
}

