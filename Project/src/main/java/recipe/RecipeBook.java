package main.java.recipe;

import java.util.LinkedList;
import java.util.List;

public class RecipeBook {

    List<Recipe> recipeList = new LinkedList<>();

    private List<Recipe> getRecipeList() {
        return recipeList;
    }

    /**
     *
     * @param recipe
     */
    public void addRecipe(Recipe recipe){
        // Since a recipe is allowed to be in two books at once, I assume the same recipe can also be stored on
        // separate pages in one book.
        getRecipeList().add(recipe);
    }

    public void removeRecipe(Recipe recipe){
        getRecipeList().remove(recipe);
    }

    public Recipe get(int i) {
        return getRecipeList().get(i);
    }
}
