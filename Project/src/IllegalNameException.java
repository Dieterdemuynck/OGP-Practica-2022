//import be.kuleuven.cs.som.annotate.*;


//TODO: iemand die dit kan nakijken of dit goed genoeg is? Want ken nie zo veel van exceptions =)
/**
 *
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 2.2
 */
public class IllegalNameException extends RuntimeException {

    /**
     * Required because this class inherits from Exception
     */
    private static final long serialVersionUID = 1L;


    private final String name;


    public IllegalNameException(String name) {
        this.name = name;
    }

    /**
     * Return the item involved in this item not writable exception.
     */
    //@Basic
    //@Immutable
    public String getName() {
        return name;
    }

}