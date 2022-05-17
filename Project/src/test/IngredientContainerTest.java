package test;

import main.java.ingredient.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientContainerTest {
    AlchemicIngredient water;
    IngredientContainer spoonContainer;
    //IngredientContainer waterContainer;

    @Before
    public void setUpFixture() {
        water = new AlchemicIngredient(15); // 15 spoons = 1 bottle
        spoonContainer = new IngredientContainer(Unit.Spoon);
        //waterContainer = new IngredientContainer(water); todo doesn't work -> something wrong with ingredient.CNTParser.getTable?
    }

    /* *********************************************************
     * CONSTRUCTOR TESTS - for all devices
     * *********************************************************/

    /* ***********************
     * CONSTRUCTOR TESTS - IngredientContainer(capacity)
     * **********************/
    @Test
    public void testConstructor_capacity_legalCase(){
        IngredientContainer container = new IngredientContainer(Unit.Spoon);
        assertEquals(Unit.Spoon, container.getCapacity());
    }

    @Test
    public void testConstructor_capacity_illegalCase(){ //Todo but because this is nominal it is possible to do this?
        IngredientContainer container = new IngredientContainer(Unit.Drop);
        assertEquals(Unit.Drop, container.getCapacity());
    }
    /* ***********************
     * CONSTRUCTOR TESTS - IngredientContainer(ingredient)
     * todo doesn't work -> something wrong with ingredient.CNTParser.getTable?
     * **********************/
    /*
    @Test
    public void testConstructor_ingredient_legalCase(){
        AlchemicIngredient milk = new AlchemicIngredient(1, new long[] {0,4}, "Milk", State.Liquid);
        IngredientContainer container = new IngredientContainer(milk);
        assertEquals(Unit.Spoon, container.getCapacity());
    }

    @Test
    public void testConstructor_ingredient_illegalCase(){ //Todo but because this is nominal it is possible to do this?
        AlchemicIngredient milk = new AlchemicIngredient(1, Unit.Drop, new long[] {0,4}, "Milk",
                State.Liquid);
        IngredientContainer container = new IngredientContainer(milk);
        assertEquals(Unit.Drop, container.getCapacity());
        assertEquals(milk, container.getContent());
    }
    */

    /* *********************************************************
     * EXTRACT TESTS - for all devices
     * todo doesn't work -> something wrong with ingredient.CNTParser.getTable?
     * *********************************************************/
    /*
    @Test
    public void testExtract(){
        assertEquals(water, waterContainer.getContent());
        waterContainer.extract();
        assertEquals(null, waterContainer.getContent());
    }
     */

    /* *********************************************************
     * INSERT TESTS - for all devices
     * todo doesn't work -> something wrong with ingredient.CNTParser.getTable?
     * *********************************************************/
    /*
    @Test
    public void testInsert_legalCase(){
        AlchemicIngredient milk = new AlchemicIngredient(3,Unit.Drop, new long[] {0,4}, "Milk", State.Liquid);
        assertEquals(null, spoonContainer.getContent());
        waterContainer.insert(milk);
        assertEquals(milk, waterContainer.getContent());
    }
     */
}
