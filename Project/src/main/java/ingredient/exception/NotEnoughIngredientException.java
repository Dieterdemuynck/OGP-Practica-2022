package main.java.ingredient.exception;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import main.java.Laboratory;
import main.java.ingredient.AlchemicIngredient;
import main.java.ingredient.Unit;

public class NotEnoughIngredientException extends RuntimeException {

    /**
     * Required because this class inherits from Exception.
     * (which in its turn implements the interface Serializable)
     */
    private static final long serialVersionUID = 7L;
    /**
     * Variable referencing the ingredient of which not enough was present in a Laboratory.
     */
    private final AlchemicIngredient ingredient;
    /**
     * Variable referencing the amount in the given unit of the given ingredient that was requested.
     */
    private final int amount;
    /**
     * Variable referencing the unit of the given ingredient that was requested.
     */
    private final Unit unit;
    /**
     * Variable referencing the Laboratory in which an amount of ingredient was requested that was more than was stored.
     */
    private final Laboratory laboratory;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this new ingredient not present exception involving the given name and laboratory.
     *
     * @post The ingredient involved in the new not enough ingredient exception is set to the given ingredient.
     *       | new.getIngredient() == ingredient
     * @post The amount involved in the new not enough ingredient exception is set to the given amount.
     *       | new.getAmount() == amount
     * @post The unit involved in the new not enough ingredient exception is set to the given unit.
     *       | new.getUnit() == Unit
     * @post The laboratory involved in the new not enough ingredient exception is set to the given laboratory.
     *       | new.getLaboratory() == laboratory
     * @param   ingredient
     *          the ingredient for the new not enough ingredient exception.
     * @param   amount
     *          the amount for the new not enough ingredient exception.
     * @param   unit
     *          the unit for the new not enough ingredient exception.
     * @param   laboratory
     *          the laboratory for the new not enough ingredient exception.
     */
    public NotEnoughIngredientException(AlchemicIngredient ingredient, int amount, Unit unit, Laboratory laboratory) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
        this.laboratory = laboratory;
    }

    /* *********************************************************
     * NAME
     * *********************************************************/

    /**
     * Return the ingredient involved in this not enough ingredient exception.
     */
    @Basic
    @Immutable
    public AlchemicIngredient getIngredient() {
        return ingredient;
    }

    /**
     * Return the amount involved in this not enough ingredient exception.
     */
    @Basic
    @Immutable
    public int getAmount() {
        return amount;
    }

    /**
     * Return the unit involved in this not enough ingredient exception.
     */
    @Basic
    @Immutable
    public Unit getUnit() {
        return unit;
    }

    /**
     * Return the laboratory involved in this not enough ingredient exception.
     */
    @Basic
    @Immutable
    public Laboratory getLaboratory() {
        return laboratory;
    }
}
