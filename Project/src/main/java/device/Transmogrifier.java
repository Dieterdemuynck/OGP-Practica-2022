package main.java.device;

import main.java.ingredient.State;

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
        getIngredient().changeState(resultingState);
    }
}
