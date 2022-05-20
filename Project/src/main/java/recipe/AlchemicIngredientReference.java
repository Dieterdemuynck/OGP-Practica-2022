package main.java.recipe;

import main.java.ingredient.Unit;

public record AlchemicIngredientReference(String name, int amount, Unit unit) {
}
