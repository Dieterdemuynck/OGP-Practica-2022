package com.ogppractica;

import java.util.Date;

public class File implements FileInterface {

    //Properties
    private String name;
    private int size;
    public static final int MAX_SIZE = Integer.MAX_VALUE;
    private final Date creationTime;
    private Date lastModificationTime;
    private boolean isWritable;  // file is writable by default

    // Constructors
    public File(String name, int size, boolean writable) {
        setName(name);
        if (canHaveAsSize(size)) {
            this.size = size;
        }
        this.isWritable = writable;
        this.creationTime = new Date();
    }

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

    // TODO: Implement steady-state setters for: name, lastModificationTime, isWritable

    // TODO: Implement inspector canHaveAsName(String)
    public boolean canHaveAsName(String name) {
        return false;
    }

    // TODO: Implement inspector canHaveAsSize(int)
    public boolean canHaveAsSize(int size) {
        return false;
    }

    // TODO: Implement inspector canHaveAsLastModificationTime(java.util.Date)
    public boolean canHaveAsLastModificationTime(Date lastModificationTime) {
        return false;
    }

    public boolean hasLastModificationTime() {
        return lastModificationTime != null;
    }

    // TODO: Implement inspector isValidMaxSize(int)
    public static boolean isValidMaxSize(int size) {
        return false;
    }

    //vergeet ook niet dat lastModificationTime moet aangepast worden :)
    @Override
    public void enlarge(int size) {

    }

    //vergeet ook niet dat lastModificationTime moet aangepast worden :)
    @Override
    public void shorten(int size) {

    }

    @Override
    public boolean hasOverlappingUsePeriod(File other) {
        if (this.hasLastModificationTime() && other.hasLastModificationTime()) {
            if (this.getCreationTime().before(other.getCreationTime())) {
                return this.getLastModificationTime().after(other.getCreationTime());
            } else {
                return other.getLastModificationTime().after(this.getCreationTime());
            }
        } else {
            return false;
        }
    }
}
