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
     * @pre     There is at least one container unit which can fit all the given ingredient.
     *          | TODO: formal specification
     * @param   ingredient
     */
    public IngredientContainer(AlchemicIngredient ingredient) {
        this(ingredient.getState().findSmallestFittingContainer(ingredient.getQuantity(), ingredient.getUnit()));
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
     * Inserts an alchemical ingredient into the container, if it does not exceed the container's capacity and the
     * ingredient's state can be held by the container.
     *
     * @pre     The inserted ingredient can fit in the container.
     *          | this.getCapacity() >= alchemicIngredient.getAmount() * alchemicIngredient.getUnit() TODO: fix
     * @post    The contents of this container is set to the given alchemical ingredient if it does not exceed
     *          the capacity.
     *          | if (doesNotExceedCapacity(alchemicIngredient) && capacity.getState() == alchemicIngredient.getState())
     *          |     new.getContents() == alchemicIngredient;
     * @param   alchemicIngredient  The alchemical ingredient to store inside the container
     */
    public void insert(AlchemicIngredient alchemicIngredient) {
        /* Since quantities are supposed to be nominally programmed, we don't test anything.
         * It kind of makes me sad, since I've made a nice function which allows us to do this totally in a somewhat
         * neat way... Please... I want total programming.... Let me just shave off some excess ingredient :(
         */
        setContent(alchemicIngredient);
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
