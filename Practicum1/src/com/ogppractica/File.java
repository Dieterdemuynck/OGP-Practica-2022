package com.ogppractica;

import java.util.Date;

public class File implements FileInterface{
    // TODO: Implement inspector canHaveAsName(String)
    // TODO: Implement inspector canHaveAsSize(int)
    // TODO: Implement inspector canHaveAsLastModificationTime(java.util.Date)
    // TODO: Implement inspector isValidMaxSize(int)

    private String name;
    private int size;
    private final static int maxSize = Integer.MAX_VALUE;  // should this be public?
    private Date creationTime;
    private Date lastModificationTime;
    private boolean isWritable = true;  // file is writable by default

    public String getName() {
        return name;
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

    // TODO: Implement steady-state setters for: name, lastModificationTime, isWritable

    @Override
    public void enlarge(int size) {

    }

    @Override
    public void shorten(int size) {

    }

    @Override
    public boolean hasOverlappingUsePeriod(File other) {
        return false;
    }
}
