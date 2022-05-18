package main.java.device;

import java.util.Random;

public class Oven extends Device {  // why do they call it oven when you of in the cold food of out hot eat the food?

    private long temperature;

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Oven;
    }

    /* *********************************************************
     * CONSTRUCTOR TODO
     * *********************************************************/


    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/
    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        if (temperature > 0) {
            this.temperature = temperature;
        }
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/
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