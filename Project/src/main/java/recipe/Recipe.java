package main.java.recipe;

import main.java.ingredient.AlchemicIngredient;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A class of recipes
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class Recipe {

    /**
     * Variable registering the list of ingredients in the right order used in the recipe.
     */
    private final List<AlchemicIngredientReference> ingredientsList;

    /**
     * Variable registering the list of operations done on the ingredients in the right order.
     */
    private final List<Operation> operationList;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Constructs a new Recipe with the given ingredient list and operations list. Removes all null elements from
     * both lists.
     *
     * @pre   The amount of add operations and ingredients is the same.
     *
     * @param ingredients
     * @param operations
     */
    public Recipe(List<AlchemicIngredientReference> ingredients, List<Operation> operations) {
        ingredients.removeAll(Collections.singleton(null));
        operations.removeAll(Collections.singleton(null));
        this.ingredientsList = ingredients;
        if (operations.get(operations.size() - 1) != Operation.Mix)
            operations.add(Operation.Mix);
        this.operationList = operations;
    }

    public Recipe(AlchemicIngredientReference[] ingredients, Operation[] operations) {
        this(Arrays.asList(ingredients), Arrays.asList(operations));
    }

    /* *********************************************************
     * INGREDIENT LIST
     * *********************************************************/

    /**
     * Returns the list of alchemical ingredient references.
     * @return the list of alchemical ingredient references
     */
    public List<AlchemicIngredientReference> getIngredientsList() {
        return ingredientsList;
    }

    /* *********************************************************
     * OPERATIONS LIST
     * *********************************************************/

    public List<Operation> getOperationsList() {
        return operationList;
    }
}
