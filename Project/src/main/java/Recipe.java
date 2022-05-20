package main.java;

import main.java.ingredient.AlchemicIngredient;

import java.util.Arrays;
import java.util.Collections;
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
    private final List<AlchemicIngredient> ingredientsList;

    /**
     * Variable registering the list of operations used on the ingredients in the right order.
     */
    private final List<Operation> operationList;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Constructs a new Recipe with the given ingredient list and operations list. Removes all null elements from
     * both lists.
     *
     * @param ingredients
     * @param operations
     */
    public Recipe(List<AlchemicIngredient> ingredients, List<Operation> operations) {
        ingredients.removeAll(Collections.singleton(null));
        operations.removeAll(Collections.singleton(null));
        this.ingredientsList = ingredients;
        this.operationList = operations;
    }

    public Recipe(AlchemicIngredient[] ingredients, Operation[] operations) {
        this(Arrays.asList(ingredients), Arrays.asList(operations));
    }

    /* *********************************************************
     * INGREDIENT LIST
     * *********************************************************/

    /**
     * Returns the list of ingredients.
     * @return the list of ingredients
     */
    public List<AlchemicIngredient> getIngredientsList() {
        return ingredientsList;
    }

    /* *********************************************************
     * OPERATIONS LIST
     * *********************************************************/

    /**
     * returns the list of operations.
     * @return the list of operations
     */
    public List<Operation> getOperationsList() {
        return operationList;
    }
}
