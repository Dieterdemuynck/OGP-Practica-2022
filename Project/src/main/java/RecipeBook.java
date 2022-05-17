package main.java;

import java.util.LinkedList;
import java.util.List;

public class RecipeBook {

    List<Recipe> recipeBook = new LinkedList<>();

    public void addRecipe(Recipe recipe){
        // mag een recept 2x in een boek zitten?
        recipeBook.add(recipe);
        // die unidirectionele link, is da goe genoeg? Of wa moet er daar bij?
    }

    public void removeRecipe(Recipe recipe){
        recipeBook.remove(recipe);
    }
}
