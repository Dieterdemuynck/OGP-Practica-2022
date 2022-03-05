package com.ogppractica;

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
public interface FileInterface {

    /**
     * Enlarges the size of the given file by the given amount of bytes.
     *
     * @pre    the size is larger than or equal to 0.
     *       | size >= 0
     * @pre    The size cannot be larger than what would exceed the maximum file size, after enlargement.
     *       | size <= this.getMaxSize() - this.getSize()
     * @post   the file size is enlarged by the correct amount of data.
     *       | TODO: add formal specification
     * @param  size  the amount of bytes by which we enlarge the size of the file.
     */
    public void enlarge(int size);

    /**
     * Shortens the size of the file by the given amount of bytes.
     *
     * @pre    the size is larger than or equal to 0.
     *       | size >= 0
     * @pre    the size is less than or equal to the current size of the file.
     *       | size <= this.getSize()
     * @post   the file size is shortened by the given amount of data.
     *       | new.getSize() == this.getSize() - size
     * @param  size  the amount of bytes by which we shorten the size of the file
     */
    public void shorten(int size);

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
    public boolean hasOverlappingUsePeriod(File other);

}
