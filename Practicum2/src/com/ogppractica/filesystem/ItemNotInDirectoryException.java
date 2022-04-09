package com.ogppractica.filesystem;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import java.io.Serial;

public class ItemNotInDirectoryException extends RuntimeException {

    /**
     * Required because this class inherits from Exception
     */
    @Serial
    private static final long serialVersionUID = 3L;

    /**
     * Variable referencing the item to which change was denied.
     */
    private final Directory directory;
    private final Item item;

    /**
     * Initialize this new item not writable exception involving the
     * given item.
     *
     * @param   directory
     *          The directory in which an item is not present.
     * @param   item
     *          The item which is not present in the directory.
     * @post    The directory and item involved are set to the given directory and item.
     *          | new.getDirectory() == directory
     *          | new.getItem() == item
     */
    public ItemNotInDirectoryException(Directory directory, Item item) {
        this.directory = directory;
        this.item = item;
    }

    /**
     * Return the item which is not in the given directory.
     */
    @Basic
    @Immutable
    public Item getItem() {
        return item;
    }

    /**
     * Return the directory which does not contain the requested item.
     */
    @Basic
    @Immutable
    public Directory getDirectory() {
        return directory;
    }
}
