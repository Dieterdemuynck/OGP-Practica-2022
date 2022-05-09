import java.util.Random;

public class Oven extends AlchemicDevice { //why do they call it oven when you of in the cold food of out heat the food

    private long temperature;

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        if (temperature > 0) {
            this.temperature = temperature;
        }
    }

    public void activate() {
        Random rand = new Random();
        double deviation = rand.nextDouble(0.95, 1.05);
        long tempTemperature = Math.round(getTemperature() * deviation);
        long[] tempIngredient = getIngredient().getTemperature();
        long longTempIngredient = asLong(tempIngredient);
        getIngredient().heat(Math.abs(longTempIngredient - tempTemperature));
    }

}
