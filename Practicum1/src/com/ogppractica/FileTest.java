package com.ogppractica;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.*;

import org.junit.Test;

public class FileTest {

    @Test
    public void testConstructorName() {
        // Case 1: valid name
        File myFile = new File("my_file");
        Date currentTime = new Date();
        assertEquals(myFile.getName(), "my_file");
        assertEquals(myFile.getCreationTime(), currentTime);
        assertEquals(myFile.getSize(), 0);
        assertTrue(myFile.isWritable());

        // Case 2: invalid name
        File myFile2 = new File("my file");
        Date currentTime2 = new Date();
        assertEquals(myFile2.getName(), "default");
        assertEquals(myFile2.getCreationTime(), currentTime2);
        assertEquals(myFile2.getSize(), 0);
        assertTrue(myFile2.isWritable());
    }

    @Test
    public void testConstructorNameSizeWritable() {
        // Case 1: valid name, valid size and writable
        File myFile = new File("my_file",10, true);
        Date currentTime = new Date();
        assertEquals(myFile.getName(), "my_file" );
        assertEquals(myFile.getCreationTime(), currentTime);
        assertEquals(myFile.getSize(), 10);
        assertTrue(myFile.isWritable());

        // Case 2: invalid name, valid size and writable
        File myFile2 = new File("my file",10, true);
        Date currentTime2 = new Date();
        assertEquals(myFile2.getName(), "default");
        assertEquals(myFile2.getCreationTime(), currentTime2);
        assertEquals(myFile2.getSize(), 10);
        assertTrue(myFile2.isWritable());

        // Case 3: valid name, invalid size and writable
        File myFile3 = new File("my_file", -10, true);
        Date currentTime3 = new Date();
        assertEquals(myFile3.getName(), "my_file");
        assertEquals(myFile3.getCreationTime(), currentTime3);
        assertEquals(myFile3.getSize(), 0); //DEFAULT VAN INT IS 0...
        assertTrue(myFile3.isWritable());

        // Case 4: invalid name, invalid size and writable
        File myFile4 = new File("my file", -10, true);
        Date currentTime4 = new Date();
        assertEquals(myFile4.getName(), "default");
        assertEquals(myFile4.getCreationTime(), currentTime4);
        assertEquals(myFile4.getSize(), 0); //DEFAULT VAN INT IS 0...
        assertTrue(myFile4.isWritable());

        // Case 5: valid name, valid size and not writable
        File myFile5 = new File("my_file", 10, false);
        Date currentTime5 = new Date();
        assertEquals(myFile5.getName(), "my_file");
        assertEquals(myFile5.getCreationTime(), currentTime5);
        assertEquals(myFile5.getSize(), 10);
        assertFalse(myFile5.isWritable());

        // Case 6: invalid name, valid size and not writable
        File myFile6 = new File("my file", 10, false);
        Date currentTime6 = new Date();
        assertEquals(myFile6.getName(), "default");
        assertEquals(myFile6.getCreationTime(), currentTime6);
        assertEquals(myFile6.getSize(), 10);
        assertFalse(myFile6.isWritable());

        // Case 7: valid name, invalid size and not writable
        File myFile7 = new File("my_file", -10, false);
        Date currentTime7 = new Date();
        assertEquals(myFile7.getName(), "my_file");
        assertEquals(myFile7.getCreationTime(), currentTime7);
        assertEquals(myFile7.getSize(), 0);
        assertFalse(myFile7.isWritable());

        // Case 8: invalid name, invalid size and not writable
        File myFile8 = new File("my file", -10, false);
        Date currentTime8 = new Date();
        assertEquals(myFile8.getName(), "default");
        assertEquals(myFile8.getCreationTime(), currentTime8);
        assertEquals(myFile8.getSize(), 0);
        assertFalse(myFile8.isWritable());
    }

    @Test
    public void testHasOverlappingUsePeriod() throws WritabilityViolationException, InterruptedException {
        // Case 1: Overlapping time period
        TimeUnit time = TimeUnit.SECONDS;
        File myFile1 = new File("my_file1",500,true);
        time.sleep(1);
        File myFile2 = new File("my_file2",40,true);
        time.sleep(1);
        myFile1.shorten(10);
        time.sleep(1);
        myFile2.shorten(10);
        assertTrue(myFile1.hasOverlappingUsePeriod(myFile2));

        // Case 2: No overlapping time period
        File myFile3 = new File("my_file3",500,true);
        time.sleep(1);
        myFile1.shorten(10);
        time.sleep(1);
        File myFile4 = new File("my_file4",40,true);
        time.sleep(1);
        myFile2.shorten(15);
        assertFalse(myFile3.hasOverlappingUsePeriod(myFile4));

        // Case 3: 1 file is never modified
        File myFile5 = new File("my_file5",500,true);
        time.sleep(1);
        myFile1.shorten(10);
        time.sleep(1);
        File myFile6 = new File("my_file6",40,true);
        assertFalse(myFile5.hasOverlappingUsePeriod(myFile6));
    }

    @Test
    public void testEnlargeValid() throws WritabilityViolationException {
        File myFile = new File("TheChungusinator", 9001, true);
        myFile.enlarge(69420);
        assertEquals(78421, myFile.getSize());
        // NOTE: testing for invalid values for size is not done, as this method is programmed assuming nominal
        // conditions.
    }

    @Test(expected = WritabilityViolationException.class)
    public void testEnlargeWritableViolation() throws WritabilityViolationException {
        File myFile = new File("generic_file_name", 1302, false);
        myFile.enlarge(520);
    }

    @Test
    public void testShortenValid() throws WritabilityViolationException {
        File myFile = new File("generic_file_name", 1302, true);
        myFile.shorten(520);
        assertEquals(782, myFile.getSize());
        // NOTE: testing for invalid values for size is not done, as this method is programmed assuming nominal
        // conditions.
    }

    @Test(expected = WritabilityViolationException.class)
    public void testShortenWritableViolation() throws WritabilityViolationException {
        File myFile = new File("generic_file_name", 1302, false);
        myFile.shorten(520);
    }
}

