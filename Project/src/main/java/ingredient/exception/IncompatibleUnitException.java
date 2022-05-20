package main.java.ingredient.exception;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.Unit;

public class IncompatibleUnitException extends RuntimeException {

    /**
     * Required because this class inherits from Exception.
     * (which in its turn implements the interface Serializable)
     */
    private static final long serialVersionUID = 11L;
    /**
     * Variable referencing the ingredient that is incompatible with a given unit.
     */
    private final AlchemicIngredient ingredient;
    /**
     * Variable referencing the unit which is incompatible with a given ingredient.
     */
    private final Unit unit;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this new incompatible unit exception involving the given ingredient and unit.
     *
     * @post The ingredient involved in the new incompatible unit exception is set to the given ingredient.
     *       | new.getIngredient() == ingredient
     * @post The unit involved in the new incompatible unit exception is set to the given unit.
     *       | new.getUnit() == unit
     * @param   ingredient
     *          the ingredient for the new incompatible unit exception.
     * @param   unit
     *          the unit for the new incompatible unit exception.
     */
    public IncompatibleUnitException(AlchemicIngredient ingredient, Unit unit) {
        this.ingredient = ingredient;
        this.unit = unit;
    }

    /* *********************************************************
     * NAME
     * *********************************************************/

    /**
     * Return the ingredient involved in this incompatible unit exception.
     */
    @Basic
    @Immutable
    public AlchemicIngredient getIngredient() {
        return ingredient;
    }

    /**
     * Return the unit involved in this incompatible unit exception.
     */
    @Basic
    @Immutable
    public Unit getUnit() {
        return unit;
    }
}
