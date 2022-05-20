package main.java.device;

import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.State;
import main.java.ingredient.Unit;

import java.util.ArrayList;
import java.util.List;

public class Kettle extends Device {

    /**
     * Variable referencing the total added ingredients, contained in the kettle.
     */
    private List<AlchemicIngredient> addedIngredients;


    /**
     * Returns the ingredients contained in the kettle.
     * @return the ingredients contained in the kettle.
     */
    public List<AlchemicIngredient> getAddedIngredients() {
        return addedIngredients;
    }

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    // Specification done in Device
    @Override
    public Device.DeviceType getDeviceType() {
        return DeviceType.Kettle;
    }

    /* *********************************************************
     * CONSTRUCTOR TODO
     * *********************************************************/

    public Kettle() {
        super();
        this.addedIngredients = new ArrayList<>();
    }

    /* *********************************************************
     * ADDED INGREDIENTS
     * *********************************************************/

    /**
     * Sets the ingredients contained in the kettle, to a given List of ingredients.
     * @param addedIngredients ArrayList of the ingredients that will be contained in the kettle.
     */
    public void setAddedIngredients(ArrayList<AlchemicIngredient> addedIngredients) {
        this.addedIngredients = addedIngredients;
    }

    /**
     * adds an ingredient to the total added ingredients.
     * @param ingredient the AlchamicIngredient that will be added to the total added ingredients.
     */
    public void insert(AlchemicIngredient ingredient) {
        getAddedIngredients().add(ingredient);
    }


    /* *********************************************************
     * ACTIVATE
     * *********************************************************/
    // TODO:
    //  Mate, start encapsulating more. Please.
    //  NAME SHOULD HAPPEN AS WELL

    /**
     * This method activates the kettle, mixing the ingredients contained in the kettle, resulting in a single ingredient.
     * @pre     the Kettle may not be empty.
     *          getAddedIngredients() != null.
     * @post    The Kettle will contain a single ingredient, with the right attributes.
     *          getAddedingredients().size() == 1
     */
    public void activate() {
        // A device may only be activated if it is in a laboratory
        if (getLaboratory() != null) {
            // The Quantity of the new ingredient, will be the total added quantities of the contained ingredients in spoons.
            int newQuantity = (int) Math.round(State.addAmountsInSpoons(getAddedIngredients()));
            long[] newTemperature = {0, 0};
            String newName = "test";
            AlchemicIngredient ingredientTempClosestToRoomTemp = null;
            long tempDifference = 20000;
            int i;
            for (i = 0; i == getAddedIngredients().size(); i++) {
                long[] tempNewIngredient = getAddedIngredients().get(i).getTemperature();
                // adding all the temperatures together, to then, later, calculate the temperature like it's supposed to be.
                if (tempNewIngredient[0] == 0) {
                    newTemperature[1] += tempNewIngredient[1];
                } else if (tempNewIngredient[1] == 0) {
                    newTemperature[0] += tempNewIngredient[0];
                }

                // Comparing the difference between the temperature of the relevant ingredient and [0,20],
                // and the difference of the ingredient with the temperature closest to [0,20], so we can calculate which ingredient to take the State from.
                // if the relevant ingredient is closer to [0,20], it will become the ingredient with the temperature closest to [0,20]
                // if the difference is the same, the ingredient with the state = Liquid will have priority over Powder.
                long newTempDifference = tempNewIngredient[0] + Math.abs(tempNewIngredient[1] - 20L);

                if (newTempDifference < tempDifference) {
                    tempDifference = newTempDifference;
                    ingredientTempClosestToRoomTemp = getAddedIngredients().get(i);
                } else if (newTempDifference == tempDifference) {
                    if (getAddedIngredients().get(i).getState() == State.Liquid) {
                        ingredientTempClosestToRoomTemp = getAddedIngredients().get(i);
                    }
                }
            }
            // To calculate the new temperature, we calculate the average temperature.
            newTemperature[0] /= newQuantity;
            newTemperature[1] /= newQuantity;
            // Up until now, the temperature has had a long in both indexes. This is illegal, so here, we calculate which index should equal 0,
            // and the other index will reduce to the right amount.
            if (newTemperature[0] < newTemperature[1]) {
                newTemperature[1] -= newTemperature[0];
                newTemperature[0] = 0;
            } else if (newTemperature[1] < newTemperature[0]) {
                newTemperature[0] -= newTemperature[1];
                newTemperature[1] = 0;
            }

            State newState = ingredientTempClosestToRoomTemp.getState();

            AlchemicIngredient newIngredient = new AlchemicIngredient(newQuantity, Unit.Spoon, newTemperature, newName, newState);
        }
    }
}


