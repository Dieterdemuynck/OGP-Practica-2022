package main.java.device;

public class CoolingBox extends Device {

    private long temperature;

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
        this.temperature = temperature;
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/
    public void activate() {
        long tempTemp = getTemperature();
        long[] tempIngredient = getIngredient().getTemperature();
        long longTempIngredient = asLong(tempIngredient);
        getIngredient().cool(Math.abs(longTempIngredient-tempTemp));
    }


}