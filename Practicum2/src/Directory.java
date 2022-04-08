import be.kuleuven.cs.som.annotate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A class of directories.
 *
 * @invar	Each item must have a valid name, existing out of: letters (upper- or lowercase), hyphens, underscores
 *          and/or digits.
 * 	        | isValidName(getName())
 * @invar   Contents may never contain a null element.
 *          | for (Item item: getContent()): item != null
 * @invar   Parent directory may not be child directory (direct or indirect)
 *          |
 * @author  Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 4
 *
 * @note		See Coding Rule 48 for more info on the encapsulation of class invariants.
 */

public class Directory extends Item implements Writability {

    /* *********************************************************
     * Constructors
     * *********************************************************/

    /**
     * Initialize a new directory with given name, parent directory and writability.
     *
     * @param name The name of the new directory.
     *
     * @effect The name of the directory is set to the given name.
     * If the given name is not valid, a default name is set.
     * | setName(name)
     *
     * @post The new creation time of this directory is initialized to some time during
     * constructor execution.
     * | (new.getCreationTime().getTime() >= System.currentTimeMillis()) &&
     * | (new.getCreationTime().getTime() <= (new System).currentTimeMillis())
     * @post The new directory has no time of last modification.
     * | new.getModificationTime() == null
     *
     * @note The constructor is annotated raw because at the start of the execution, not all fields are
     * defaulted to a value that is accepted by the invariants.
     * E.g. the name is defaulted to null, which is not allowed,
     * thus the object is in a raw state upon entry of the constructor.
     */
    @Model @Raw
    public Directory(Directory dir, String name, boolean writable) {
        super(name);
        setParentDirectory(dir);
        setWritable(writable);
    }

    /**
     * Initialize a new directory with given name, parent directory. This new directory is by default
     * writable.
     *
     * @param dir  The directory which will be the new directory's parent directory.
     * @param name The name of the new directory.
     *
     * @effect A new directory object is created, which is by default writable.
     * | Directory(dir, name, true)
     */
    @Raw
    public Directory(Directory dir, String name){
        this(dir,name,true);
    }

    /**
     * Initialize a new directory with given name and writability. This new directory will be a root directory.
     *
     * @param name      The name of the new directory.
     * @param writable  The directory which will be the new directory's parent directory.
     *
     * @effect A new directory object is created, which is by default a root directory.
     * | Directory(null, name, writable)
     */
    @Raw
    public Directory(String name, boolean writable) {
        this(null, name, writable);
    }

    /**
     * Initialize a new directory with given name. This new directory is by default writable, and will be a root
     * directory.
     *
     * @param name The name of the new directory.
     *
     * @effect A new directory object is created, which is by default writable and a root directory.
     * | Directory(null, name, true)
     */
    @Raw
    public Directory(String name) {
        this(null, name, true);
    }

    /* *********************************************************
     * name - total programming
     * ********************************************************/

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
     *          otherwise the name of the directory is set to the default, a valid name.
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

    /**
     * Variable registering whether this directory is writable.
     */
    private boolean isWritable = true;

    /**
     * Check whether this directory is writable.
     */
    @Raw
    @Basic
    public boolean isWritable() {
        return isWritable;
    }

    /**
     * Set the writability of this directory to the given writability.
     *
     * @param isWritable The new writability
     * @post The given writability is registered as the new writability
     * for this directory.
     * | new.isWritable() == isWritable
     */
    @Raw
    public void setWritable(boolean isWritable) {
        this.isWritable = isWritable;
    }

    /* *********************************************************
     * contents - defensive programming
     * *********************************************************/
    private List<Item> contents = new ArrayList();
    //TODO ALS NAAM VERANDERD VAN ITEM MOET ARRAYLIST AANGEPAST WORDEN. -> verwijderen en terug toevoegen?
    // add is al juist gemaakt voor item
    // remove zou normaal deftig werken...

    // TODO: alles implementeren, ieder element in map moet in deze lijst te komen staan...

