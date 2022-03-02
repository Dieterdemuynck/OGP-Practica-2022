package com.ogppractica;

public interface FileInterface {
    /**
     * Enlarges the size of the given file by the given amount of bytes.
     * @pre     the size is larger than or equal to 0.
     *       |  size >= 0
     * @pre     The size cannot be larger than what would exceed the maximum file size, after enlargement.
     *       |  size <= this.getMaxSize() - this.getSize()
     * @post    the file size is enlarged by the correct amount of data.
     * @param size the amount of bytes by which we enlarge the size of the file.
     */

    public void enlarge(int size);

    /**
     * Shortens the size of the file by the given amount of bytes.
     *
     * @pre    the size is larger than or equal to 0.
     *       | size >= 0
     * @pre    the size is less than or equal to the current size of the file
     *       | size <= this.getSize()
     * @param  size  the amount of bytes by which we shorten the size of the file
     */
    public void shorten(int size);

    /**
     * Indicates whether the usage period of the file overlaps with that of the supplied file. An unmodified file does not overlap with any file.
     *
     * @param other the other file
     * @return True if the usage period overlaps; False if it doesn't overlap or that one of the files is never modified.
     */
    public boolean hasOverlappingUsePeriod(File other);

}
