package main.java.device;

import main.java.ingredient.AlchemicIngredient;

import java.util.Random;

/**
 * A class of cooling boxes
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class Oven extends TemperatureDevice {  // why do they call it oven when you of in the cold food of out hot eat the food?

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    // Specification done in Device
    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Oven;
    }

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Constructs a new Oven device with its temperature set to {0, 20}.
     *
     * @post    the Oven will have its temperature set to {0, 20}
     *          | new.getTemperature() == new long[]{0, 20};
     */
    public Oven(){
        super();
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/

    /**
     * Lowers the temperature of the ingredient in the Oven to the temperature the Oven has been set to.
     * A random deviation will be added to the requested temperature.
     */
    public void activate() {
        // A device may only be activated if it is in a laboratory
        if (getLaboratory() != null) {
            Random rand = new Random();
            double deviation = rand.nextDouble(0, 0.05);
            long deviationTemperature = (long) (getTemperatureAsLong() * deviation);  // Automatically rounds down

            // At this point, we have the absolute value of the deviation

            if (rand.nextBoolean()) {
                // 50/50 chance at cooler/hotter than set temperature
                deviationTemperature *= -1;
            }

            // Get the ingredient's temperature
            long[] ingredientTemperatureArray = getIngredient().getTemperature();
            long ingredientTemperature = ingredientTemperatureArray[0] * -1 + ingredientTemperatureArray[1];

            // Heat the ingredient by the difference of it's temperature and the set temperature, plus the deviation
            getIngredient().heat(Math.abs(this.getTemperatureAsLong() - ingredientTemperature + deviationTemperature));
        }
    }
}