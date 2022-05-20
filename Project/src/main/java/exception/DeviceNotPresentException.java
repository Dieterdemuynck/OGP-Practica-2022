package main.java.exception;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import main.java.Laboratory;
import main.java.device.Device;
import main.java.ingredient.AlchemicIngredient;

/**
 * A class for signaling illegal attempts to use a device that is not present.
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 1.0
 */
public class DeviceNotPresentException extends RuntimeException {

    /**
     * Required because this class inherits from Exception
     * (which in its turn implements the interface Serializable)
     */
    private static final long serialVersionUID = 13L;

    /**
     * Variable registering the Laboratory the devices was supposed to be in.
     */
    private final Laboratory laboratory;

    /**
     * Variable referencing the device that was attempted to be used.
     */
    private final Device.DeviceType deviceType;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/

    /**
     * Initialize this new device not empty exception involving the alchemical ingredient in the device and the new
     * alchemical ingredient.
     *
     * @param   laboratory
     *          The laboratory of the device involved in the new device not present exception.
     * @param   deviceType
     *          The device type of the device involved in the new device not present exception.
     * @post    The laboratory and deviceType involved in the new device not present exception are respectively set
     *          to the laboratory and deviceType.
     *          | new.getLaboratory() == laboratory
     *          | new.getDeviceType() == deviceType
     */
    public DeviceNotPresentException(Laboratory laboratory, Device.DeviceType deviceType) {
        this.deviceType = deviceType;
        this.laboratory = laboratory;
    }

    /* *********************************************************
     * LABORATORY
     * *********************************************************/
    /**
     * Return the laboratory of the device involved in this device not present exception.
     */
    @Basic
    @Immutable
    public Laboratory getLaboratory() {return this.laboratory;}

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/
    /**
     * Return the device type involved in this device not present exception.
     */
    @Basic
    @Immutable
    public Device.DeviceType getDeviceType() {return this.deviceType;}


}
