import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Date;

// VOORLOPIG HEB IK GWN DINGEN GEKOPIEERD EN GEPLAKT HIERIN, NOG NIET AF!

/**
 * A class of Items.
 *
 * @author  Mark Dreesen
 * @author  Tommy Messelis
 * @author  Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 4
 */
public abstract class Item {

    /**********************************************************
     * Constructors
     **********************************************************/


    /**
     * Initialize a new file with given name, size and writability.
     *
     * @param  	name
     *         	The name of the new file.
     * @effect  The name of the file is set to the given name.
     * 			If the given name is not valid, a default name is set.
     *          | setName(name)
     * @post    The new creation time of this file is initialized to some time during
     *          constructor execution.
     *          | (new.getCreationTime().getTime() >= System.currentTimeMillis()) &&
     *          | (new.getCreationTime().getTime() <= (new System).currentTimeMillis())
     * @post    The new file has no time of last modification.
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

    /**********************************************************
     * name - total programming
     **********************************************************/

    /**
     * Variable referencing the name of this file.
     * @note		See Coding Rule 32, for information on the initialization of fields.
     */
    protected String name = null;

    /**
     * Return the name of this file.
     * @note		See Coding Rule 19 for the Basic annotation.
     */
    @Raw
    @Basic
    public String getName() {
        return name;
    }

    /**
     * Check whether the given name is a legal name for a file.
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
     * Set the name of this file to the given name.
     *
     * @param   name
     * 			The new name for this file.
     * @post    If the given name is valid, the name of
     *          this file is set to the given name,
     *          otherwise the name of the file is set to a valid name (the default).
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
     * Return the name for a new file which is to be used when the
     * given name is not valid.
     *
     * @return   A valid file name.
     *         | isValidName(result)
     */
    @Model
    protected static String getDefaultName() {
        return "new_file";
    }

    public void changeName(String name) throws FileNotWritableException {
        if (isValidName(name)){
            setName(name);
            setModificationTime();
        }
    }// Moet overscheven worden voor bestand en map -> writable moet erin zitten


    /**********************************************************
     * creationTime
     **********************************************************/

    /**
     * Variable referencing the time of creation.
     */
    protected final Date creationTime = new Date();

    /**
     * Return the time at which this file was created.
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



    /**********************************************************
     * modificationTime
     **********************************************************/

    /**
     * Variable referencing the time of the last modification,
     * possibly null.
     */
    protected Date modificationTime = null;

    /**
     * Return the time at which this file was last modified, that is
     * at which the name or size was last changed. If this file has
     * not yet been modified after construction, null is returned.
     */
    @Raw @Basic
    public Date getModificationTime() {
        return modificationTime;
    }

    /**
     * Check whether this file can have the given date as modification time.
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
     * Set the modification time of this file to the current time.
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
     * Return whether this file and the given other file have an
     * overlapping use period.
     *
     * @param 	other
     *        	The other file to compare with.
     * @return 	False if the other file is not effective
     * 			False if the prime object does not have a modification time
     * 			False if the other file is effective, but does not have a modification time
     * 			otherwise, true if and only if the open time intervals of this file and
     * 			the other file overlap
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
    public boolean hasOverlappingUsePeriod(File other) {
        if (other == null) return false;
        if(getModificationTime() == null || other.getModificationTime() == null) return false;
        return ! (getCreationTime().before(other.getCreationTime()) &&
                getModificationTime().before(other.getCreationTime()) ) &&
                ! (other.getCreationTime().before(getCreationTime()) &&
                        other.getModificationTime().before(getCreationTime()) );
    }

    /**********************************************************
     * directory
     **********************************************************/
    //foutmelding omdat klasse Directory nog niet bestaat, laat dus nog efkes in commentaar tot die klasse bestaat...
    //protected Directory directory;


    /*
    Moet er nog bij komen:
    - public void move(...)
    - public String getRoot()
    - public boolean isDirectOrIndirectChildOf(...)
    - public int getTotalDiskUsage()
    - public String getAbsolutePath()
    - public void delete()
    - public void deleteRecursive()
     */
}
