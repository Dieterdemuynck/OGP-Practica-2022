/**
 * An enum representing the type (and extension) a file can have.
 * For now, we distinguish text-files, java-files and pdfs.
 */
public enum Type {
    TEXT_FILE("txt"),
    JAVA_FILE("java"),
    PDF_FILE("pdf");

    private final String extension;

    private Type(String extension){
        this.extension = extension;
    }

    public String getExtension(){
        return extension;
    }

}
