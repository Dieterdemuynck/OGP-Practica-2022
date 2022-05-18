package test;

import main.java.Laboratory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LaboratoryTest {

    /* *********************************************************
     * CONSTRUCTOR TESTS - for all devices
     * *********************************************************/
    @Test
    public void constructorTest(){
        Laboratory laboratory = new Laboratory(10);
        assertEquals(10, laboratory.getStoreroomCapacity());
        // TODO: I think it's obvious that you should also instantiate ingredient and device storage on laboratory
        //  instantiation... Why the "assertNull(...)"?
        // assertNull(laboratory.getStorage());
        // assertNull(laboratory.getDevices());
    }
}
