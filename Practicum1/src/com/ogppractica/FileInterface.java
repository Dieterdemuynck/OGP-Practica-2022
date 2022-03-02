package com.ogppractica;

public interface FileInterface {

    public void enlarge(int size);

    public void shorten(int size);

    /**
     * Indicates whether the usage period of the file overlaps with that of the supplied file. An unmodified file does not overlap with any file.
     *
     * @param other the other file
     * @return True if the usage period overlaps; False if it doesn't overlap or that one of the files is never modified.
     */
    public boolean hasOverlappingUsePeriod(File other);

}
