import be.kuleuven.cs.som.annotate.*;

/**
 * A class of files.
 *
 * @invar	Each file must have a properly spelled name.
 * 			| isValidName(getName())
 * @invar	Each file must have a valid size.
 * 			| isValidSize(tSize())
 * @invar   Each file must have a valid creation time.
 *          | isValidCreationTime(getCreationTime())
 * @invar   Each file must have a valid modification time.
 *          | canHaveAsModificationTime(getModificationTime())
 *
 * -> directory
 * -> writable
 * -> type
 *
 *
 * @author  Mark Dreesen
 * @author  Tommy Messelis
 * @author  Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 4
 * 
 * @note		See Coding Rule 48 for more info on the encapsulation of class invariants.
 */
public class File extends Item {

    /* *********************************************************
     * Constructors
     * *********************************************************/

    /**
     * Initialize a new file with given directory (dir), name, size, writability and type.
     *
     * @param dir      The directory of the new file.
     * @param name     The name of the new file.
     * @param size     The size of the new file.
     * @param writable The writability of the new file.
     * @param type     The type of the new file
     * @effect The directory of the file is set to the given directory dir.
     * | setParentDirectory(dir) TODO: controleren
     * @effect The name of the file is set to the given name.
     * If the given name is not valid, a default name is set.
     * | setName(name)
     * @effect The size is set to the given size (must be valid)
     * | setSize(size)
     * @effect The writability is set to the given flag
     * | setWritable(writable)
     * @effect The type of the file is set to the given type.
     * | this.type = type TODO: controleren
     * @post The new creation time of this file is initialized to some time during
     * constructor execution.
     * | (new.getCreationTime().getTime() >= System.currentTimeMillis()) &&
     * | (new.getCreationTime().getTime() <= (new System).currentTimeMillis())
     * @post The new file has no time of last modification.
     * | new.getModificationTime() == null
     * @note The constructor is annotated raw because at the start of the execution, not all fields are
     * defaulted to a value that is accepted by the invariants.
     * E.g. the name is defaulted to null, which is not allowed,
     * thus the object is in a raw state upon entry of the constructor.
     */
    @Raw
    public File(Directory dir, String name, int size, boolean writable, Type type) {
        super(name);
        setSize(size);
        setWritable(writable);
        this.type = type;
        setParentDirectory(dir);
    }

    /**
     * Initialize a new file with given directory (dir), name and type.
     *
     * @param dir  The directory of the new file.
     * @param name The name of the new file.
     * @param type The type of the new file.
     * @effect This new file is initialized with the given directory (dir), name, a zero size, true writability
     * and type
     * | this(dir,name,0,true,type)
     */
    @Raw
    public File(Directory dir, String name, Type type) {
        this(dir, name, 0, true, type);
    }


    /**
     * Change the name of this file to the given name.
     *
     * @param name The new name for this file.
     * @throws ItemNotWritableException(this) This file is not writable
     *                                        | ! isWritable()
     * @effect The name of this file is set to the given name,
     * if this is a valid name and the file is writable,
     * otherwise there is no change.
     * | if (isValidName(name) && isWritable())
     * | then setName(name)
     * @effect If the name is valid and the file is writable, the modification time
     * of this file is updated.
     * | if (isValidName(name) && isWritable())
     * | then setModificationTime()
     */
    @Override
    public void changeName(String name) throws ItemNotWritableException {
        if (isWritable()) {
            if (isValidName(name)) {
                super.setName(name);
                setModificationTime();
            }
        } else {
            throw new ItemNotWritableException(this);
        }
    }


    /* *********************************************************
     * size - nominal programming
     * *********************************************************/

    /**
     * Variable registering the size of this file (in bytes).
     */
    private int size = 0;

    /**
     * Variable registering the maximum size of any file (in bytes).
     */
    private static final int maximumSize = Integer.MAX_VALUE;


    /**
     * Return the size of this file (in bytes).
     */
    @Raw
    @Basic
    public int getSize() {
        return size;
    }

