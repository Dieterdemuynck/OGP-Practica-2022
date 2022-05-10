public class Transmogrifier extends AlchemicDevice{

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
