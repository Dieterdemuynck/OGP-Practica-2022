package main.java.device.exception;

import be.kuleuven.cs.som.annotate.*;
import main.java.ingredient.AlchemicIngredient;

/**
 * A class for signaling illegal attempts to place an alchemical ingredient in a device that is not empty.
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 1.0
 */
public class DeviceNotEmptyException extends RuntimeException{

    /**
     * Required because this class inherits from Exception
     * (which in its turn implements the interface Serializable)
     */
    private static final long serialVersionUID = 99L;
    /**
     * The alchemical ingredient that is already in the device
     */
    private final AlchemicIngredient ingredientIn;
    /**
     * The alchemical ingredient that illegally placed in the device
     */
    private final AlchemicIngredient ingredientNew;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this new device not empty exception involving the alchemical ingredient in the device and the new
     * alchemical ingredient.
     *
     * @param   ingredientIn
     *          The alchemical ingredient in the device involved in the new device not empty exception.
     * @param   ingredientNew
     *          The new alchemical ingredient involved in the new device not empty exception.
     * @post    The ingredientIn and ingredientNew involved in the new device not empty exception are respectively set
     *          to the given alchemical ingredients.
     *          | new.getIngredientIn() == ingredientIn
     *          | new.getIngredientNew() == ingredientNew
     */
    public DeviceNotEmptyException(AlchemicIngredient ingredientIn, AlchemicIngredient ingredientNew) {
        this.ingredientIn = ingredientIn;
        this.ingredientNew = ingredientNew;
    }

    /* *********************************************************
     * INGREDIENT IN
     * *********************************************************/
    /**
     * Return the alchemical ingredient in the device involved in this device not empty exception.
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
     * Return the new alchemical ingredient involved in this device not empty exception.
     */
    @Basic
    @Immutable
    public AlchemicIngredient getIngredientNew(){
        return this.ingredientNew;
    }



}
