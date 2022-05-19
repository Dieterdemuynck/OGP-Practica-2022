package main.java.ingredient.exception;

import be.kuleuven.cs.som.annotate.*;
import main.java.ingredient.IngredientType;

/**
 * A class for signaling illegal attempts to set a special name for a not mixed ingredient type.
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 2.2
 */
public class NonMixedSpecialNameException extends RuntimeException {

    /**
     * Required because this class inherits from Exception.
     * (which in its turn implements the interface Serializable)
     */
    private static final long serialVersionUID = 3L;  // I still have no idea what this is
    /**
     * Variable referencing the ingredient type to which a special name was denied.
     */
    private final IngredientType ingredientType;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this new non mixed special name exception involving the given ingredient type.
     *
     * @param   ingredientType
     *          The ingredient type for the new non mixed special name exception.
     * @post    The ingriedint type involved in the new non mixed special name exception is set to the given ingredient
     *          type.
     *          | new.getIngredientType() == ingredientType
     */
    public NonMixedSpecialNameException(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    /* *********************************************************
     * NAME
     * *********************************************************/
    /**
     * Return the ingredient type involved in this non mixed special name exception.
     */
    @Basic
    @Immutable
    public IngredientType getIngredientType() {
        return this.ingredientType;
    }

}