import java.util.ArrayList;

public class IngredientType {

    private String name;
    private ArrayList<Integer> StandardTemperature;
    private State state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getStandardTemperature() {
        return StandardTemperature;
    }

    public void setStandardTemperature(ArrayList<Integer> standardTemperature) {
        StandardTemperature = standardTemperature;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
