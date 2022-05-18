package main.java.device;

import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.Quantity;
import main.java.ingredient.State;

// TODO:
//  Should be nearly finished. All that's left is testing and specification. :)
public class Transmogrifier extends Device {

    private State resultingState;

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Transmogrifier;
    }

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Creates a new Transmogrifier device with the resultingState set to Liquid.
     *
     * @post    The resultingState of the new Transmogrifier device is Liquid
     *          | new.getState() == State.Liquid
     */
    public Transmogrifier() {
        this.resultingState = State.Liquid;
    }


    /* *********************************************************
     * STATE
     * *********************************************************/
    public State getState() {
        return resultingState;
    }

    /**
     * Sets the resultingState of the Transmogrifier to the given State, if the given State is not null.
     *
     * @post    The resultingState will be set to the given state if it is not null
     *          | if (state != null) new.getState() == state;
     *
     * @param state The State to which we want to set the Transmogrifier, to convert the State of an ingredient.
     */
    public void setState(State state) {
        if (state != null)
            this.resultingState = state;
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/

    public void activate() {
        if(getIngredient().getState() != getState()) {
            // TODO: transfer SpecialName to new Ingredient
            Quantity quantity = State.convertTo(getIngredient(), getState());

            // This is *the* reason why I wish I could just pass an ingredient type.
            setIngredient(new AlchemicIngredient(quantity.getAmount(), quantity.getUnit(),
                    getIngredient().getStandardTemperature(), getIngredient().getTemperature(),
                    getIngredient().getName(), getIngredient().getStandardState(), getState()));
        }
    }
}
