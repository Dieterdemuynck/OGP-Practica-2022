package main.java.ingredient;

import be.kuleuven.cs.som.annotate.*;

public class IngredientNotEmptyException extends RuntimeException{

    /**
     * Required because this class inherits from Exception
     */
    private static final long serialVersionUID = 1L;
    private final AlchemicIngredient ingredientIn;
    private final AlchemicIngredient ingredientNew;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/
    public IngredientNotEmptyException(AlchemicIngredient ingredientIn, AlchemicIngredient ingredientNew) {
        this.ingredientIn = ingredientIn;
        this.ingredientNew = ingredientNew;
    }

    /* *********************************************************
     * INGREDIENT IN
     * *********************************************************/
    /**
     *
     */
    @Basic
    @Immutable
    public AlchemicIngredient getIngredientIn(){
        return this.ingredientIn;
    }

    /* *********************************************************
     * INGREDIENT NEW
     * *********************************************************/
    /**
     *
     */
    @Basic
    @Immutable
    public AlchemicIngredient getIngredientNew(){
        return this.ingredientNew;
    }



}
