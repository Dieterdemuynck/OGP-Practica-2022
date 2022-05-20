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
            throw new DeviceNotEmptyException(this.ingredient, ingredient);
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
        setIngredient(ingredientContainer.extract());
        /* There's nothing referencing the container, so there's nothing to "terminate". All we can do is empty its
         * contents
         */
    }

    /* ***************************
     * ALCHEMIC INGREDIENT - RETRIEVE
     * ***************************/

    // TODO: I think "storeContentsIn(Container)" is a better name.
    // Should we return the container itself too? Do we really need to be able to chain methods?
    public IngredientContainer retrieve(IngredientContainer container) {
        //TODO volgens mij heeft dit geen input nodig, je moet gwn een container teruggeven waarin dit ingredient kan, denkik.
        if (container.getContent() == null) { //TODO ook nagaan of in container kan? Dieter da's me quantities =)
            container.insert(getIngredient());
            setIngredient(null);
        }
        return container;
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
