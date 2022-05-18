package main.java.device;

import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.State;
import main.java.ingredient.Unit;

public class Transmogrifier extends Device {

    private State resultingState;

    /* *********************************************************
     * CONSTRUCTOR TODO
     * *********************************************************/


    /* *********************************************************
     * STATE
     * *********************************************************/
    public State getState() {
        return resultingState;
    }

    public void setState(State state) {
        this.resultingState = state;
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/
    public void activate() {// TODO pls dubbelchecken
        AlchemicIngredient newIngredient = new AlchemicIngredient((int) Math.round(getIngredient().getQuantityInSpoons()), Unit.Spoon, getIngredient().getStandardTemperature(), getIngredient().getTemperature(), getIngredient().getName(), getIngredient().getStandardState(),resultingState);
        setIngredient(newIngredient);
    }
}
