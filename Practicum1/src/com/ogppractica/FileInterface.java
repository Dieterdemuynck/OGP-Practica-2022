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

    public void shorten(int size);

    public boolean hasOverlappingUsePeriod(File other);

}
