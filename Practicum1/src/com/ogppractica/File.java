package com.ogppractica;

import java.util.Date;

public class File implements FileInterface{

    private String name;
    private int size;
    public static final int MAX_SIZE = Integer.MAX_VALUE;
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

    // TODO: Implement inspector isValidMaxSize(int)
    public static boolean isValidMaxSize(int size){
        return false;
    }

    @Override
    public void enlarge(int size) {

    }

    @Override
    public void shorten(int size) {

    }

    @Override
    public boolean hasOverlappingUsePeriod(File other) {
        if (this.creationTime.before(this.lastModificationTime) && other.creationTime.before(other.lastModificationTime)){
            if (this.creationTime.before(other.creationTime)) {
                return this.lastModificationTime.after(other.creationTime);
            }
            else {
                return other.lastModificationTime.after(this.creationTime);
            }
        }
        else{
            return false;
        }
    }
}
