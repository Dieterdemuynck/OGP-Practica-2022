import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Date;

// VOORLOPIG HEB IK GWN DINGEN GEKOPIEERD EN GEPLAKT HIERIN, NOG NIET AF!

/**
 * A class of Items.
 *
 * -> name
 * -> creationTime
 * -> modificationTime
 * -> directory
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
     * Check whether the given name is a legal name for a item.
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
     *         | isValidName(result)
     */
    @Model
    protected static String getDefaultName() {
        return "new_item";
    }

    public void changeName(String name) throws ItemNotWritableException {
        if (isValidName(name)){
            setName(name);
            setModificationTime();
        }
    }// Moet overscheven worden voor bestand en map -> writable moet erin zitten


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
     *         | result == (date == null) ||
     *         | ( (date.getTime() >= getCreationTime().getTime()) &&
     *         |   (date.getTime() <= System.currentTimeMillis())     )
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
     * @throws IllegalArgumentException if the
     * @param  parentDirectory  The directory in to which the link will be moved or in which it will be created.
     */
    public void setParentDirectory(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
        if (parentDirectory != null) {
            parentDirectory.addToContents(this);
        }
    }


    /* *********************************************************
     * bijkomende methodes
     * *********************************************************/
    /*
    TODO
    Moet er nog bij komen:
    - public void move(...)
    - public String getRoot()
    - public boolean isDirectOrIndirectChildOf(...)
    - public int getTotalDiskUsage()
    - public String getAbsolutePath()
    - public void delete()
    - public void deleteRecursive()
     */

    public void move(Directory dir){
        Directory oldDirectory = parentDirectory;

        oldDirectory.removeFromContents(this);
        dir.addToContents(this);
        this.parentDirectory = dir;
    }

    public Directory getRoot(){
        return getRoot(this.getParentDirectory());

    }

    private Directory getRoot(Item item){
        if (item.parentDirectory == null){
            return (Directory) item;
        }
        else {
            return getRoot(item.getParentDirectory());
        }
    }

    public boolean isDirectOrIndirectChildOf(Directory directory){
        //TODO heb ik deze methode juist verstaan?
        if (directory.hasAsItem(this)){
            return true;
        }
        else if (directory.getParentDirectory() != null){
            directory = directory.getParentDirectory();
            return isDirectOrIndirectChildOf(directory);
        }
        else {
            return false;
        }
    }

    public int getTotalDiskUsage(){
        return 0;
    }


}
