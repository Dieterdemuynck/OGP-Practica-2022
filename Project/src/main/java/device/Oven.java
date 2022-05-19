package main.java.device;

import java.util.Random;

/**
 * A class of cooling boxes
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class Oven extends Device {  // why do they call it oven when you of in the cold food of out hot eat the food?

    /**
     * Variable registering the temperature of this oven
     */
    private long temperature;

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    // Specification done in Device
    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Oven;
    }

    /* *********************************************************
     * CONSTRUCTOR TODO
     * *********************************************************/

    /**
     * Constructs a new Oven device with its temperature set to {0, 20}.
     *
     * @post    the Oven will have its temperature set to {0, 20}
     *          | new.getTemperature() == new long[]{0, 20};
     */
    public Oven(){
        this.temperature = 20;
    }

    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/

    private long getTemperatureAsLong() {
        return this.temperature;
    }

    /**
     * Returns the temperature array that the Oven is set to.
     *
     * @return The long[] array representing the temperature the Oven is set to.
     */

    public long getTemperature() {
        return temperature;
    }

    /**
     * Sets the Oven's temperature to the given temperature array.
     *
     * @param temperature
     */
    public void setTemperature(long temperature) {
        if (temperature > 0) {
            this.temperature = temperature;
        }
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/

    /**
     * Lowers the temperature of the ingredient in the Oven to the temperature the Oven has been set to.
     * A random deviation will be added to the requested temperature.
     */
    public void activate() {
        Random rand = new Random();
        double deviation = rand.nextDouble(0.95, 1.05);
        long tempTemperature = Math.round(getTemperature() * deviation);
        // afgerond is da 158 ma das meer dan 5% afwijkend is da ok?
        //todo Kvraag mij af als je dan lik 150°C neemt en die kiest als random waarde 1,05 -> dan heb je 157,5°C
        long[] tempIngredient = getIngredient().getTemperature();
        long longTempIngredient = asLong(tempIngredient);
        getIngredient().heat(Math.abs(longTempIngredient - tempTemperature));
    }
}