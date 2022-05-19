package main.java.ingredient;

/**
 * A class of ingredient containers, involving capacity and content.
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */
public class IngredientContainer {
    /**
     * Variable referencing the capacity of this ingredient container
     */
    private final Unit capacity;
    /**
     * Variable referencing the content of this ingredient container
     */
    private AlchemicIngredient content = null;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize a new ingredient container with given unit as capacity
     *
     * @param   capacity
     *          the capacity of the new ingredient container
     * @pre     capacity is a valid capacity
     *          | isValidCapacity(capacity)
     */
    public IngredientContainer(Unit capacity) {
        this.capacity = capacity;
    }

    /**
     * Initialize a new ingredient container with given alchemical ingredient
     *
     * @param   ingredient
     *          The content of the new ingredient container.
     * @pre     There is at least one container unit which can fit all the given ingredient.
     *          | ingredient.getState().hasContainer()
     * @post    The ingredient is inserted in the ingredient container
     *          | new.getContent() == ingredient
     */
    public IngredientContainer(AlchemicIngredient ingredient) {
        this(ingredient.getState().findSmallestFittingContainer(ingredient.getAmount(), ingredient.getUnit()));
        insert(ingredient);
    }

    /* *********************************************************
     * CAPACITY
     * *********************************************************/

    /**
     * Return the capacity of this ingredient container
     */
    public Unit getCapacity() {
        return capacity;
    }

    /**
     * checks whether the given capacity is a valid capacity
     *
     * @param   capacity
     *          the capacity to check
     * @return  True if and only if the capacity can be a container
     *          | result == capacity.canBeContainer()
     */
    public boolean isValidCapacity(Unit capacity){
        return capacity.canBeContainer();
    }


    /* *********************************************************
     * ALCHEMIC INGREDIENT
     * *********************************************************/

    public AlchemicIngredient getContent() {
        return content;
    }

    /**
     * Sets the content of this container to the given alchemical ingredient
     *
     * @param   alchemicIngredient
     *          The alchemicIngredient to which the content is set.
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
     *          | if (ingredient.getState().compareInSameState(1, getCapacity(), ingredient.getAmount(),
     *          |           ingredient.getUnit()) < 0)
     *          | new.getContents() == ingredient.copyAllValsExcept(1, getCapacity());
     *          | else new.getContents() == ingredient;
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
     *          | new.getContents() == null
     * @return  The alchemicIngredient which was inside the container prior to extraction.
     *          | result == this.getContents()
     */
    public AlchemicIngredient extract() {
        AlchemicIngredient contents = getContent();
        empty();
        return contents;
    }

    /**
     * Empties the content of the container
     */
    private void empty() {
        setContent(null);
    }
}