    private int findIndexForInContents(Item item, int start, int einde){
        // Complexity of log(n)
        if (start < einde){
            int mid = (start + einde)/2;
            if (compareStrings(item.getName(),contents.get(mid).getName()) == 1){
                return findIndexForInContents(item, start,mid-1);
            }
            else if (compareStrings(item.getName(),contents.get(mid).getName()) == 2){
                return findIndexForInContents(item,mid+1,einde);
            }
            else { //item heeft dezelfde naam
                throw new IllegalDirectoryContentExeption(this,item);
            }
        }
        else {
            if (compareStrings(item.getName(), contents.get(start).getName()) == 1){
                return start;
            }
            else  if (compareStrings(item.getName(),contents.get(start).getName()) == 2){
                return start + 1;
            }
            else { // Item has same name
                throw new IllegalDirectoryContentExeption(this,item);
            }
        }
    }

    /**
     * TODO: specification
     * @return
     */
    @Basic
    public List<Item> getContents() {
        return contents;
    }

    /* DELETED: setContents(List<Item>)
     * Needless setter, could seriously mess up the code too. Use Directory.addToContents(Item item) instead.
     */

    /**
     * Adds an item to the contents of the directory. This counts as a modification of the directory.
     *
     * @effect  The directory's modification time will be set to some time during execution.
     *          | setModificationTime()
     * @param   item
     *          The item which will be added to the directory.
     */
    public void addToContents(Item item){
        if (!isWritable()) {
            // Directory must be writable.
            throw new ItemNotWritableException(this);
        }
        if (item == null) {
            // Directory content cannot be null.
            throw new IllegalArgumentException("Directory content may not be null.");
        }
        if (item instanceof Directory && this.isDirectOrIndirectChildOf((Directory)item) || item == this) {
            // Directory paths may not have any loops.
            throw new IllegalDirectoryContentExeption(this, item);
        }
        if (contents.size()>0){
            int place = findIndexForInContents(item,0,getContents().size()-1);
            getContents().add(place, item); //Moet gesorteerd worden op naam, zou moeten juist zijn zo
        }
        else {
            getContents().add(item);
        }
        setModificationTime();
    }

    /**
     * Removes an item from the contents of the directory. This counts as a modification of the directory.
     *
     * @effect  The directory's modification time will be set to some time during execution.
     *          | setModificationTime()
     * @param   item
     *          The item which will be removed from the directory.
     */
    @Model
    public void removeFromContents(Item item){
        if (!isWritable()) {
            throw new ItemNotWritableException(this);
        }
        if (item == null){
            throw new IllegalArgumentException("Cannot attempt to remove null Item from Directory.");
        }
        int place = getIndexOf(item);
        getContents().remove(place);
        setModificationTime();
    }

    // TODO: specification
    public int getNbItems(){
        return getContents().size();
    }

    // TODO: specification
    public Item getItemAt(int place){
        return getContents().get(place-1); //start vanaf 1
    }

    // TODO: specification
    public Item getItem(String itemName) {
        //Index ook +1 doen zoals in getItemAt(.) ? Nu niet het geval
        //Normaal complexiteit O(log n)
        return contents.get(getItemIndex(itemName,0, getContents().size()));
    }

    private int getItemIndex(String itemName,int start, int einde){
        if (start != einde){
            int mid = (start + einde)/2;
            if (compareStrings(itemName,getContents().get(mid).getName()) == 1){
                return getItemIndex(itemName, start,mid-1);
            }
            else if (compareStrings(itemName,getContents().get(mid).getName()) == 2){
                return getItemIndex(itemName,mid+1,einde);
            }
            else {
                return mid;
            }
        }
        else {
            if (compareStrings(itemName, getContents().get(start).getName()) == 0){
                return start;
            }
            else { // Naam komt niet voor
                return -1; //TODO ZIT ER NIET IN, moet defensief -> exception?
            }
        }
    }

    /**
     * Checks whether there is an item with the relevant name in this directory.
     *
     * @param itemName is the name of the relevent item.
     * @return whether there is an item with the given name in this directory, ignoring upper or lower case letters.
     */
    public boolean containsDiskItemWithName(String itemName){
        itemName = itemName.toLowerCase();
        for (Item item: getContents()){
            String name = item.getName().toLowerCase();
            if (name.equals(itemName)){
                return true;
            }
        }
        return false;
    }


