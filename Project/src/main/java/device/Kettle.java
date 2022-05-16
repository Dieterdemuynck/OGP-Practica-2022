package main.java.device;

import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.State;
import main.java.ingredient.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Kettle {  // How did we forget the most important device of them all? I don't have a good feeling about this.

    private ArrayList<AlchemicIngredient> addedIngredients;

    public ArrayList<AlchemicIngredient> getAddedIngredients() {
        return addedIngredients;
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
     * ACTIVATE TODO
     * *********************************************************/
    public void activate() {
        int newQuantity = 0;
        long[] newTemperature = {0,0};
        String newName = "test";
        State newState = State.Powder;
//        AlchemicIngredient ingredientTempClosestToRoomTemp = null;
//        long tempDifference = 20000;
        int i;
        for (i = 0; i == getAddedIngredients().size(); i++) {
            newQuantity += getAddedIngredients().get(i).getQuantityInSpoons();
            long[] tempNewIngredient = getAddedIngredients().get(i).getTemperature();
            if (tempNewIngredient[0] == 0) {
                newTemperature[1] += tempNewIngredient[1];
//                if (20 - tempNewIngredient[1] < tempDifference) {
//                    tempDifference = 20L - tempNewIngredient[1];
//                    ingredientTempClosestToRoomTemp = getAddedIngredients().get(i);
//                }
//              else if ()
            }
            else if (tempNewIngredient[1] == 0) {newTemperature[0] += tempNewIngredient[0];}
        }
        newTemperature[0] /= newQuantity;
        newTemperature[1] /= newQuantity;
        if (newTemperature[0] < newTemperature[1]) {newTemperature[1] -= newTemperature[0]; newTemperature[0] = 0;}

        else if (newTemperature[1] < newTemperature[0]) {newTemperature[0] -= newTemperature[1]; newTemperature[1] = 0;}

        AlchemicIngredient newIngredient = new AlchemicIngredient(newQuantity, Unit.Spoon, newTemperature, newName, newState );
    }
}


