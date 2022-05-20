package test;

import main.java.Laboratory;
import main.java.device.Device;
import main.java.device.Kettle;
import main.java.device.Oven;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;
import main.java.ingredient.State;
import main.java.ingredient.Unit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KettleTest {

    Kettle kettle;
    AlchemicIngredient milk;
    IngredientContainer milkContainer;
    AlchemicIngredient chocolate;
    IngredientContainer chocolateContainer;
    Laboratory laboratory;


    @Before
    public void setUpFixture() {
        kettle = new Kettle();
        milk = new AlchemicIngredient(1, Unit.Bottle, new long[] {0,4}, "Milk", State.Liquid);
        milkContainer = new IngredientContainer(milk);
        chocolate = new AlchemicIngredient(1, Unit.Box, new long[] {0,15}, "Chocolate", State.Powder);
        chocolateContainer = new IngredientContainer(chocolate);
        laboratory = new Laboratory(1);
    }

    @Test
    public void testGetDeviceType(){
        assertEquals(Device.DeviceType.Kettle,kettle.getDeviceType());
    }

    @Test
    public void testActivate_withoutLaboratory(){
        kettle.insert(milkContainer);
        kettle.insert(chocolateContainer);
        kettle.activate();
        IngredientContainer container = kettle.retrieve();
        AlchemicIngredient chocolateWithMilk = container.getContent();
        assertEquals("Chocolate mixed with Milk", chocolateWithMilk.getName());
        assertEquals(State.Powder, chocolateWithMilk.getState());
        assertEquals(Unit.Spoon, chocolateWithMilk.getUnit());
        assertEquals(57, chocolateWithMilk.getAmount());
        assertEquals(0, chocolateWithMilk.getTemperature()[0]);
        assertEquals(12, chocolateWithMilk.getTemperature()[1]);
    }

    @Test
    public void testActivate_withLaboratory(){
        kettle.insert(milkContainer);
        kettle.insert(chocolateContainer);
        kettle.activate();
        IngredientContainer container = kettle.retrieve();
        AlchemicIngredient chocolateWithMilk = container.getContent();
        assertEquals("Chocolate mixed with Milk", chocolateWithMilk.getName());
        assertEquals(State.Powder, chocolateWithMilk.getState());
        assertEquals(Unit.Spoon, chocolateWithMilk.getUnit());
        assertEquals(57, chocolateWithMilk.getAmount());
        assertEquals(0, chocolateWithMilk.getTemperature()[0]);
        assertEquals(12, chocolateWithMilk.getTemperature()[1]);
    }
}
