public class IngredientContainer {

    private Quantity capacity;
    private AlchemicIngredient content;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /* *********************************************************
     * CAPACITY
     * *********************************************************/

    public Quantity getCapacity() {
        return capacity;
    }

    public void setCapacity(Quantity capacity) {
        this.capacity = capacity;
    }


    /* *********************************************************
     * ALCHEMIC INGREDIENT
     * *********************************************************/
    public AlchemicIngredient getContent() {
        return content;
    }

    public void insert(AlchemicIngredient ingredient) {
        if (capacity.getValue() >= ingredient.getQuantity()
                && getCapacity().getRespectiveState() == ingredient.getState()) {
            this.content = ingredient;
        }
    }

}
