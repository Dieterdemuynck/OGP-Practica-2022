import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Date;

// VOORLOPIG HEB IK GWN DINGEN GEKOPIEERD EN GEPLAKT HIERIN, NOG NIET AF!

/**
 * A class of Items.
 *
 * -> directory  // TODO: does this even need any invariants? The parent directory is either a directory or null? That's always the case though...
 *
 * @invar	Each item must have a valid name, existing out of: letters (upper- or lowercase), hyphens, periods,
 *          underscores and/or digits.
 * 	        | isValidName(getName())
 * @invar   Each item must have a valid creation time.
 *          | isValidCreationTime(getCreationTime())
 * @invar   Each item must have a valid modification time.
 *          | canHaveAsModificationTime(getModificationTime())
 *
 * @author  Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 4
 */
public abstract class Item {

    /* *********************************************************
     * Constructors
     * *********************************************************/


    /**
     * Initialize a new item with given name.
     *
     * @param  	name
     *         	The name of the new item.
     * @effect  The name of the item is set to the given name.
     * 			If the given name is not valid, a default name is set.
     *          | setName(name)
     * @post    The new creation time of this item is initialized to some time during
     *          constructor execution.
     *          | (new.getCreationTime().getTime() >= System.currentTimeMillis()) &&
     *          | (new.getCreationTime().getTime() <= (new System).currentTimeMillis())
     * @post    The new item has no time of last modification.
     *          | new.getModificationTime() == null
     * @post    The item will be given a valid name, which is the given name is it is valid, or a default valid name.
     *          | isValidName(name) && new.getName() == name || !isValidName(name) && isValidName(new.getName())
     *
     * @note	The constructor is annotated raw because at the start of the execution, not all fields are
     * 			defaulted to a value that is accepted by the invariants.
     * 			E.g. the name is defaulted to null, which is not allowed,
     * 			thus the object is in a raw state upon entry of the constructor.
     */
    @Raw
    public Item(String name) {
        setName(name);
    }

    /* *********************************************************
     * name - total programming
     * *********************************************************/

    /**
     * Variable referencing the name of this item.
     * @note		See Coding Rule 32, for information on the initialization of fields.
     */
    protected String name = null;

    /**
     * Return the name of this item.
     * @note		See Coding Rule 19 for the Basic annotation.
     */
    @Raw
    @Basic
    public String getName() {
        return name;
    }

    /**
     * Check whether the given name is a legal name for an item.
     *
     * @param  	name
     *			The name to be checked
     * @return	True if the given string is effective, not
     * 			empty and consisting only of letters, digits, dots,
     * 			hyphens and underscores; false otherwise.
     * 			| result ==
     * 			|	(name != null) && name.matches("[a-zA-Z_0-9.-]+")
     */
    public static boolean isValidName(String name) {
        return (name != null && name.matches("[a-zA-Z_0-9.-]+"));
    } // Moet overschreven worden voor map "[a-zA-Z_0-9-]+"

