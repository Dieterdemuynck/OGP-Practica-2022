package main.java.device;

import main.java.ingredient.AlchemicIngredient;
import java.util.ArrayList;

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

    }
}


