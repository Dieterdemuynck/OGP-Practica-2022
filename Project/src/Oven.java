public class Oven extends AlchemicDevice { //why do they call it oven when you of in the cold food of out heat the food

    private long temperature;

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        if (temperature > 0) {
            this.temperature = temperature;}
    }

    public void activate() {
        long tempTemp = getTemperature();
        long[] ingredientTemp = getIngredient().getTemperature();
        if (ingredientTemp[0] > 0) {
            tempTemp -= ingredientTemp[0];
            ingredientTemp[0] = 0;
        }
        ingredientTemp[1] += tempTemp;

        getIngredient().setTemperature(ingredientTemp);
    }
}

