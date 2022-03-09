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

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @return
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     *
     * @return
     */
    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    /**
     *
     * @return
     */
    public boolean isWritable() {
        return isWritable;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        if (canHaveAsName(name)) {
            this.name = name;
        } else {
            this.name = "default";
        }
    }

    /**
     *
     * @param size
     */
    private void setSize(int size) {
        assert canHaveAsSize(size);
        this.size = size;
    }

    /**
     *
     * @param writable
     */
    public void setWritable(boolean writable){
        this.isWritable = writable;
    }

    /**
     * Updates lastModificationTime to the current date. If the creation time is somehow after the modification time,
     * no update is done.
     */
    private void updateLastModificationTime() {
        Date currentDate = new Date();  // automatically equals the current date
        if (canHaveAsLastModificationTime(currentDate)) {
            // Only if the creation time is indeed before the current date, the lastModificationDate must be updated.
            lastModificationTime = currentDate;
        }
    }

    /**
     * Inspector which checks whether a given name is a valid name for a file.
     *
     * @param  name  A string representing a possible file name, whether valid or not
     * @return true if name contains at least one character and all characters are letters, digits, periods, dashes or
     *         underscores. Returns false otherwise.
     */
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

    /**
     * Inspector which checks whether a given file size is a valid file size.
     *
     * @param  size  A possible file size, whether valid or not
     * @return true if size is greater than or equal to zero, and smaller than or equal to the maximum file size.
     *         false otherwise.
     */
    public boolean canHaveAsSize(int size) {
        return size >= 0 & size <= MAX_SIZE;  // When MAX_SIZE == Integer.MAX_VALUE, the 2nd condition is always true
    }

    /**
     * Inspector which checks whether a given possible last modification time is valid.
     *
     * @param  lastModificationTime A date which represents a potential last time a file was modified.
     * @return true if lastModificationTime is strictly after the file's creation time. false otherwise.
     */
    public boolean canHaveAsLastModificationTime(Date lastModificationTime) {
        return getCreationTime().before(lastModificationTime);
    }

    /**
     * Inspector which checks whether the current file has been modified.
     * @return true if the file has a date saved of the last time it was modified.
     */
    public boolean hasLastModificationTime() {
        return lastModificationTime != null;
    }

    /**
     * Inspector which checks whether the file size limit, or maximum file size, is a valid or sensible limit.
     * @param  size  A potential limit for the file size.
     * @return true if size is greater than or equal to zero.
     */
    public static boolean isValidMaxSize(int size) {
        return size >= 0;
    }

    /**
     * Enlarges the size of the given file by the given amount of bytes.
     *
     * @pre    the size is larger than or equal to 0.
     *       | size >= 0
     * @pre    The size cannot be larger than what would exceed the maximum file size, after enlargement.
     *       | size <= this.getMaxSize() - this.getSize()
     * @post   the file size is enlarged by the correct amount of data.
     *       | new.getSize() == this.getSize() + size;
     * @throws WritabilityViolationException
     *         The destination file is not writable
     *       | ! this.isWritable()
     * @param  size  the amount of bytes by which we enlarge the size of the file.
     */
    @Override
    public void enlarge(int size) throws WritabilityViolationException {
        if (!isWritable()) {
            // An attempt is made at writing to a non-writable file: throw an exception immediately.
            throw new WritabilityViolationException();
        }
        setSize(getSize() + size);     // uses nominal programming: no check needed, though assertions are used.
        updateLastModificationTime();  // uses total programming: no check needed since exceptions cannot happen.
    }

    /**
     * Shortens the size of the file by the given amount of bytes.
     *
     * @pre    the size is larger than or equal to 0.
     *       | size >= 0
     * @pre    the size is less than or equal to the current size of the file.
     *       | size <= this.getSize()
     * @post   the file size is shortened by the given amount of data.
     *       | new.getSize() == this.getSize() - size;
     * @throws WritabilityViolationException
     *         The destination file is not writable
     *       | ! this.isWritable()
     * @param  size  the amount of bytes by which we shorten the size of the file
     */
    @Override
    public void shorten(int size) throws WritabilityViolationException {
        if (!isWritable()) {
            // An attempt is made at writing to a non-writable file: throw an exception immediately.
            throw new WritabilityViolationException();
        }
        setSize(getSize() - size);     // uses nominal programming: no check needed, though assertions are used.
        updateLastModificationTime();  // uses total programming: no check needed since exceptions cannot happen.
    }

    /**
     * Indicates whether the use period of the file overlaps with that of the given file. An unmodified file does
     * not overlap with any file.
     *
     * @param  other  the other file
     * @return True if the use period overlaps; False if it doesn't overlap or one of the files was never modified.
     *       | result ==
     *       |    ( (hasLastModificationTime)
     *       |   && (other.hasLastModificationTime)
     *       |   && ( (creationTime <= other.creationTime <= lastModificationTime
     *       |        || other.creationTime <= creationTime <= other.lastModificationTime) ) )
     */
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
