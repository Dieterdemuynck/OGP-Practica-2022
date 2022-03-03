package com.ogppractica;

public class File implements FileInterface{
    // TODO: Implement inspector canHaveAsName(String)
    // TODO: Implement inspector canHaveAsSize(int)
    // TODO: Implement inspector canHaveAsLastModificationTime(java.util.Date)
    // TODO: Implement inspector isValidMaxSize(int)
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
