package main.java;

import main.java.ingredient.AlchemicIngredient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Recipe {

    private final List<AlchemicIngredient> ingredientsList;
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

    public List<AlchemicIngredient> getIngredientsList() {
        return ingredientsList;
    }

    /* *********************************************************
     * OPERATIONS LIST
     * *********************************************************/

    public List<Operation> getOperationsList() {
        return operationList;
    }
}
