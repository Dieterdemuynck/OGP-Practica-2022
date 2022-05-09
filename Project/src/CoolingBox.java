public class CoolingBox extends AlchemicDevice{

    private long temperature;

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public void activate() {
        long tempTemp = getTemperature();
        long[] tempIngredient = getIngredient().getTemperature();
        long longTempIngredient = asLong(tempIngredient);
        getIngredient().cool(Math.abs(longTempIngredient-tempTemp));
    }


}