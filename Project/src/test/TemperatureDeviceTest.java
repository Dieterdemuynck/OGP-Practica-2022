package test;

import main.java.Laboratory;
import main.java.device.*;
import main.java.ingredient.*;
import org.junit.Before;
import org.junit.Test;

import java.security.spec.RSAOtherPrimeInfo;

import static org.junit.Assert.*;

/**
 * A JUnit (4) test class for testing the non-private methods of the Device Class.
 * The class is abstract, so we can use either one of the subclasses to test the members of the superclass.
 *
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class TemperatureDeviceTest {
    // OVEN:
    private static Oven oven;

    // COOLING BOX:
    private static CoolingBox coolingBox;

    // ALCHEMICAL INGREDIENT
    private static AlchemicIngredient water, sugar;

    // INGREDIENT CONTAINER
    private static IngredientContainer waterContainer, sugarContainer;

    //LABORATORY
    private static Laboratory laboratory;

    @Before
    public void setUpFixture() {
        oven = new Oven();
        coolingBox = new CoolingBox();
        water = new AlchemicIngredient(1);
        sugar= new AlchemicIngredient(2, Unit.Sachet, new long[] {0,18}, "Sugar", State.Powder);
        waterContainer = new IngredientContainer(water);
        sugarContainer = new IngredientContainer(sugar);
        laboratory = new Laboratory(1);
    }

    @Test
    public void testConstructorOven() {
        Oven oven1 = new Oven();
        assertEquals(Device.DeviceType.Oven, oven1.getDeviceType());
        assertEquals(0, oven1.getTemperature()[0]);
        assertEquals(20, oven1.getTemperature()[1]);
    }

    @Test
    public void testConstructorCoolingBox() {
        CoolingBox coolingbox1 = new CoolingBox();
        assertEquals(Device.DeviceType.CoolingBox, coolingbox1.getDeviceType());
        assertEquals(0, coolingbox1.getTemperature()[0]);
        assertEquals(20, coolingbox1.getTemperature()[1]);
    }

    @Test
    public void testInsert(){
        assertTrue(oven.isEmptyDevice());
        oven.insert(waterContainer);
        assertFalse(oven.isEmptyDevice());
    }

    /*@Test
    public void testRetrieve(){
        assertTrue(oven.isEmptyDevice());
        oven.insert(waterContainer);
        assertFalse(oven.isEmptyDevice());
        oven.retrieve()
    }*/

    @Test
    public void testLaboratory(){
        assertNull(oven.getLaboratory());
        oven.setLaboratory(laboratory);
        assertEquals(laboratory, oven.getLaboratory());
    }

    @Test
    public void testSetTemperature() {
        assertEquals(0, oven.getTemperature()[0]);
        assertEquals(20, oven.getTemperature()[1]);
        oven.setTemperature(new long[]{0, 100});
        assertEquals(0, oven.getTemperature()[0]);
        assertEquals(100, oven.getTemperature()[1]);
    }

}
