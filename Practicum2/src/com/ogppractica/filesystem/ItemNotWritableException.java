package com.ogppractica.filesystem;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling illegal attempts to change an item.
 *
 * @author Tommy Messelis
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 2.2
 */
public class ItemNotWritableException extends RuntimeException {

    /**
     * Required because this class inherits from Exception
     */
    private static final long serialVersionUID = 1L;

    /**
     * Variable referencing the item to which change was denied.
     */
    private final Item item;

    /**
     * Initialize this new item not writable exception involving the
     * given item.
     *
     * @param    item The item for the new item not writable exception.
     * @post The item involved in the new item not writable exception
     * is set to the given item.
     * | new.getItem() == item
     */
    public ItemNotWritableException(Item item) {
        this.item = item;
    }

    /**
     * Return the item involved in this item not writable exception.
     */
    @Basic
    @Immutable
    public Item getItem() {
        return item;
    }

}