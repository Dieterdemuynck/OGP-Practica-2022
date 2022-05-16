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

    public IngredientContainer(AlchemicIngredient ingredient) {
        this(Unit.findSmallestFittingContainer(ingredient.getQuantity(), ingredient.getUnit(), ingredient.getState()));
        // TODO: ingredient.size could be larger than this.capacity... can we make a pre condition?
        this.content = ingredient;
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
     * TODO: should we keep this precondition or test for it? this is a bit of a mess of nominal and total rn.
     * @pre     The alchemical Ingredient is not null
     *        | alchemicIngredient != null
     * @post    The contents of this container is set to the given alchemical ingredient if it does not exceed
     *          the capacity.
     *        | if (doesNotExceedCapacity(alchemicIngredient) && capacity.getState() == alchemicIngredient.getState())
     *        |     new.getContents() == alchemicIngredient;
     * @param   alchemicIngredient  The alchemical ingredient to store inside the container
     */
    public void insert(AlchemicIngredient alchemicIngredient) {
        if (doesNotExceedCapacity(alchemicIngredient) && hasSameState(alchemicIngredient)) {
            setContent(alchemicIngredient);
        }
        // TODO: will we use total programming or defensive? If it's the latter, best to throw an exception here!
        // If it's the former, maybe we should return a boolean for if the ingredient was actually inserted or not?
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

    /**
     * Checker
     * TODO: documentation
     * @return
     */
    public boolean hasValidContent() {
        return getContent() == null || getCapacity().getValue() >= getContent().getQuantityInSpoons();
    }

    public boolean doesNotExceedCapacity(AlchemicIngredient alchemicIngredient) {
        return alchemicIngredient == null || getCapacity().getValue() >= alchemicIngredient.getQuantityInSpoons();
    }

    /**
     * Handy checker
     * TODO: documentation
     * @param alchemicIngredient
     * @return
     */
    public boolean hasSameState(AlchemicIngredient alchemicIngredient) {
        return getCapacity().getRespectiveState() == null
                || getCapacity().getRespectiveState() == alchemicIngredient.getState();
    }
}
