package main.java.device;

public class CoolingBox extends Device {

    private long temperature;

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CoolingBox;
    }

    /* *********************************************************
     * CONSTRUCTOR TODO
     * *********************************************************/


    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/
    public long getTemperature() {
        // TODO: should return long[] instead
        return temperature;
    }

    public void setTemperature(long temperature) {
        // TODO: should get a long[] instead
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