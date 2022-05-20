package main.java.device;

import be.kuleuven.cs.som.annotate.Model;
import main.java.ingredient.AlchemicIngredient;

public abstract class TemperatureDevice extends Device {

    /**
     * Variable registering the temperature of this temperature device
     */
    private long temperature;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Constructs a new temperature device with its temperature set to {0, 20}.
     *
     * @post    the temperature device will have its temperature set to {0, 20}
     *          | new.getTemperature() == new long[]{0, 20};
     */
    @Model
    public TemperatureDevice(){
        super();
        setTemperature(new long[]{0,20});
    }

    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/

    protected long getTemperatureAsLong() {
        return this.temperature;
    }

    /**
     * Returns the temperature array that the temperature device is set to.
     *
     * @return The long[] array representing the temperature that the temperature device is set to.
     */

    public long[] getTemperature() {
        if (getTemperatureAsLong() < 0)
            return new long[]{-getTemperatureAsLong(), 0};
        return new long[]{0, getTemperatureAsLong()};
    }

    /**
     * Sets the temperature device's temperature to the given temperature array.
     *
     * @param temperature
     */
    public void setTemperature(long[] temperature) {
        if (!AlchemicIngredient.isValidTemperature(temperature)) {
            this.temperature = 20; //DEFAULT
        }
        else {
            this.temperature = temperature[1] - temperature[0];
        }
    }
}