    /**
     * Set the size of this file to the given size.
     *
     * @param size The new size for this file.
     * @pre The given size must be legal.
     * | isValidSize(size)
     * @post The given size is registered as the size of this file.
     * | new.getSize() == size
     */
    @Raw
    @Model
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * Return the maximum file size.
     */
    @Basic
    @Immutable
    public static int getMaximumSize() {
        return maximumSize;
    }

    /**
     * Check whether the given size is a valid size for a file.
     *
     * @param size The size to check.
     * @return True if and only if the given size is positive and does not
     * exceed the maximum size.
     * | result == ((size >= 0) && (size <= getMaximumSize()))
     */
    public static boolean isValidSize(int size) {
        return ((size >= 0) && (size <= getMaximumSize()));
    }

    /**
     * Increases the size of this file with the given delta.
     *
     * @param delta The amount of bytes by which the size of this file
     *              must be increased.
     * @pre The given delta must be strictly positive.
     * | delta > 0
     * @effect The size of this file is increased with the given delta.
     * | changeSize(delta)
     */
    public void enlarge(int delta) throws ItemNotWritableException {
        changeSize(delta);
    }

    /**
     * Decreases the size of this file with the given delta.
     *
     * @param delta The amount of bytes by which the size of this file
     *              must be decreased.
     * @pre The given delta must be strictly positive.
     * | delta > 0
     * @effect The size of this file is decreased with the given delta.
     * | changeSize(-delta)
     */
    public void shorten(int delta) throws ItemNotWritableException {
        changeSize(-delta);
    }

    /**
     * Change the size of this file with the given delta.
     *
     * @param delta The amount of bytes by which the size of this file
     *              must be increased or decreased.
     * @throws ItemNotWritableException(this) This file is not writable.
     *                                        | ! isWritable()
     * @pre The given delta must not be 0
     * | delta != 0
     * @effect The size of this file is adapted with the given delta.
     * | setSize(getSize()+delta)
     * @effect The modification time is updated.
     * | setModificationTime()
     */
    @Model
    private void changeSize(int delta) throws ItemNotWritableException {
        if (isWritable()) {
            setSize(getSize() + delta);
            setModificationTime();
        } else {
            throw new ItemNotWritableException(this);
        }
    }

    /* *********************************************************
     * writable
     * *********************************************************/

    /**
     * Variable registering whether this file is writable.
     */
    private boolean isWritable = true;

    /**
     * Check whether this file is writable.
     */
    @Raw
    @Basic
    public boolean isWritable() {
        return isWritable;
    }

    /**
     * Set the writability of this file to the given writability.
     *
     * @param isWritable The new writability
     * @post The given writability is registered as the new writability
     * for this file.
     * | new.isWritable() == isWritable
     */
    @Raw
    public void setWritable(boolean isWritable) {
        this.isWritable = isWritable;
    }

    /* *********************************************************
     * type
     * *********************************************************/
    private final Type type;

    /**
     * Return the type of this file.
     */
    public Type getType() {
        return type;
    }

    /**
     * Return the extension of this file.
     */
    private String getExtension() {
        return type.getExtension();
    }

    /* *********************************************************
     * parentDirectory - defensive programming
     * *********************************************************/

    @Override
    public void setParentDirectory(Directory parentDirectory) {
        if (parentDirectory == null) {
            throw new IllegalArgumentException("Parent directory of File may not be null.");
        }
        super.setParentDirectory(parentDirectory);
    }


    /* *********************************************************
     * bijkomende methodes
     * *********************************************************/
    @Override
    public int getTotalDiskUsage() {
        return getSize();
    }

    @Override
    public String getAbsolutePath() {
        if (this.getParentDirectory() == null) {
            return "/" + this.getName() + this.getExtension();
        } else {
            Directory dir = this.getParentDirectory();
            return dir.getAbsolutePath() + "/" + this.getName() + this.getExtension();
        }
    }

    @Override
    public void terminate() {
        if (!isTerminated()&& isWritable()) {
            setTerminated(true);
            this.getParentDirectory().removeFromContents(this);
        }
    }
}


