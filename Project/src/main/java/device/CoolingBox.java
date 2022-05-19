package main.java.device;

import main.java.ingredient.AlchemicIngredient;

/**
 * A class of cooling boxes
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class CoolingBox extends Device {

    /**
     * Variable registering the temperature of this cooling box
     */
    private long temperature;

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
     * @post    the CoolingBox will have its temperature set to {0, 20}
     *          | new.getTemperature() == new long[]{0, 20};
     */
    public CoolingBox(){
        this.temperature = 20;
    }

    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/

    private long getTemperatureAsLong() {
        return this.temperature;
    }

    /**
     * Returns the temperature array that the CoolingBox is set to.
     *
     * @return The long[] array representing the temperature the CoolingBox is set to.
     */
    public long[] getTemperature() {
        if (getTemperatureAsLong() < 0)
            return new long[]{-getTemperatureAsLong(), 0};
        return new long[]{0, getTemperatureAsLong()};
    }

    /**
     * Sets the CoolingBox's temperature to the given temperature array. If the given temperature is invalid, it will be set to [0,20]
     *
     * @param temperature
     */
    public void setTemperature(long[] temperature) {
        if (AlchemicIngredient.isValidTemperature(temperature)) {
            this.temperature = 20; //DEFAULT
        }
        else {
            long temp = 0;
            if (temperature[0] != 0) {
                temp -= temperature[0];
            } else {
                temp = temperature[1];
            }
            this.temperature = temp;
        }
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/

    /**
     * Lowers the temperature of the ingredient in the coolbox to the temperature the coolbox has been set to.
     */
    public void activate() {
        long[] tempIngredient = getIngredient().getTemperature();
        long longTempIngredient = asLong(tempIngredient);
        getIngredient().cool(Math.max(0, longTempIngredient-getTemperatureAsLong()));
    }

    /* *********************************************************
     * CHECKERS
     * *********************************************************/


}