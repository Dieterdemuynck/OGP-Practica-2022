package main.java.device;

import be.kuleuven.cs.som.annotate.Model;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.Quantity;
import main.java.ingredient.State;

/**
 * The Transmogrifier is an implementation of the abstract class Device. This device has the DeviceType
 * "DeviceType.Transmogrifier".
 * It can hold a single AlchemicIngredient instance, which upon activation of this device,
 * will change the State (and recalculates its quantity-values to the largest fitting representing Unit of
 * the resulting State) to the Transmogrifier's State.
 *
 * @invar   The Transmogrifier's State is never null
 *          | getState() != null
 */
public class Transmogrifier extends Device {

    /**
     * Variable referencing the resulting State the present alchemical ingredient will have after activation.
     */
    private State resultingState;

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    // Specification done in Device
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

    /**
     * Returns the State the Transmogrifier is set to.
     * @return The State the Transmogrifier is set to.
     */
    public State getState() {
        return resultingState;
    }

    /**
     * Sets the resultingState of the Transmogrifier to the given State, if the given State is not null.
     *
     * @post    The resultingState will be set to the given state if it is not null
     *          | if (state != null) new.getState() == state;
     *
     * @param   state
     *          The State to which we want to set the Transmogrifier, to convert the State of an ingredient.
     */
    public void setState(State state) {
        if (state != null)
            this.resultingState = state;
    }

    /* *********************************************************
     * ACTIVATE
     * *********************************************************/

    /**
     * Activates the Transmogrifier and converts the present Ingredient to the Transmogrifier's current State.
     *
     * @effect  If there is ingredient inside the device and
     *          the Transmogrifier's State differs from the present AlchemicIngredient's State,
     *          the present AlchemicIngredient's State will be set to the Transmogrifier's State, and its
     *          Unit and amount will be adjusted accordingly to have the largest fitting Unit and to lose as
     *          little ingredient as possible.
     *          | if (this.getIngredient() != null && this.getIngredient().getState() != this.getState())
     *          |   new.getIngredient() ==
     *          |     this.getIngredient().copyAllValsExcept(
     *          |       State.covertTo(this.getIngredient, this.getState()).getAmount(),
     *          |       State.covertTo(this.getIngredient, this.getState()).getUnit(),
     *          |       this.getState()
     *          |     );
     */
    @Model
    public void activate() {
        // A device may only be activated if it is in a laboratory
        if (getLaboratory() != null) {
            // Only if there is ingredient present and the State differs do we want to do any computation at all:
            if (getIngredient() != null && getIngredient().getState() != getState()) {
                // Step 1: Find the Quantity of the new ingredient
                Quantity quantity = State.convertTo(getIngredient(), getState());

                // Step 2: Make a copy of the old ingredient, but with different quantity-values and state
                AlchemicIngredient newIngredient = getIngredient().copyAllValsExcept(
                        quantity.getAmount(), quantity.getUnit(), getState());

                // Step 3: Set the present ingredient to the new ingredient
                setIngredient(newIngredient);
            }
        }
    }
}
