package main.java;

import main.java.ingredient.AlchemicIngredient;

import java.util.LinkedList;
import java.util.List;

public class Recipe {

    private List<AlchemicIngredient> ingredientsList = new LinkedList<>();
    private List<Operation> operationList = new LinkedList<>();

    public List<AlchemicIngredient> getIngredientsList() {
        return ingredientsList;
    }

    public List<Operation> getOperationsList() {
        return operationList;
    }
}
