package main.java.ingredient.exception;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import main.java.Laboratory;
import main.java.device.Device;

/**
 * A class for signaling illegal attempts to use a special name that does not exist.
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 1.0
 */
public class SpecialNameDoesNotExistException extends RuntimeException {

    /**
     * Required because this class inherits from Exception
     * (which in its turn implements the interface Serializable)
     */
    private static final long serialVersionUID = 69L;

    /**
     * Variable registering the Laboratory the ingredient with the special name was supposed to be in.
     */
    private final Laboratory laboratory;

    /**
     * Variable referencing the special name that was supposed to exist.
     */
    private final String specialName;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this special name does not exist exception involving the laboratory containing this ingredient and the special name of the ingredient.
     *
     * @param   laboratory
     *          The laboratory of the device involved in the special name does not exist exception.
     * @param   specialName
     *          The special name of the ingredient involved in the special name does not exist exception.
     * @post    The laboratory and specialName involved in the special name does not exist exception are respectively set
     *          to the given laboratory and specialName.
     *          | new.getLaboratory() == laboratory
     *          | new.getSpecialName() == specialName
     */
    public SpecialNameDoesNotExistException(Laboratory laboratory, String specialName) {
        this.laboratory = laboratory;
        this.specialName = specialName;
    }

    /* *********************************************************
     * LABORATORY
     * *********************************************************/
    /**
     * Return the laboratory of the ingredient involved in this special name does not exist exception.
     */
    @Basic
    @Immutable
    public Laboratory getLaboratory() {return this.laboratory;}

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/
    /**
     * Return the special name of the ingredient involved in this special name does not exist exception.
     */
    @Basic
    @Immutable
    public String getSpecialName() {return this.specialName;}


}
