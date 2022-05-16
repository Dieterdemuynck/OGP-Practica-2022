package main.java.ingredient;

public record Quantity(int amount, Unit unit) {

    public int getAmount() {
        return amount;
    }

    public Unit getUnit() {
        return unit;
    }
}
