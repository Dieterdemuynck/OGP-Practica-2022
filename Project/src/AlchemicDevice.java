public abstract class AlchemicDevice {//TODO In opgave staat er gwn Device? Kwn kzou lik de naam van de opdracht gebruiken nie?

    private AlchemicIngredient ingredient = null;
    private int ID;
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
    public void insert(IngredientContainer ingredient) {
        setIngredient(ingredient.getContent()); //todo Container waarin het zat, moet da nie getermineerd worden?
    }

    /* ***************************
     * ALCHEMIC INGREDIENT - RETRIEVE
     * ***************************/
    public IngredientContainer retrieve(IngredientContainer container) {
        //TODO volgens mij heeft dit geen input nodig, je moet gwn een container teruggeven waarin dit ingredient kan, denkik.
        if (container.getContent().getQuantity() == 0) { //TODO ook nagaan of in container kan? Dieter da's me quantities =)
            container.insert(getIngredient()) ;
            setIngredient(null);}
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
        this.laboratory = laboratory;
    }

}
