package main.java.device;

import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.State;
import main.java.ingredient.Unit;

import java.util.ArrayList;

public class Kettle extends Device {  // How did we forget the most important device of them all? I don't have a good feeling about this.

    private ArrayList<AlchemicIngredient> addedIngredients;

    public ArrayList<AlchemicIngredient> getAddedIngredients() {
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



    /* *********************************************************
     * ADDED INGREDIENTS
     * *********************************************************/

    public void setAddedIngredients(ArrayList<AlchemicIngredient> addedIngredients) {
        this.addedIngredients = addedIngredients;
    }

    public void insert(AlchemicIngredient ingredient) {
        getAddedIngredients().add(ingredient);
    }


    /* *********************************************************
     * ACTIVATE
     * *********************************************************/
    // TODO:
    //  Mate, start encapsulating more. Please.
    public void activate() {
        int newQuantity = 0;
        long[] newTemperature = {0,0};
        String newName = "test";
        State newState = State.Powder;
        AlchemicIngredient ingredientTempClosestToRoomTemp = null;
        long tempDifference = 20000;
        int i;
        for (i = 0; i == getAddedIngredients().size(); i++) {
            // TODO: getQuantityInSpoons no longer exists, fix
            // newQuantity += getAddedIngredients().get(i).getQuantityInSpoons();
            long[] tempNewIngredient = getAddedIngredients().get(i).getTemperature();
            if (tempNewIngredient[0] == 0) {
                newTemperature[1] += tempNewIngredient[1];}
            else if (tempNewIngredient[1] == 0) {newTemperature[0] += tempNewIngredient[0];}
            long newTempDifference = tempNewIngredient[0] + Math.abs(tempNewIngredient[1] - 20L);

            if (newTempDifference < tempDifference) {
                tempDifference = newTempDifference;
                ingredientTempClosestToRoomTemp = getAddedIngredients().get(i);
            }
            else if (newTempDifference == tempDifference) {
                if (getAddedIngredients().get(i).getState() == State.Liquid) {
                    ingredientTempClosestToRoomTemp = getAddedIngredients().get(i);
                }
            }
        }
        // TODO: was division by zero. after fix, please uncomment, thx :)
        // newTemperature[0] /= newQuantity;
        // newTemperature[1] /= newQuantity;
        if (newTemperature[0] < newTemperature[1]) {newTemperature[1] -= newTemperature[0]; newTemperature[0] = 0;}

        else if (newTemperature[1] < newTemperature[0]) {newTemperature[0] -= newTemperature[1]; newTemperature[1] = 0;}

        newState = ingredientTempClosestToRoomTemp.getState();

        AlchemicIngredient newIngredient = new AlchemicIngredient(newQuantity, Unit.Spoon, newTemperature, newName, newState );
    }
}


