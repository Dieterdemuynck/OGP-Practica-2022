public abstract class AlchemicDevice {

    private AlchemicIngredient ingredient;
    private int ID;
    private Laboratory laboratory;

    public AlchemicIngredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(AlchemicIngredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public void insert(IngredientContainer ingredient) {
        this.ingredient = ingredient.getContent();
    }

    public IngredientContainer retrieve(IngredientContainer container) {
        if (container.getContent().getQuantity() == 0) {
            container.insert(getIngredient()) ;
            setIngredient(null);}
        return container;
    }

}
