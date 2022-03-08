package com.ogppractica;

import java.util.Date;

public class File implements FileInterface {

    // Properties
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
     *
     */
    private void updateLastModificationTime() {
        Date currentDate = new Date();  // automatically equals the current date
        if (canHaveAsLastModificationTime(currentDate)) {
            // Only if the creation time is indeed before the current date, the lastModificationDate must be updated.
            lastModificationTime = currentDate;
        }
    }

    // TODO: Implement inspector canHaveAsName(String)
    public boolean canHaveAsName(String name) {
        return false;
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
    public void enlarge(int size) throws WritabilityViolationException{

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
