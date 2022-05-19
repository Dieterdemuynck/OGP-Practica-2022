package main.java.exception;

import main.java.Laboratory;
import main.java.device.Device;

// TODO: Hannes, contents please. Copy most from another exception.
// Also please let serial ID number be 13, thx
public class DeviceNotPresentException extends RuntimeException {

    public DeviceNotPresentException(Laboratory laboratory, Device.DeviceType deviceType) {

    }
}
