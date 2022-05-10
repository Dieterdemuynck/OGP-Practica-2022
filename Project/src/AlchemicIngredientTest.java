import be.kuleuven.cs.som.annotate.*;
import org.junit.Before;
import org.junit.Test;

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

    // CONSTRUCTOR TEST-------------------------------------------------------------------------------------------------

    // INGREDIENT TYPE TEST---------------------------------------------------------------------------------------------
    // --- NAME TEST----------------------------------------------------------------------------------------------------

    // --- STANDARD STATE TEST------------------------------------------------------------------------------------------

    // --- STANDARD TEMPERATURE TEST------------------------------------------------------------------------------------

}

