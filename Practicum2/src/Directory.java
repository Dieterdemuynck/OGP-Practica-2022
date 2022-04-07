import be.kuleuven.cs.som.annotate.*;

public class Directory extends Item {

    /* *********************************************************
     * Constructors
     * *********************************************************/

    /**
     * Initialize a new directory with given name, parent directory and writability
     *
     * @param name The name of the new directory.
     * @effect The name of the directory is set to the given name.
     * If the given name is not valid, a default name is set.
     * | setName(name)
     * @post The new creation time of this directory is initialized to some time during
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
    public Directory(Directory dir, String name, boolean writable) {
        super(name);
        setParentDirectory(dir);
        setWritable(writable);
    }

    public Directory(String name, boolean writable) {
        this(null, name, writable);
    }

    public Directory(String name) {
        this(null, name, true);
    }

    /* *********************************************************
     * name - total programming
     * *********************************************************/

    /**
     * Check whether the given name is a legal name for a directory.
     *
     * @param  	name
     *			The name to be checked
     * @return	True if the given string is effective, not
     * 			empty and consisting only of letters, digits,
     * 			hyphens and underscores; false otherwise.
     * 			| result ==
     * 			|	(name != null) && name.matches("[a-zA-Z_0-9-]+")
     */
    public static boolean isValidName(String name) {  // Static method -> not inherited. (darn.)
        return (name != null && name.matches("[a-zA-Z_0-9-]+"));
    }

    /**
     * Set the name of this directory to the given name.
     *
     * @param   name
     * 			The new name for this directory.
     * @post    If the given name is valid, the name of
     *          this directory is set to the given name,
     *          otherwise the name of the file is set to the default, a valid name.
     *          | if (isValidName(name))
     *          |      then new.getName().equals(name)
     *          |      else new.getName().equals(getDefaultName())
     */
    @Override @Raw @Model
    protected void setName(String name) {
        if (isValidName(name)) {  // Need to override setName due to different checker (isValidName). Is this bad?
            this.name = name;
        } else {
            this.name = getDefaultName();
        }
    }

    /**
     * Return the name for a new directory which is to be used when the
     * given name is not valid.
     *
     * @return   A valid directory name.
     *         | isValidName(result)
     */
    @Model
    protected static String getDefaultName() {
        return "new_directory";
    }

    /**
     * Changes the name of the directory to the given name, or the default name if name is not a valid directory name.
     *
     * @param  name  The string representing the new name.
     * @throws ItemNotWritableException if the directory is not writable, the name cannot be changed and this will be thrown.
     */
    @Override
    public void changeName(String name) throws ItemNotWritableException {
        if (!isWritable()) {
            throw new ItemNotWritableException(this);
        }
        if (isValidName(name)){
            setName(name);
            setModificationTime();
        }
    }

    /* *********************************************************
     * writability - defensive programming
     * *********************************************************/
    // TODO: properly implement (check if methods obey requests, such as changing lastModificationTime)
    private boolean isWritable = true;

    public boolean isWritable() {
        return isWritable;
    }

    public void setWritable(boolean writable) {
        isWritable = writable;
    }
}
