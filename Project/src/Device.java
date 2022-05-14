public abstract class Device {

    private AlchemicIngredient ingredient = null;
    private int ID;  // TODO: My idea of an ID representing the index in a list is bad. Revision needed (@Hannes)
    // As for the revision, perhaps just have a bunch of if-else statements checking "instanceof DeviceX"?
    // Seems bad/inefficient, any way to optimize that?
    private Laboratory laboratory;

    /* *********************************************************
     * ALCHEMIC INGREDIENT
     * *********************************************************/

    public AlchemicIngredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(AlchemicIngredient ingredient) {
        if (!isEmptyDevice() && ingredient != null){
            throw new IngredientNotEmptyException(this.ingredient, ingredient);
        }
        this.ingredient = ingredient;
    }
    public boolean isEmptyDevice(){
        return this.ingredient == null;
    }

    /* ***************************
     * ALCHEMIC INGREDIENT - INSERT
     * ***************************/
    public void insert(IngredientContainer ingredientContainer) {
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
     * ID - Wat is hiervan het nut?
     * TODO: Remove and create something else idk.
     *  We still gotta be able to check if a device type is present in a lab. Abstract "getType()" with nested enum?
     * *********************************************************/

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /* *********************************************************
     * LABORATORY
     * *********************************************************/

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        // TODO: Do we allow ingredients to move between laboratories through devices?
        this.laboratory = laboratory;
    }

}
