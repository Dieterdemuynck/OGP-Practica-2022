package main.java.device;

import be.kuleuven.cs.som.annotate.Basic;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.IngredientContainer;
import main.java.device.exception.DeviceNotEmptyException;
import main.java.Laboratory;

public abstract class Device {

    private AlchemicIngredient ingredient = null;
    private Laboratory laboratory;

    /* *********************************************************
     * ALCHEMIC INGREDIENT
     * *********************************************************/

    protected AlchemicIngredient getIngredient() {
        return ingredient;
    }

    public IngredientContainer getLargestFittingContainerForContents() {
        return new IngredientContainer(                                   // Create a container with...
                getIngredient().getState().findSmallestFittingContainer(  // the best fit container unit for...
                getIngredient().getAmount(), getIngredient().getUnit())); // this amount of ingredient
    }

    public void setIngredient(AlchemicIngredient ingredient) throws DeviceNotEmptyException {
        this.ingredient = ingredient;
    }
    public boolean isEmptyDevice(){
        return this.ingredient == null;
    }

    /* ***************************
     * ALCHEMIC INGREDIENT - INSERT
     * ***************************/

    public void insert(IngredientContainer ingredientContainer) throws DeviceNotEmptyException {
        if (ingredientContainer != null) {
            if (!isEmptyDevice() && !ingredientContainer.isEmpty()){
                throw new DeviceNotEmptyException(this);
            }
            setIngredient(ingredientContainer.extract());
        }
    }

    /* ***************************
     * ALCHEMIC INGREDIENT - RETRIEVE
     * ***************************/

    /**
     * Retrieves the ingredient from the device and puts it in the best fitting container. If the container is too
     * small, the rest of the ingredient will be lost.
     * @effect creates a new ingredient container with the present ingredient and lowers quantity if necessary,
     *         null if it is empty.
     *         | new IngredientContainer(getIngredient())
     * @return
     */
    public IngredientContainer retrieve() {
        if (getIngredient()==null){
            return null;
        }
        else {
            return new IngredientContainer(getIngredient());
        }
    }

    protected static long asLong(long[] temperature){
        if (temperature[0] != 0){
            return -temperature[0];
        }
        else {
            return temperature[1];
        }
    }

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    /**
     * An enum listing all possible types of devices. This is what Laboratory bases itself on for device types, as only
     * 1 device may be present of each type.
     */
    public enum DeviceType{
        CoolingBox, Oven, Kettle, Transmogrifier
    }

    /**
     * Returns the DeviceType of this device.
     * @return The DeviceType of this device.
     */
    public abstract DeviceType getDeviceType();

    /* *********************************************************
     * LABORATORY
     * *********************************************************/

    /**
     * The laboratory in which this device is currently present. Null if it is not present in any laboratory.
     * @return The laboratory in which this device is present
     *         | result == this.laboratory
     */
    @Basic
    public Laboratory getLaboratory() {
        return laboratory;
    }

    /**
     * The given laboratory will be the laboratory this device has been placed in.
     * @param laboratory The laboratory in which we want to place the device.
     */
    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    /* *********************************************************
     * METHODS
     * *********************************************************/

    public abstract void activate();

}
