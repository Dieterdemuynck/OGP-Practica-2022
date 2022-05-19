package main.java.ingredient;

/**
 * A class of the quantity of alchemic ingredients
 * @author Dieter Demuynck, Hannes Ingelaere and Ine Malfait
 * @version 1.0
 */

public record Quantity(int amount, Unit unit) {

    /**
     * Returns the amount of units.
     * @return the amount of units.
     */

    public int getAmount() {
        return amount;
    }

    /**
     * Returns the unit.
     * @return the unit.
     */

    public Unit getUnit() {
        return unit;
    }
}
