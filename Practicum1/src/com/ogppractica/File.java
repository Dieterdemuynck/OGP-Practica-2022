package com.ogppractica;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class of files involving file name, size, date of creation, date of the latest change or modification, and whether
 * the file is writable or not. A file is always readable to anyone.
 *
 * @invar   file name has at least one character, and all characters must be letters (upper- or lowercase), numbers,
 *          periods, dashes or underscores.
 *        | canHaveAsName(getName())
 * @invar   file size must be smaller than or equal to the maximum size any file is allowed to have.
 *        | canHaveAsSize(getSize())
 * @invar   time of last modification must be later than time of creation.
 *        | canHaveAsLastModificationTime(java.util.Date)
 * @invar   maximum file size must be positive
 *        | isValidMaxSize(int)
 * @author  Team 2: Ine Malfait, Hannes Ingelaere, Dieter Demuynck
 */
public class File implements FileInterface {

    // Properties
    /**
     * Variable registering the current name of this file.
     */
    private String name;
    /**
     * Variable registering the current size of this file.
     */
    private int size;
    /**
     * Variable registering the maximum size of all files. The size of a file can never be greater than this.
     */
    public static final int MAX_SIZE = Integer.MAX_VALUE;
    /**
     * Variable registering the creation time of this file. This time can not be changed for this file.
     */
    private final Date creationTime;
    /**
     * Variable registering the last time the file was modified.
     */
    private Date lastModificationTime;
    /**
     * Variable registering if the file is writable or not.
     */
    private boolean isWritable;

    // Constructors

    /**
     * Initialize this new file with given name, size and writablily.
     *
     * @param   name
     *          The name of this file.
     * @param   size
     *          The size of this file
     * @param   writable
     *          The writablity of this file. If true then the file can be changed, else the file cannot be changed.
     * @post    If the name has characters different from upper and lower case letters, numbers, periods (.), hyphens (-) and underscores (_), the name is set as "default"
     * @pre     The given size must be a valid size for a file.
     *          |(canHaveAsSize(size))
     * @post    The size of this new file is equal to the given size.
     *          | new.getSize() == size
     * @effect  The size of this new file is set to the given size
     * @post    The new writable state of this new file is equal to the given flag.
     *          | new.isWritable() == writable
     */
    public File(String name, int size, boolean writable) {
        setName(name);
        if (canHaveAsSize(size)) {
            this.size = size;
        }
        this.isWritable = writable;
        this.creationTime = new Date();
    }

    /**
     *
     * @param   name
     *          The name of this file
     * @post    If the name has characters different from upper and lower case letters, numbers, periods (.), hyphens (-) and underscores (_), the name is set as "default"
     * @effect  This new file is initialized with the given name as its name, zero as its size and true as its initial writable state.
     *          | this(name,0,true)
     */
    public File(String name) {
        this(name, 0, true);
    }


    // Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (canHaveAsName(name)) {
            this.name = name;
        } else {
            this.name = "default";
        }
    }

    public int getSize() {
        return size;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    public boolean isWritable() {
        return isWritable;
    }

    private void setSize(int size) {
        // Assertions added to check for invalid inputs. (Optional)
        // Perhaps too many? Simply unnecessary? I do not know...
        assert size <= getSize();
        assert size <= MAX_SIZE - getSize();
        assert size >= 0;
        this.size = size;
    }

    // TODO: Implement steady-state setters for: name, isWritable


    /**
     * Updates lastModificationTime to the current date.
     */
    private void updateLastModificationTime() {
        Date currentDate = new Date();  // automatically equals the current date
        if (canHaveAsLastModificationTime(currentDate)) {
            // Only if the creation time is indeed before the current date, the lastModificationDate must be updated.
            lastModificationTime = currentDate;
        }
    }

    public boolean canHaveAsName(String name) {
        boolean possibleName = true;
        ArrayList<Character> possibleCharacters = new ArrayList<>();
        possibleCharacters.add('.');
        possibleCharacters.add('-');
        possibleCharacters.add('_');
        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);
            if (!(Character.isLowerCase(character) |Character.isUpperCase(character)| Character.isDigit(character) | possibleCharacters.contains(character))) {
                possibleName = false;
            }
        }
        return possibleName;
    }

    public boolean canHaveAsSize(int size) {
        return size >= 0 & size <= MAX_SIZE;
    }

    // TODO: Implement inspector canHaveAsLastModificationTime(java.util.Date)
    public boolean canHaveAsLastModificationTime(Date lastModificationTime) {
        return getCreationTime().before(lastModificationTime);
    }

    public boolean hasLastModificationTime() {
        return lastModificationTime != null;
    }

    //LIJKT MIJ NUTTELOOS - Ine
    // TODO: Implement inspector isValidMaxSize(int)
    public static boolean isValidMaxSize(int size) {
        return false;
    }

    // vergeet ook niet dat lastModificationTime moet aangepast worden :) - Ine
    @Override
    public void enlarge(int size) throws WritabilityViolationException {

    }

    @Override
    public void shorten(int size) throws WritabilityViolationException {
        if (!isWritable()) {
            // An attempt is made at writing to a non-writable file: throw an exception immediately.
            throw new WritabilityViolationException();
        }
        setSize(getSize() - size);
        updateLastModificationTime();  // uses total programming: no check needed in case exceptions happen.
    }

    @Override
    public boolean hasOverlappingUsePeriod(File other) {
        if (this.hasLastModificationTime() && other.hasLastModificationTime()) {
            if (this.getCreationTime().before(other.getCreationTime()) | this.getCreationTime().equals(other.getCreationTime())) {
                return this.getLastModificationTime().after(other.getCreationTime()) | this.getLastModificationTime().equals(other.getCreationTime());
            } else {
                return other.getLastModificationTime().after(this.getCreationTime()) | this.getLastModificationTime().equals(other.getCreationTime());
            }
        } else {
            return false;
        }
    }
}
