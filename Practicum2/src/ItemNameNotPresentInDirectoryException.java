import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling illegal attempts to change a item.
 *
 * @author Tommy Messelis
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 2.2
 */
public class ItemNameNotPresentInDirectoryException extends RuntimeException {

    /**
     * Required because this class inherits from Exception
     */
    private static final long serialVersionUID = 4L;

    /**
     * Variable referencing the item to which change was denied.
     */
    private final Directory directory;
    private final String name;

    /**
     * Initialize this new item not writable exception involving the
     * given item.
     *
     * @param   directory
     *          The directory in which the item name is not present
     * @param   name
     *          The string representing an item's name which is not present in the directory
     * @post    The directory and name involved in the exception is set to the given directory and name.
     *          | new.getDirectory() == directory
     *          | new.getName() == name
     */
    public ItemNameNotPresentInDirectoryException(Directory directory, String name) {
        this.directory = directory;
        this.name = name;
    }

    /**
     * Return the directory involved in this item name not present in directory exception.
     */
    @Basic
    @Immutable
    public Directory getDirectory() {
        return directory;
    }

    /**
     * Return the name involved in this item name not present in directory exception.
     */
    @Basic
    @Immutable
    public String getName() {
        return name;
    }
}