import be.kuleuven.cs.som.annotate.*;

/**
 * TODO: invariants
 * A class of links.
 *
 * -> name
 * -> creationTime
 * -> modificationTime
 * -> directory
 * -> LinkedItem
 *
 * @author  Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 4
 *
 * @note		See Coding Rule 48 for more info on the encapsulation of class invariants.
 */

public class Link extends Item {

    /* *********************************************************
     * Constructors
     * *********************************************************/

    /**
     * Initialize a new link with given name, size and writability.
     *
     * @param name The name of the new link.
     *
     * @effect The name of the link is set to the given name.
     * If the given name is not valid, a default name is set.
     * | setName(name)
     *
     * @post The new creation time of this link is initialized to some time during
     * constructor execution.
     * | (new.getCreationTime().getTime() >= System.currentTimeMillis()) &&
     * | (new.getCreationTime().getTime() <= (new System).currentTimeMillis())
     * @post The new link has no time of last modification.
     * | new.getModificationTime() == null
     *
     * @note The constructor is annotated raw because at the start of the execution, not all fields are
     * defaulted to a value that is accepted by the invariants.
     * E.g. the name is defaulted to null, which is not allowed,
     * thus the object is in a raw state upon entry of the constructor.
     */
    @Raw
    public Link(Directory dir, String name, Item linkedItem) {
        super(name);  // Damn you java, for making the call to super a necessity to be the first statement!!!
        if (linkedItem instanceof Link) {
            throw new IllegalArgumentException("linkedItem cannot be instance of Link");
        }
        this.linkedItem = linkedItem;
        setParentDirectory(dir);
        dir.addToContents(this);
    }


    /* *********************************************************
     * linkedItem - defensive programming
     * *********************************************************/

    /**
     * Variable referencing the linked item.
     *
     */
    private final Item linkedItem;  // Assigned during creation.

    /**
     * Return the linked item.
     * @return The item to which the link references.
     */
    @Immutable @Basic
    public Item getLinkedItem() {
        return linkedItem;
    }

    /**
     * Sets the parent directory of the item to the given directory, if the given directory is not null.
     *
     * @throws IllegalArgumentException if the parentDirectory == null
     * @param  parentDirectory  The directory in to which the link will be moved or in which it will be created.
     */
    @Override
    protected void setParentDirectory(Directory parentDirectory) {
        if (parentDirectory == null) {
            throw new IllegalArgumentException("Parent directory of File may not be null.");
        }
        super.setParentDirectory(parentDirectory);
    }

    public void terminate() {
        if (!isTerminated()) {
            setTerminated(true);
            this.getParentDirectory().removeFromContents(this);
            // Reference to parent directory does not need to be removed. (could be useful for restoring if possible.)
        }
    }

    @Override
    public void deleteRecursive(){
        terminate();
    }
}