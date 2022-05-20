package main.java;

import main.java.ingredient.AlchemicIngredient;

import java.util.LinkedList;
import java.util.List;

/**
 * A class of laboratories
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class Recipe {

    /**
     * Variable registering the list of ingredients in the right order used in the recipe.
     */
    private List<AlchemicIngredient> ingredientsList = new LinkedList<>();

    /**
     * Variable registering the list of operations used on the ingredients in the right order.
     */
    private List<Operation> operationList = new LinkedList<>();

    /**
     * Returns the list of ingredients.
     * @return the list of ingredients
     */
    public List<AlchemicIngredient> getIngredientsList() {
        return ingredientsList;
    }

    /**
     * returns the list of operations.
     * @return the list of operations
     */
    public List<Operation> getOperationsList() {
        return operationList;
    }
}
