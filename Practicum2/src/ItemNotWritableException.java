import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling illegal attempts to change a file.
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
     * Variable referencing the file to which change was denied.
     */
    private final Item item;

    /**
     * Initialize this new file not writable exception involving the
     * given file.
     *
     * @param    item The file for the new file not writable exception.
     * @post The file involved in the new file not writable exception
     * is set to the given file.
     * | new.getFile() == file
     */
    public ItemNotWritableException(Item item) {  // TODO: adjust FileTest
        this.item = item;
    }

    /**
     * Return the file involved in this file not writable exception.
     */
    @Basic
    @Immutable
    public Item getItem() {
        return item;
    }

}