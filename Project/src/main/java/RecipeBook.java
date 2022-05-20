package main.java;

import java.util.LinkedList;
import java.util.List;

public class RecipeBook {

    List<Recipe> recipeBook = new LinkedList<>();

    /**
     *
     * @param recipe
     */
    public void addRecipe(Recipe recipe){
        // Since a recipe is allowed to be in two books at once, I assume the same recipe can also be stored on
        // separate pages in one book.
        recipeBook.add(recipe);
    }

    public void removeRecipe(Recipe recipe){
        recipeBook.remove(recipe);
    }
}
