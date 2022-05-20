package test;

import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;
import org.junit.Before;
import org.junit.Test;

public class CoolingBoxTest {
    IngredientContainer waterContainer;
    IngredientContainer hotWaterContainer;
    IngredientContainer coldWaterContainer;
    IngredientContainer mixtureContainer;

    @Before
    public void setUpFixture() {
        waterContainer = new IngredientContainer(new AlchemicIngredient(1));
        // hotWaterContainer = new IngredientContainer(new AlchemicIngredient(1, ))
    }
}
