import be.kuleuven.cs.som.annotate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A class of directories.
 *
 * -> name
 * -> creationTime
 * -> modificationTime
 * -> directory
 * -> contents
 * -> writable
 *
 * @author  Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 4
 *
 * @note		See Coding Rule 48 for more info on the encapsulation of class invariants.
 */

public class Directory extends Item {

    /* *********************************************************
     * Constructors
     * *********************************************************/

    /**
     * Initialize a new directory with given name, parent directory and writability
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
    public Directory(Directory dir, String name, boolean writable) {
        super(name);
        setParentDirectory(dir);
        setWritable(writable);
    }

    public Directory(Directory dir, String name){
        this(dir,name,true);
    }

    public Directory(String name, boolean writable) {
        this(null, name, writable);
    }

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

    private int findIndexForInContents(Item item,int start, int einde){
        // complexiteit is lg(n)
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
            else { //item heeft dezelfde naam
                throw new IllegalDirectoryContentExeption(this,item);
            }
        }
    }

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
    private int compareStrings(String string1, String string2){
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

    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }

    public void addToContents(Item item){
        if (!isWritable()) {
            throw new ItemNotWritableException(this);
        }
        if (item == null){
            throw new IllegalDirectoryContentExeption(this, null);
        }
        setModificationTime();
        if (contents.size()>0){
            int place = findIndexForInContents(item,0,getContents().size()-1);
            getContents().add(place, item); //Moet gesorteerd worden op naam, zou moeten juist zijn zo
        }
        else {
            getContents().add(item);
        }
    }

    public void removeFromContents(Item item){
        if (!isWritable()) {
            throw new ItemNotWritableException(this);
        }
        if (item == null){
            throw new IllegalDirectoryContentExeption(this, null);
        }
        int place = getIndexOf(item);
        contents.remove(place);
    }

    public int getNbItems(){
        return getContents().size();
    }

    public Item getItemAt(int place){
        return getContents().get(place-1); //start vanaf 1
    }

    public int getItem(String itemName) {
        //Index ook +1 doen zoals in getItemAt(.) ? Nu niet het geval
        //Normaal complexiteit O(log n)
        return getItem(itemName,0, getContents().size());
    }

    private int getItem(String itemName,int start, int einde){
        if (start != einde){
            int mid = (start + einde)/2;
            if (compareStrings(itemName,getContents().get(mid).getName()) == 1){
                return getItem(itemName, start,mid-1);
            }
            else if (compareStrings(itemName,getContents().get(mid).getName()) == 2){
                return getItem(itemName,mid+1,einde);
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

    public boolean containsDiskItemWithName(String itemName){
        itemName = itemName.toLowerCase();
        boolean contains = false;
        for (Item item: getContents()){
            String name = item.getName().toLowerCase();
            if (name.equals(itemName)){
                contains = true;
            }
        }
        return contains;
    }


    public int getIndexOf(Item item) {
        //Index ook +1 doen zoals in getItemAt(.) ? Nu niet het geval
        return getIndexOf(item,  getContents().size());
    }

    private int getIndexOf(Item item, int einde){
        //
        int place = getItem(item.getName(),0,einde);
        if (item == getContents().get(place)){
            return place;
        }
        else {
            return -1; //TODO ZIT ER NIET IN, moet defensief -> exception?
        }
    }

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
     * bijkomende methodes
     * *********************************************************/
    public void makeRoot(){
        setParentDirectory(null);
    }

    @Override
    public int getTotalDiskUsage(){
        int diskUsage = 0;
        for (Item item: contents){
            diskUsage += item.getTotalDiskUsage();
        }
        return diskUsage;
    }

    /* *********************************************************
     * Destructor - defensive programming
     * *********************************************************/

    @Override
    public void terminate() {
        if (!isTerminated() && isWritable() && contents.isEmpty()) {
            setTerminated(true);
            this.getParentDirectory().removeFromContents(this);
        }
    }

    private boolean canBeDeletedRecursively(){
        //TODO kan iemand dit???
        // idee we schrijven in link een methode isWritable die altijd true geeft -> dan kan je dit laten werken hahaha
        boolean canBeTerminated = true;
        for (Item item: contents){
            Link link = (Link) item; // da mag nie zeker :(
            if(link.isTerminated()){
                canBeTerminated = false;
            }
            File file = (File) item; // da gaat nie zeker :(
            if (file.isTerminated() && !file.isWritable()){
                canBeTerminated = false;
            }
            Directory directory = (Directory) item; // da gaat nie zeker :(
            if (directory.isTerminated() && !directory.isWritable()){
                canBeTerminated = directory.canBeDeletedRecursively();
            }
        }
        return canBeTerminated;
    }

    @Override
    public void deleteRecursive(){
        if (canBeDeletedRecursively()){
            for (Item item: contents){
                item.setTerminated(true);
                Directory directory = (Directory) item;
                directory.deleteRecursive();
            }
            contents.clear();
            terminate();
        }
    }
}
