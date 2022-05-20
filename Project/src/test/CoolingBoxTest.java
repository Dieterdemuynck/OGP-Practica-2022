package test;

import main.java.Laboratory;
import main.java.device.CoolingBox;
import main.java.device.Device;
import main.java.device.Oven;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CoolingBoxTest {
    CoolingBox coolingBox;
    AlchemicIngredient water;
    IngredientContainer waterContainer;
    Laboratory laboratory;

    @Before
    public void setUpFixture() {
        coolingBox = new CoolingBox();
        water = new AlchemicIngredient(1);
        waterContainer = new IngredientContainer(water);
        laboratory = new Laboratory(1);
    }

    @Test
    public void testGetDeviceType(){
        assertEquals(Device.DeviceType.CoolingBox,coolingBox.getDeviceType());
    }

    @Test
    public void testActivate_withoutLaboratory(){
        coolingBox.setTemperature(new long[] {100,0});
        assertEquals(100,coolingBox.getTemperature()[0]);
        assertEquals(0,coolingBox.getTemperature()[1]);
        coolingBox.insert(waterContainer);
        coolingBox.activate();
        IngredientContainer container = coolingBox.retrieve();
        assertEquals(0,container.getContent().getTemperature()[0]);
        assertEquals(20,container.getContent().getTemperature()[1]);
    }

    @Test
    public void testActivate_withLaboratory(){
        // oven must heat water to 100 with a marge of 5%
        coolingBox.setTemperature(new long[] {100,0});
        coolingBox.setLaboratory(laboratory);
        assertEquals(100, coolingBox.getTemperature()[0]);
        assertEquals(0, coolingBox.getTemperature()[1]);
        coolingBox.insert(waterContainer);
        coolingBox.activate();
        IngredientContainer container = coolingBox.retrieve();
        assertEquals(100,container.getContent().getTemperature()[0]);
        assertEquals(0,container.getContent().getTemperature()[1]);
    }
}