    /**
     * Set the name of this item to the given name.
     *
     * @param   name
     * 			The new name for this item.
     * @post    If the given name is valid, the name of
     *          this item is set to the given name,
     *          otherwise the name of the item is set to a valid name (the default).
     *          | if (isValidName(name))
     *          |      then new.getName().equals(name)
     *          |      else new.getName().equals(getDefaultName())
     */
    @Raw @Model
    protected void setName(String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            this.name = getDefaultName();
        }
    }

    /**
     * Return the name for a new item which is to be used when the
     * given name is not valid.
     *
     * @return   A valid item name.
     *           | isValidName(result)
     */
    @Model
    protected static String getDefaultName() {
        return "new_item";
    }

    public void changeName(String name) throws ItemNotWritableException {
        if (isValidName(name)){
            getParentDirectory().removeFromContents(this);
            setName(name);
            setModificationTime();
            getParentDirectory().addToContents(this);
        }
    }


    /* *********************************************************
     * creationTime
     * *********************************************************/

    /**
     * Variable referencing the time of creation.
     */
    protected final Date creationTime = new Date();

    /**
     * Return the time at which this item was created.
     */
    @Raw @Basic @Immutable
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Check whether the given date is a valid creation time.
     *
     * @param  	date
     *         	The date to check.
     * @return 	True if and only if the given date is effective and not
     * 			in the future.
     *         	| result ==
     *         	| 	(date != null) &&
     *         	| 	(date.getTime() <= System.currentTimeMillis())
     */
    public static boolean isValidCreationTime(Date date) {
        return 	(date!=null) &&
                (date.getTime()<=System.currentTimeMillis());
    }



    /* *********************************************************
     * modificationTime
     * *********************************************************/

    /**
     * Variable referencing the time of the last modification,
     * possibly null.
     */
    protected Date modificationTime = null;

    /**
     * Return the time at which this item was last modified, that is
     * at which the name or size was last changed. If this item has
     * not yet been modified after construction, null is returned.
     */
    @Raw @Basic
    public Date getModificationTime() {
        return modificationTime;
    }

    /**
     * Check whether this item can have the given date as modification time.
     *
     * @param	date
     * 			The date to check.
     * @return 	True if and only if the given date is either not effective
     * 			or if the given date lies between the creation time and the
     * 			current time.
     *          | result == (date == null) ||
     *          | ( (date.getTime() >= getCreationTime().getTime()) &&
     *          |   (date.getTime() <= System.currentTimeMillis())     )
     */
    @Raw
    public boolean canHaveAsModificationTime(Date date) {
        return (date == null) ||
                ( (date.getTime() >= getCreationTime().getTime()) &&
                        (date.getTime() <= System.currentTimeMillis()) );
    }

    /**
     * Set the modification time of this item to the current time.
     *
     * @post   The new modification time is effective.
     *         | new.getModificationTime() != null
     * @post   The new modification time lies between the system
     *         time at the beginning of this method execution and
     *         the system time at the end of method execution.
     *         | (new.getModificationTime().getTime() >=
     *         |                    System.currentTimeMillis()) &&
     *         | (new.getModificationTime().getTime() <=
     *         |                    (new System).currentTimeMillis())
     */
    @Model
    protected void setModificationTime() {
        modificationTime = new Date();
    }

    /**
     * Return whether this item and the given other item have an
     * overlapping use period.
     *
     * @param 	other
     *        	The other item to compare with.
     * @return 	False if the other item is not effective
     * 			False if the prime object does not have a modification time
     * 			False if the other item is effective, but does not have a modification time
     * 			otherwise, true if and only if the open time intervals of this item and
     * 			the other item overlap
     *        	| if (other == null) then result == false else
     *        	| if ((getModificationTime() == null)||
     *        	|       other.getModificationTime() == null)
     *        	|    then result == false
     *        	|    else
     *        	| result ==
     *        	| ! (getCreationTime().before(other.getCreationTime()) &&
     *        	|	 getModificationTime().before(other.getCreationTime()) ) &&
     *        	| ! (other.getCreationTime().before(getCreationTime()) &&
     *        	|	 other.getModificationTime().before(getCreationTime()) )
     */
    public boolean hasOverlappingUsePeriod(Item other) {
        if (other == null) return false;
        if(getModificationTime() == null || other.getModificationTime() == null) return false;
        return ! (getCreationTime().before(other.getCreationTime()) &&
                getModificationTime().before(other.getCreationTime()) ) &&
                ! (other.getCreationTime().before(getCreationTime()) &&
                        other.getModificationTime().before(getCreationTime()) );
    }

    /* *********************************************************
     * parentDirectory - defensive programming
     * *********************************************************/

    /**
     * Variable referencing the parent directory of the item.
     */
    private Directory parentDirectory = null;

    @Basic
    public Directory getParentDirectory() {
        return parentDirectory;
    }

    /**
     * Sets the parent directory of the item to the given directory, if the given directory is not null.
     *
     * @post    the new parentDirectory will be set to the given directory,
     *
     * @throws  IllegalArgumentException if the
     * @param   parentDirectory  The directory in to which the link will be moved or in which it will be created.
     */
    @Basic @Raw
    protected void setParentDirectory(Directory parentDirectory) {
        // TODO: check usages, if content is added to each item.
        this.parentDirectory = parentDirectory;
    }

    /**
     * Moves the current file to the given directory, if the current and target directories are both  writable.
     * To move a directory to null, e.g. to make it a root directory, use Directory.makeRoot() instead.
     *
     * @effect  removes the item from the current parentDirectory and adds it to the targetParentDirectory.
     *          | getParentDirectory().removeFromContents(this)
     *          | parentDirectory.addToContents(this)
     * @throws  IllegalArgumentException
     *          The given new parentDirectory may not be null.
     *          | parentDirectory != null
     * @throws  ItemNotWritableException
     *          The new parentDirectory and current parentDirectory must both be writable.
     *          | parentDirectory.isWritable() && getParentDirectory.isWritable()
     * @param   parentDirectory
     *          The directory in to which the link will be moved or in which it will be created.
     */
    public void move(Directory parentDirectory) throws IllegalArgumentException, ItemNotWritableException {
        if (parentDirectory == null) {
            // move() must move item to a new directory.
            throw new IllegalArgumentException("target parentDirectory may not be null.");
        } else if (!getParentDirectory().isWritable()) {
            // the current parentDirectory must be writable.
            throw new ItemNotWritableException(getParentDirectory());
        } else if (!parentDirectory.isWritable()) {
            // the target parentDirectory must be writable.
            throw new ItemNotWritableException(parentDirectory);
        } else if (this instanceof Directory && parentDirectory.isDirectOrIndirectChildOf((Directory)this)
                || parentDirectory == this) {
            // Directory paths may not contain any loops.
            throw new IllegalDirectoryContentExeption(parentDirectory, this);
        }
        // Remove item from current parentDirectory.
        getParentDirectory().removeFromContents(this);
        // Add item to target parentDirectory.
        parentDirectory.addToContents(this);
        // Adjust item's reference to parentDirectory.
        setParentDirectory(parentDirectory);
    }


    /* *********************************************************
     * bijkomende methodes
     * *********************************************************/

    // TODO: specification
    public Directory getRoot(){
        return getRoot(this.getParentDirectory());

    }

    // TODO: specification
    private Directory getRoot(Item item){
        if (item.parentDirectory == null){
            return (Directory) item;
        }
        else {
            return getRoot(item.getParentDirectory());
        }
    }

    // TODO: specification
    public boolean isDirectOrIndirectChildOf(Directory directory){
        if (directory.getContents().size() == 0){
            // Case 1: contents of given directory are empty ==> item is not a child of given directory.
            return false;
        } else if (directory.hasAsItem(this)) {
            // Case 2: item is a direct child of given directory.
            return true;
        } else {
            // Case 3: item is not a direct, but possibly an indirect child of given directory. Go through each item in its contents to check.
            for (Item item: directory.getContents()) {
                if (item instanceof Directory && isDirectOrIndirectChildOf((Directory)item)) {
                    return true;
                }
            }
            return false;
        }
    }

    // TODO: specification
    // Bit odd that an item, "by default", does not use disk space. Hm.
    public int getTotalDiskUsage(){
        return 0;
    }

    // TODO: specification
    public String getAbsolutePath(){
        if (this.getParentDirectory() ==  null){
            return "/" + this.getName();
        }
        else {
            Directory dir = this.getParentDirectory();
            return dir.getAbsolutePath() + "/" + this.getName();
        }
    }

    /* *********************************************************
     * Destructor - defensive programming
     * *********************************************************/

    // TODO: specification
    private boolean isTerminated = false;

    // TODO: specification
    public boolean isTerminated() {
        return isTerminated;
    }

    // TODO: specification
    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }

    /**
     * Destructs the item.
     */
    public abstract void terminate();

    // TODO: specification? Does it need documentation? Perhaps a simple explanation instead of full documentation with eg. doctags...
    public abstract void deleteRecursive();

    /* *********************************************************
     * Miscellaneous
     * *********************************************************/
    /**
     * Compares two strings and gives their lexicographic ordering based on the ASCII values of
     * the characters. The difference between upper- and lowercase letters is ignored, as all lower-
     * case letters will be set to uppercase during comparison. If the shorter string matches with
     * the beginning of the longer string, the shorter string is considered to be lexicographically
     * before the longer string.
     *
     * @pre    both strings differ from null
     *       | string1 != null && string2 != null;
     *
     * @param  string1  The first string to compare
     * @param  string2  The second string to compare
     * @return  0 if the strings are the same;
     *          1 if string1 lexicographically before string2;
     *          2 if string1 lexicographically after string2.
     *        | if (string1 == string2) {
     *        |     result == 0
     *        | }
     *        | Assume string1 == a1 a2 a3 ... ak && string2 == b1 b2 b3 ... bl
     *        | and i is the smallest index where ai != bi where i <= k && i <= l.
     *        | if (ai < bi) {
     *        |     result == 1;
     *        | } else {
     *        |     result == 2;
     *        | }
     *        | Assume string1 == a1 a2 a3 ... ak && string2 == b1 b2 b3 ... bl
     *        | where k != l && a1 a2 a3 ... a(min(k, l)) == b1 b2 b3 ... b(min(k, l)).
     *        | result == k < l ? 1 : 2
     */
    // Quite some formal specification, init? Is it even correct? It uses less Java and more math-language after all...
    public static int compareStrings(String string1, String string2){
        // Ignoring upper- and lowercase differences by turning both strings to uppercase.
        string1 = string1.toUpperCase();
        string2 = string2.toUpperCase();

        // Compare each character until different character has been found or end of string has been found
        int result = 0;
        for (int i = 0; i < Math.min(string1.length(), string2.length()); i++) {
            if (string1.charAt(i) < string2.charAt(i)) {
                return 1;
            } else if (string1.charAt(i) > string2.charAt(i)) {
                return 2;
            }
        }

        // End of the shortest string reached ==> compare length
        if (string1.length() < string2.length()) {
            return 1;
        } else if (string1.length() > string2.length()) {
            return 2;
        } else {
            return 0;
        }
    }
}
