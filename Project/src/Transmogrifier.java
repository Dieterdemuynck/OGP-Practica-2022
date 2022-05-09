public class Transmogrifier extends AlchemicDevice{
    private State resultingState;

    public State getState() {
        return resultingState;
    }

    public void setState(State state) {
        this.resultingState = state;
    }

    public void activate() {
        getIngredient().changeState(resultingState);
    }
}