    public int getIndexOf(Item item) {
        //Index ook +1 doen zoals in getItemAt(.) ? Nu niet het geval
        return getIndexOf(item,  getContents().size());
    }

    private int getIndexOf(Item item, int einde){  // TODO: Only used once, redundant separation? move code to public version
        int place = getItemIndex(item.getName(),0,einde);
        if (item == getContents().get(place)){
            return place;
        }
        else {
            return -1; //TODO ZIT ER NIET IN, moet defensief -> exception?
        }
    }

    /**
     * Looks for a specific item in this directory.
     *
     * @param item is the item we are looking for.
     * @return whether the item is in this directory.
     */
    public boolean hasAsItem(Item item){
        boolean contains = false;
        for (Item contentItem: getContents()){
            if (item == contentItem){
                contains = true;
            }
        }
        return contains;
    }


    /* *********************************************************
     * parentDirectory
     * *********************************************************/

    // DELETED: needless override of setParentDirectory(Directory parentDirectory)

    /* *********************************************************
     * Additional Methods
     * *********************************************************/

    /**
     * Make a directory a root directory.
     *
     * @post    the referenced Directory will not have a parent directory.
     */
    public void makeRoot(){
        setParentDirectory(null);
    }

    /**
     * gives you the total disk usage.
     *
     * @return the amount of memory this directory needs.
     */
    @Override
    public int getTotalDiskUsage(){
        int diskUsage = 0;
        for (Item item: getContents()){
            diskUsage += item.getTotalDiskUsage();
        }
        return diskUsage;
    }

    /* *********************************************************
     * Destructor - defensive programming
     * *********************************************************/

    /**
     * This terminates the referenced directory.
     *
     * @pre     The directory must not be terminated already.
     * @post    The directory will be terminated.
     */
    @Override
    public void terminate() {
        if (!isTerminated() && isWritable() && contents.isEmpty()) {
            setTerminated(true);
            this.getParentDirectory().removeFromContents(this);
        }
    }

    // beter private zetten zeker?

    /**
     * Tells whether the directory is deletable or not.
     *
     * @pre    The directory and it's items must be writable and the items must be deletable.
     * @return The result will tell whether the directory can be deleted, depending on the writability of the directory and of the items in the directory.
     */
    public boolean canBeDeleted() {
        // A directory cannot be deleted if it is not writable
        if (!isWritable()) {
            return false;
        }

        for (Item item: getContents()) {
            if (item instanceof Directory && !((Directory) item).canBeDeleted()) {
                // There is a directory which cannot be deleted
                return false;
            } else if (item instanceof  Writability && !((Writability) item).isWritable()) {
                // There is content of the directory which is not writable
                return false;
                /* NOTE: While the only other object which has writability is File, current implementation allows for
                 * further expansion of subclasses which implement writability.
                 */
            }
        }

        return true;
    }

    // TODO: any pre or post conditions? Anything?
    // Note: we do not use "@effect" doctag or "@Model" annotation as the precondition from deleteRecursivelyRaw() does not transfer.
    /**
     * Safely deletes the directory and all its contents, by first checking if all content, including content
     * within child directories, is writable.
     */
    @Override
    public void deleteRecursive(){
        // TODO: should this throw an error upon finding a non-writable file? FIX: rename "canBeDeleted()" to "checkDirectoryAndContentWritability()"
        // The implementation of the renamed function should be relatively obvious.
        if (canBeDeleted()){
            deleteRecursiveRaw();
        }
    }

    /**
     * Deletes the directory and its contents, assuming the directory and its contents are all writable and can
     * safely be deleted.
     *
     * @pre  The directory and its contents should all be writable, and each directory within this directory
     *       should also be able to be deleted.
     *     | this.canBeDeleted()
     */
    @Raw //@Model
    public void deleteRecursiveRaw() {
        Item lastItem = null;
        while (getContents().size() > 0){
            // As long as the directory still has contents, delete the last item.
            lastItem = getContents().get(getContents().size() - 1);
            if (lastItem instanceof Directory) {
                ((Directory)lastItem).deleteRecursiveRaw();
            } else {
                lastItem.deleteRecursive();
            }
        }

        // All items have been deleted, delete the directory itself.
        terminate();
    }
}
