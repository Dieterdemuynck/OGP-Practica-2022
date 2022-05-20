package main.java.device.exception;

import be.kuleuven.cs.som.annotate.*;
import main.java.device.Device;
import main.java.ingredient.AlchemicIngredient;

// TODO: SPECIFICATION (made adjustments, no longer holds ingredients, only device
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
    private final Device device;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this new device not empty exception involving the alchemical ingredient in the device and the new
     * alchemical ingredient.
     *
     * @param   device
     *          The alchemical ingredient in the device involved in the new device not empty exception.
     * @post    The ingredientIn and ingredientNew involved in the new device not empty exception are respectively set
     *          to the given alchemical ingredients.
     *          | new.getIngredientIn() == ingredientIn
     *          | new.getIngredientNew() == ingredientNew
     */
    public DeviceNotEmptyException(Device device) {
        this.device = device;
    }

    /* *********************************************************
     * INGREDIENT IN
     * *********************************************************/
    /**
     * Return the alchemical ingredient in the device involved in this device not empty exception.
     */
    @Basic
    @Immutable
    public Device getDevice(){
        return this.device;
    }

}
