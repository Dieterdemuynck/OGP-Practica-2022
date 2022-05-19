package main.java.ingredient.exception;

import be.kuleuven.cs.som.annotate.*;


//TODO: iemand die dit kan nakijken of dit goed genoeg is? Want ken nie zo veel van exceptions =)
/**
 * A class for signaling illegal attempts to set the name of an ingredient type due to in valid characters.
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 2.2
 */
public class IllegalNameException extends RuntimeException {

    /**
     * Required because this class inherits from Exception.
     * (which in its turn implements the interface Serializable)
     */
    private static final long serialVersionUID = 100L;
    /**
     * Variable referencing to the name that was denied
     */
    private final String name;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this new illegal name exception involving the given name.
     *
     * @param   name
     *          the name for the new illegal name exception.
     * @post    The name involved in the new illegal name exception is set to the given name.
     *          | new.getName() == name
     */
    public IllegalNameException(String name) {
        this.name = name;
    }

    /* *********************************************************
     * NAME
     * *********************************************************/
    /**
     * Return the name involved in this illegal name exception.
     */
    @Basic
    @Immutable
    public String getName() {
        return name;
    }

}