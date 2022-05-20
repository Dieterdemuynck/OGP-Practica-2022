package main.java.device;

import main.java.device.exception.DeviceNotEmptyException;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * @invar   The Kettle's content has no null elements
 */
public class Kettle extends Device {

    /**
     * Variable referencing the total added ingredients, contained in the kettle.
     */
    private List<AlchemicIngredient> addedIngredients = new ArrayList<>();  // Each initialization creates an empty list


    /**
     * Returns the ingredients contained in the kettle.
     * @return the ingredients contained in the kettle.
     */
    public List<AlchemicIngredient> getAddedIngredients() {
        return addedIngredients;
    }

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    // Specification done in Device
    @Override
    public Device.DeviceType getDeviceType() {
        return DeviceType.Kettle;
    }

    /* *********************************************************
     * CONSTRUCTOR TODO
     * *********************************************************/

    public Kettle() {
        super();
    }

    /* *********************************************************
     * ADDED INGREDIENTS
     * *********************************************************/

    /**
     * Sets the ingredients contained in the kettle, to a given List of ingredients.
     * @param addedIngredients ArrayList of the ingredients that will be contained in the kettle.
     */
    public void setAddedIngredients(ArrayList<AlchemicIngredient> addedIngredients) {
        this.addedIngredients = addedIngredients;
    }

    /**
     * TODO Spec
     * @param container
     */
    @Override
    public void insert(IngredientContainer container) {
        if (container != null && !container.isEmpty()) {
            getAddedIngredients().add(container.extract());
        }
    }

    /**
     * Empties the kettle. This can be used when an incorrect ingredient is put inside the Kettle.
     *
     * @post The Kettle is empty after running this method.
     *       | new.getAddedIngredients().isEmpty()
     */
    public void empty() {
        // No need to reallocate space for a new, empty list if the list is already empty
        if (!this.getAddedIngredients().isEmpty())
            this.addedIngredients = new ArrayList<>();
    }


    /* *********************************************************
     * ACTIVATE
     * *********************************************************/

    /**
     * Activates the kettle and mixes all ingredients inside
     * TODO: pre & post
     */
    public void activate() {
        // A device may only be activated if it is in a laboratory
        if (getIngredient() != null) {
            throw new DeviceNotEmptyException(this);
        }
        if (getLaboratory() != null && !getAddedIngredients().isEmpty()) {
            // Take out the last element inside the Kettle, and mix it with the rest.
            // Then, set the output ingredient field to the mix.
            setIngredient(
                    getAddedIngredients().remove(getAddedIngredients().size() - 1).mixWith(getAddedIngredients())
            );
            // Empty the addedIngredients list
            empty();
        }
    }
}


