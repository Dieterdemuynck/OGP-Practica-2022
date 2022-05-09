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
        long longTempIngredient = getLong(tempIngredient);
        getIngredient().cool(Math.abs(longTempIngredient-tempTemp));
    }

    private long getLong(long[] temperature){
        if (temperature[0] != 0){
            return -temperature[0];
        }
        else {
            return temperature[1];
        }
    }
}

