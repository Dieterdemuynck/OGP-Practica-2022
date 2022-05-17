package main.java.ingredient;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

// TODO: Update this horrible mess to use the unit table (see cnt file) instead.
public enum Unit {
    // State-independent
    Spoon(true),
    Storeroom(false),

    // Liquid
    Drop(false),

    Vial(true),
    Bottle(true),
    Jug(true),
    Barrel(true),

    // Powder
    Pinch(false),

    Sachet(true),
    Box(true),
    Sack(true),
    Chest(true),
    ;

    // TODO: Delete all of the fields and getters etc.
    // Properties
    private final boolean canBeContainer;

    Unit(boolean canBeContainer) {
        this.canBeContainer = canBeContainer;
    }

    /* *********************************************************
     * CONTAINER
     * *********************************************************/
    public boolean canBeContainer() {
        return canBeContainer;
    }

}
