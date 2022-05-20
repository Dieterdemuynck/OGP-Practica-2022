package test;

import main.java.Laboratory;
import main.java.device.Device;
import main.java.device.Oven;
import main.java.device.exception.DeviceNotEmptyException;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;
import main.java.ingredient.State;
import main.java.ingredient.Unit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit (4) test class for testing the non-private methods of the Oven Class.
 *
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class OvenTest {
    Oven oven;
    AlchemicIngredient water;
    IngredientContainer waterContainer;
    Laboratory laboratory;

    @Before
    public void setUpFixture() {
        oven = new Oven();
        water = new AlchemicIngredient(1);
        waterContainer = new IngredientContainer(water);
        laboratory = new Laboratory(1);
    }

    @Test
    public void testGetDeviceType(){
        assertEquals(Device.DeviceType.Oven,oven.getDeviceType());
    }

    @Test
    public void testActivate_withoutLaboratory(){
        oven.setTemperature(new long[] {0,100});
        assertEquals(0,oven.getTemperature()[0]);
        assertEquals(100,oven.getTemperature()[1]);
        oven.insert(waterContainer);
        oven.activate();
        IngredientContainer container = oven.retrieve();
        assertEquals(0,container.getContent().getTemperature()[0]);
        assertEquals(20,container.getContent().getTemperature()[1]);
    }

    @Test
    public void testActivate_withLaboratory(){
        // oven must heat water to 100 with a marge of 5%
        oven.setTemperature(new long[] {0,100});
        oven.setLaboratory(laboratory);
        assertEquals(0, oven.getTemperature()[0]);
        assertEquals(100, oven.getTemperature()[1]);
        oven.insert(waterContainer);
        oven.activate();
        IngredientContainer container = oven.retrieve();
        assertEquals(0,container.getContent().getTemperature()[0]);
        assertTrue(0 <= container.getContent().getTemperature()[1]
                    && container.getContent().getTemperature()[1] <=95);
    }

    @Test
    public void testActivate_withLaboratory_tooCold(){
        // oven must heat water to 100 with a marge of 5%
        oven.setTemperature(new long[] {100,0});
        oven.setLaboratory(laboratory);
        assertEquals(100, oven.getTemperature()[0]);
        assertEquals(0, oven.getTemperature()[1]);
        oven.insert(waterContainer);
        oven.activate();
        IngredientContainer container = oven.retrieve();
        assertEquals(0,container.getContent().getTemperature()[0]);
        assertEquals(20, container.getContent().getTemperature()[1]);
    }

    @Test (expected = DeviceNotEmptyException.class)
    public void testInsert_illegalCase(){
        oven.insert(waterContainer);
        AlchemicIngredient chocolate = new AlchemicIngredient(1, Unit.Box, new long[] {0,15}, "Chocolate", State.Powder);
        IngredientContainer chocolateContainer = new IngredientContainer(chocolate);
        oven.insert(chocolateContainer);
    }
}
