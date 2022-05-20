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
        if (!isEmptyDevice() && ingredient != null){
            throw new DeviceNotEmptyException(this);
        }
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
            setIngredient(ingredientContainer.extract());
        }
    }

    /* ***************************
     * ALCHEMIC INGREDIENT - RETRIEVE
     * ***************************/

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

    @Basic
    public Laboratory getLaboratory() {
        return laboratory;
    }

    /**
     * The given laboratory will be the laboratory this device has been placed in.
     * @param laboratory
     */
    public void setLaboratory(Laboratory laboratory) {
        // TODO: Do we allow ingredients to move between laboratories through devices?
        this.laboratory = laboratory;
    }

    /* *********************************************************
     * METHODS
     * *********************************************************/

    public abstract void activate();

}
