package main.java.ingredient;

public class IngredientContainer {

    private final Unit capacity;
    private AlchemicIngredient content = null;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    public IngredientContainer(Unit capacity) {
        this.capacity = capacity;
    }

    /**
     *
     *
     * @pre     There is at least one container unit which can fit all the given ingredient.
     *          | ingredient.getState().hasContainer()
     * @param   ingredient
     */
    public IngredientContainer(AlchemicIngredient ingredient) {
        this(ingredient.getState().findSmallestFittingContainer(ingredient.getAmount(), ingredient.getUnit()));
        insert(ingredient);
    }

    /* *********************************************************
     * CAPACITY
     * *********************************************************/

    public Unit getCapacity() {
        return capacity;
    }


    /* *********************************************************
     * ALCHEMIC INGREDIENT
     * *********************************************************/

    public AlchemicIngredient getContent() {
        return content;
    }

    /**
     * Private setter for the content of the container.
     *
     * @param alchemicIngredient The alchemicIngredient to which the content is set.
     */
    private void setContent(AlchemicIngredient alchemicIngredient) {
        this.content = alchemicIngredient;
    }

    /**
     * Inserts an alchemical ingredient into the container. If the ingredient does not fit, it will shave off any excess
     * ingredient by creating a new alchemicIngredient object which fills the container.
     *
     * @post    The contents of this container is set to the given alchemical ingredient. If the alchemical ingredient
     *          does not fit inside the container, a new alchemicIngredient object is made with its quantity (amount
     *          and unit) set to 1 times the capacity of the container.
     *          | if (ingredient.getState().compareInSameState(1, getCapacity(), ingredient.getAmount(), ingredient.getUnit())
     *          |      < 0)
     *          |     new.getContents() == ingredient.copyAllValsExcept(1, getCapacity());
     *          | else
     *          |     new.getContents() == ingredient;
     * @param   ingredient  The alchemical ingredient to store inside the container
     */
    public void insert(AlchemicIngredient ingredient) {
        if (ingredient.getState().compareInSameState(1, getCapacity(), ingredient.getAmount(), ingredient.getUnit())
                < 0) {
            ingredient = ingredient.copyAllValsExcept(1, getCapacity());
        }
        setContent(ingredient);
    }

    /**
     * Empties the contents of the container and returns it.
     *
     * @post    The container is empty
     *        | new.getContents() == null
     * @return  The alchemicIngredient which was inside the container prior to extraction.
     *        | result == this.getContents()
     */
    public AlchemicIngredient extract() {
        AlchemicIngredient contents = getContent();
        empty();
        return contents;
    }

    /**
     * A function specifically to simplify and intuitively emptying the container.
     * Private function, no need for specification, really.
     *
     * author: パリピディーター
     */
    private void empty() {
        setContent(null);
    }

    // TODO: FIX OR DELETE THESE NEXT CHECKERS
//    /**
//     * Checker
//     * TODO: documentation
//     * @return
//     */
//    public boolean hasValidContent() {
//        return getContent() == null || getCapacity().getValue() >= getContent().getQuantityInSpoons();
//    }
//
//    public boolean doesNotExceedCapacity(AlchemicIngredient alchemicIngredient) {
//        return alchemicIngredient == null || getCapacity().getValue() >= alchemicIngredient.getQuantityInSpoons();
//    }

//    /**
//     * Handy checker
//     * TODO: documentation
//     * @param alchemicIngredient
//     * @return
//     */
//    public boolean hasSameState(AlchemicIngredient alchemicIngredient) {
//        return getCapacity().getRespectiveState() == null
//                || getCapacity().getRespectiveState() == alchemicIngredient.getState();
//    }
}
