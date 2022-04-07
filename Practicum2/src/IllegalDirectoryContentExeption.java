import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * A class for signaling illegal to add an item to a directoy.
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 4
 */

public class IllegalDirectoryContentExeption extends RuntimeException{

    /**
     * Required because this class inherits from Exception
     */
    private static final long serialVersionUID = 2L;

    /**
     * Variable referencing the directory to which the item cannot be added.
     */
    private final Directory dir ;

    /**
     * Variable referencing the item which cannot be added to the directory.
     */
    private final Item item ;

    /**
     * Initialize this new illegal item exception involving the
     * given item and directory.
     *
     * @param item  The item which cannot be added to the directory.
     * @param dir   The directory to which the item cannot be added.
     * @post The item involved in the new illegal item exception
     * is set to the given item.
     * | new.getItem() == item
     * @post The directory (dir) involved in the new illegal item exception
     * is set to the given directory.
     * | new.getDirectory() == dir
     */
    public IllegalDirectoryContentExeption(Directory dir, Item item) {  // TODO: adjust FileTest
        this.dir = dir;
        this.item = item;
    }

    /**
     * Return the item involved in the exception.
     */
    @Basic
    @Immutable
    public Item getItem() {
        return item;
    }

    /**
     * Return the directory involved in the exception.
     */
    @Basic
    @Immutable
    public Item getDirectory() {
        return dir;
    }

}

