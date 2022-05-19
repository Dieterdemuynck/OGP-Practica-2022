package main.java.ingredient.exception;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import main.java.Laboratory;

public class IngredientNotPresentException extends RuntimeException {

    /**
     * Required because this class inherits from Exception.
     * (which in its turn implements the interface Serializable)
     */
    private static final long serialVersionUID = 5L;
    /**
     * Variable referencing to the name of an ingredient that was not present.
     */
    private final String name;
    /**
     * Variable referencing to the Laboratory in which an ingredient name was not present.
     */
    private final Laboratory laboratory;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this new ingredient not present exception involving the given name and laboratory.
     *
     * @post The name involved in the new ingredient not present exception is set to the given name.
     *       | new.getName() == name
     * @post The laboratory involved in the new ingredient not present exception is set to the given laboratory.
     *       | new.getLaboratory() == laboratory
     * @param   name
     *          the name for the new ingredient not present exception.
     * @param   laboratory
     *          the laboratory for the new ingredient not present exception.
     */
    public IngredientNotPresentException(String name, Laboratory laboratory) {
        this.name = name;
        this.laboratory = laboratory;
    }

    /* *********************************************************
     * NAME
     * *********************************************************/

    /**
     * Return the name involved in this ingredient not present exception.
     */
    @Basic
    @Immutable
    public String getName() {
        return name;
    }

    /**
     * Return the laboratory involved in this ingredient not present exception.
     */
    @Basic
    @Immutable
    public Laboratory getLaboratory() {
        return laboratory;
    }
}
