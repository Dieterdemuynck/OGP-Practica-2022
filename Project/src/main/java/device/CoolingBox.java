package main.java.device;

import main.java.ingredient.AlchemicIngredient;

/**
 * A class of cooling boxes
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class CoolingBox extends TemperatureDevice {

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    // Specification done in Device
    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CoolingBox;
    }

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Constructs a new CoolingBox device with its temperature set to {0, 20}.
     *
     * // TODO: @effect from superclass instead of post?
     * @post    the CoolingBox will have its temperature set to {0, 20}
     *          | new.getTemperature() == new long[]{0, 20};
     */
    public CoolingBox(){
        super();
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/

    /**
     * Lowers the temperature of the ingredient in the coolbox to the temperature the coolbox has been set to.
     * @pre the Coolingbox must be in a laboratory
     *      | getLaboratory() != null
     */
    public void activate() {
        // A device may only be activated if it is in a laboratory
        if (getLaboratory() != null) {
            long[] tempIngredient = getIngredient().getTemperature();
            long longTempIngredient = asLong(tempIngredient);
            if (longTempIngredient > getTemperatureAsLong()) {
                getIngredient().cool(Math.max(0, longTempIngredient - getTemperatureAsLong()));
            }
        }
    }

    /* *********************************************************
     * CHECKERS
     * *********************************************************/


}