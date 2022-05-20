package test;

import main.java.Laboratory;
import main.java.device.Device;
import main.java.device.Transmogrifier;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;
import main.java.ingredient.State;
import main.java.ingredient.Unit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransmogrifierTest {

    Transmogrifier transmogrifier;
    AlchemicIngredient milk;
    IngredientContainer milkContainer;
    Laboratory laboratory;


    @Before
    public void setUpFixture() {
        transmogrifier = new Transmogrifier(State.Powder);
        milk = new AlchemicIngredient(1, Unit.Bottle, new long[] {0,4}, "Milk", State.Liquid);
        milkContainer = new IngredientContainer(milk);
        laboratory = new Laboratory(1);
    }

    @Test
    public void testGetDeviceType(){
        assertEquals(Device.DeviceType.Transmogrifier,transmogrifier.getDeviceType());
    }

    @Test
    public void testActivate_withoutLaboratory(){
        transmogrifier.insert(milkContainer);
        transmogrifier.activate();
        IngredientContainer container = transmogrifier.retrieve();
        assertEquals(milk.getState(), container.getContent().getState());
    }

    @Test //TODO FOUT ZIE MESSENGER
    public void testActivate_withLaboratory(){
        transmogrifier.setLaboratory(laboratory);
        transmogrifier.insert(milkContainer);
        transmogrifier.activate();
        IngredientContainer container = transmogrifier.retrieve();
        assertEquals(State.Powder, container.getContent().getState());
    }
}
