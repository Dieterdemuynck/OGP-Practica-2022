package main.java.device;

import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.Quantity;
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
    public void activate() {
        if(getIngredient().getState() != getState()) {
            // TODO: transfer SpecialName to new Ingredient
            // This is part of the reason why I wish I could just pass an ingredient type.
            Quantity quantity = getIngredient().getState().convertTo(getIngredient(), getState());
            setIngredient(new AlchemicIngredient(quantity.getAmount(), quantity.getUnit(),
                    getIngredient().getStandardTemperature(), getIngredient().getTemperature(),
                    getIngredient().getName(), getIngredient().getStandardState(), getState()));
        }
    }
}
