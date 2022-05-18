package main.java.device;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import main.java.Laboratory;

/**
 *
 *
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
 * @version 2.2
 */
public class DeviceTypeAlreadyPresentInLaboratoryException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4L;
    private final Device.DeviceType deviceType;
    private final Laboratory laboratory;

    /* *********************************************************
     * CONSTRUCTOR
     * *********************************************************/
    public DeviceTypeAlreadyPresentInLaboratoryException(Device.DeviceType deviceType, Laboratory laboratory) {
        this.deviceType = deviceType;
        this.laboratory = laboratory;
    }

    /* *********************************************************
     * DEVICE TYPE
     * *********************************************************/

    /**
     *
     */
    @Basic
    @Immutable
    public Device.DeviceType getDeviceType() {
        return deviceType;
    }

    /* *********************************************************
     * LABORATORY
     * *********************************************************/

    /**
     *
     */
    @Basic
    @Immutable
    public Laboratory getLaboratory() {
        return laboratory;
    }
}
