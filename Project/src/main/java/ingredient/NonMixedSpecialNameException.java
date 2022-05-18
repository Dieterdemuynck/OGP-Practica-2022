package main.java.ingredient;

import be.kuleuven.cs.som.annotate.*;

/**
 *
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 2.2
 */
public class NonMixedSpecialNameException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 3L;  // I still have no idea what this is
    private final IngredientType ingredientType;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/
    public NonMixedSpecialNameException(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    /* *********************************************************
     * NAME
     * *********************************************************/
    /**
     *
     */
    @Basic
    @Immutable
    public IngredientType getIngredientType() {
        return this.ingredientType;
    }

}