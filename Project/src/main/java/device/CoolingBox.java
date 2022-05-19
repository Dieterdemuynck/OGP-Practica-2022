package main.java.device;

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

    /**
     *
     * @return
     */
    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CoolingBox;
    }

    /* *********************************************************
     * CONSTRUCTOR TODO
     * *********************************************************/

    /**
     *
     */
    public CoolingBox(){
        this.temperature = 20;
    }

    /* *********************************************************
     * TEMPERATURE
     * *********************************************************/

    /**
     *
     * @return
     */
    public long getTemperature() {
        // TODO: should return long[] instead
        return temperature;
    }

    /**
     *
     * @param temperature
     */
    public void setTemperature(long[] temperature) {
        // TODO: should get a long[]
        if (temperature == null) {
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
     *
     */
    public void activate() {
        long tempTemp = getTemperature();
        long[] tempIngredient = getIngredient().getTemperature();
        long longTempIngredient = asLong(tempIngredient);
        getIngredient().cool(Math.abs(longTempIngredient-tempTemp));
    }


}