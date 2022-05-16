package main.java.ingredient;

public enum State {
    Powder(0),
    Liquid(1),
    ;
    private final int ID;

    State(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return this.ID;
    }
}
