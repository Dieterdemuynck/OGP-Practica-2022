package test;

import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.State;
import org.junit.Before;

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
    public void setUpFixture(){
        water = new AlchemicIngredient(1);
        long[] standardTemperatureMilk = {0, 4};
        milk = new AlchemicIngredient(1, standardTemperatureMilk,"Milk", State.Liquid);
        long[] standardTemperatureLizardsTale = {0, 20};
        lizardsTail = new AlchemicIngredient(1, standardTemperatureMilk,"Lizard's Tail", State.Powder);
    }



}
