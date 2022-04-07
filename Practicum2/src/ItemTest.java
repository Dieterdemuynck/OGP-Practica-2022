import org.junit.Before;
import org.junit.Test;

import java.security.DigestException;
import java.util.Date;

import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;

/**
 * A JUnit test class for testing the public methods of the File Class  
 * @author Tommy Messelis
 *
 */
public class ItemTest {

	File fileDirectoryStringIntBooleanType;
	File fileDirectoryStringType;
	Link linkDirectoryStringItem;
	Directory root;
	Directory directoryDirectoryStringBoolean;
	Directory directoryDirectoryString;
	Directory directoryStringBoolean;

	Date timeBeforeConstruction, timeAfterConstruction;
	
	File fileNotWritable;
	Directory directoryNotWritable;
	Date timeBeforeConstructionNotWritable, timeAfterConstructionNotWritable;
	
	@Before
	public void setUpFixture(){
		timeBeforeConstruction = new Date();
		root = new Directory("root");
		directoryStringBoolean = new Directory("map1",true);
		directoryDirectoryStringBoolean = new Directory(root,"map2",true);
		directoryDirectoryString = new Directory(root, "map3");

		fileDirectoryStringIntBooleanType = new File(root,"bestand1",100, true,Type.PDF_FILE);
		fileDirectoryStringType = new File(root,"bestand2",Type.PDF_FILE);

		linkDirectoryStringItem = new Link(root, "link", fileDirectoryStringIntBooleanType);

		timeAfterConstruction = new Date();

		timeBeforeConstructionNotWritable = new Date();

		fileNotWritable = new File(root,"bestand3",100, false,Type.PDF_FILE);
		directoryNotWritable = new Directory(root,"map4",false);

		timeAfterConstructionNotWritable = new Date();
	}

	@Test
	public void testFileDirectoryStringIntBooleanType_LegalCase() {
		assertEquals("bestand1", fileDirectoryStringIntBooleanType.getName());
		assertEquals(fileDirectoryStringIntBooleanType.getSize(),100);
		assertTrue(fileDirectoryStringIntBooleanType.isWritable());
		assertNull(fileDirectoryStringIntBooleanType.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileDirectoryStringIntBooleanType.getCreationTime()));
		assertFalse(fileDirectoryStringIntBooleanType.getCreationTime().after(timeAfterConstruction));
	}


	@Test
	public void testFileStringIntBoolean_IllegalCase() {
		timeBeforeConstruction = new Date();
		fileDirectoryStringIntBooleanType = new File(root,"$IllegalName$", File.getMaximumSize(), false,Type.PDF_FILE);
		timeAfterConstruction = new Date();
		assertTrue(File.isValidName(fileDirectoryStringIntBooleanType.getName()));
		assertEquals(File.getMaximumSize(), fileDirectoryStringIntBooleanType.getSize());
		assertFalse(fileDirectoryStringIntBooleanType.isWritable());
		assertNull(fileDirectoryStringIntBooleanType.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileDirectoryStringIntBooleanType.getCreationTime()));
		assertFalse(fileDirectoryStringIntBooleanType.getCreationTime().after(timeAfterConstruction));
	}

	@Test
	public void testFileDirectoryStringType_LegalCase() {
		assertEquals("bestand2", fileDirectoryStringType.getName());
		assertEquals(0, fileDirectoryStringType.getSize());
		assertTrue(fileDirectoryStringType.isWritable());
		assertNull(fileDirectoryStringType.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileDirectoryStringType.getCreationTime()));
		assertFalse(fileDirectoryStringType.getCreationTime().after(timeAfterConstruction));
	}

	@Test
	public void testFileString_IllegalCase() {
		timeBeforeConstruction = new Date();
		fileDirectoryStringType = new File(root,"$IllegalName$",Type.PDF_FILE);
		timeAfterConstruction = new Date();
		assertTrue(File.isValidName(fileDirectoryStringType.getName()));
		assertEquals(0, fileDirectoryStringType.getSize());
		assertTrue(fileDirectoryStringType.isWritable());
		assertNull(fileDirectoryStringType.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileDirectoryStringType.getCreationTime()));
		assertFalse(fileDirectoryStringType.getCreationTime().after(timeAfterConstruction));
	}

	/*
	@Test
	public void testIsValidName_LegalCase() {
		assertTrue(File.isValidName("abcDEF123-_."));
	}

	@Test
	public void testIsValidName_IllegalCase() {
		assertFalse(File.isValidName(null));
		assertFalse(File.isValidName(""));
		assertFalse(File.isValidName("%illegalSymbol"));
		
	}

	@Test
	public void testChangeName_LegalCase() {
		Date timeBeforeSetName = new Date();
		fileString.changeName("NewLegalName");
		Date timeAfterSetName = new Date();
		
		assertEquals("NewLegalName", fileString.getName());
		assertNotNull(fileString.getModificationTime());
		assertFalse(fileString.getModificationTime().before(timeBeforeSetName));
		assertFalse(timeAfterSetName.before(fileString.getModificationTime()));
	}
	
	@Test (expected = ItemNotWritableException.class)
	public void testChangeName_FileNotWritable() {
		fileNotWritable.changeName("NewLegalName");
	}
	
	@Test
	public void testChangeName_IllegalName() {
		fileString.changeName("$IllegalName$");
		assertEquals("bestand.txt", fileString.getName());
		assertNull(fileString.getModificationTime());
	}

	@Test
	public void testIsValidSize_LegalCase() {
		assertTrue(File.isValidSize(0));
		assertTrue(File.isValidSize(File.getMaximumSize()/2));
		assertTrue(File.isValidSize(File.getMaximumSize()));
	}
	
	@Test
	public void testIsValidSize_IllegalCase() {
		assertFalse(File.isValidSize(-1));
		if (File.getMaximumSize() < Integer.MAX_VALUE) {
			assertFalse(File.isValidSize(File.getMaximumSize()+1));
		}
	}

	@Test
	public void testEnlarge_LegalCase() {
		File file = new File("bestand.txt", File.getMaximumSize()-1,true,Type.PDF_FILE);
		Date timeBeforeEnlarge = new Date();
		file.enlarge(1);
		Date timeAfterEnlarge = new Date();		
		assertEquals(file.getSize(), File.getMaximumSize());
		assertNotNull(file.getModificationTime());
		assertFalse(file.getModificationTime().before(timeBeforeEnlarge));
		assertFalse(timeAfterEnlarge.before(file.getModificationTime()));
	}
	
	@Test (expected = ItemNotWritableException.class)
	public void testEnlarge_FileNotWritable() {
		fileNotWritable.enlarge(1);
	}
	
	@Test
	public void testShorten_LegalCase() {
		fileStringIntBoolean.shorten(1);
		Date timeAfterShorten = new Date();		
		assertEquals(fileStringIntBoolean.getSize(),99);
		assertNotNull(fileStringIntBoolean.getModificationTime());
		assertFalse(fileStringIntBoolean.getModificationTime().before(timeAfterConstruction));
		assertFalse(timeAfterShorten.before(fileStringIntBoolean.getModificationTime()));
	}
	
	@Test (expected = ItemNotWritableException.class)
	public void testShorten_FileNotWritable() {
		fileNotWritable.shorten(1);
	}

	@Test
	public void testIsValidCreationTime_LegalCase() {
		Date now = new Date();
		assertTrue(File.isValidCreationTime(now));
	}
	
	@Test
	public void testIsValidCreationTime_IllegalCase() {
		assertFalse(File.isValidCreationTime(null));
		Date inFuture = new Date(System.currentTimeMillis() + 1000*60*60);
		assertFalse(File.isValidCreationTime(inFuture));
	}
	
	@Test
	public void testcanHaveAsModificationTime_LegalCase() {
		assertTrue(fileString.canHaveAsModificationTime(null));
		assertTrue(fileString.canHaveAsModificationTime(new Date()));
	}
	
	@Test
	public void testcanHaveAsModificationTime_IllegalCase() {
		assertFalse(fileString.canHaveAsModificationTime(new Date(timeAfterConstruction.getTime() - 1000*60*60)));
		assertFalse(fileString.canHaveAsModificationTime(new Date(System.currentTimeMillis() + 1000*60*60)));
	}

	@Test
	public void testHasOverlappingUsePeriod_UnmodifiedFiles() {
		// one = implicit argument ; other = explicit argument
		File one = new File("one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		File other = new File("other",Type.PDF_FILE);
		
		//1 Test unmodified case
		assertFalse(one.hasOverlappingUsePeriod(other));
		
		//2 Test one unmodified case
		other.enlarge(File.getMaximumSize());
		assertFalse(one.hasOverlappingUsePeriod(other));
		
		//3 Test other unmodified case
		//so re-initialise the other file
		other = new File("other",Type.PDF_FILE);
		one.enlarge(File.getMaximumSize());
		assertFalse(one.hasOverlappingUsePeriod(other));
		
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedNoOverlap() {
		// one = implicit argument ; other = explicit argument
		File one, other;
		one = new File("one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File("other",Type.PDF_FILE);
		
		//1 Test one created and modified before other created and modified case
		one.enlarge(File.getMaximumSize());
        sleep();
        //re-initialise the other
        other = new File("other",Type.PDF_FILE);
        other.enlarge(File.getMaximumSize());
	    assertFalse(one.hasOverlappingUsePeriod(other));
	    
	    //2 Test other created and modified before one created and modified
		other.enlarge(File.getMaximumSize());
        sleep();
        one = new File("one",Type.PDF_FILE);
        one.enlarge(File.getMaximumSize());
        assertFalse(one.hasOverlappingUsePeriod(other));
	
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_A() {
		// one = implicit argument ; other = explicit argument
		//A Test one created before other created before one modified before other modified
	    File one, other;
		one = new File("one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File("other",Type.PDF_FILE);
	
		one.enlarge(File.getMaximumSize());
        sleep();
        other.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_B() {
		// one = implicit argument ; other = explicit argument
		//B Test one created before other created before other modified before one modified
       	File one, other;
		one = new File("one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File("other",Type.PDF_FILE);
	
		other.enlarge(File.getMaximumSize());
        sleep();
        one.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_C() {
		// one = implicit argument ; other = explicit argument
		//C Test other created before one created before other modified before one modified
        File one, other;
		other = new File("other",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new File("one",Type.PDF_FILE);
		
		other.enlarge(File.getMaximumSize());
        sleep();
        one.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testHasOverlappingUsePeriod_ModifiedOverlap_D() {
		// one = implicit argument ; other = explicit argument
		//D Test other created before one created before one modified before other modified
		File one, other;
		other = new File("one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new File("other",Type.PDF_FILE);
	
		one.enlarge(File.getMaximumSize());
        sleep();
        other.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testSetWritable() {
		fileString.setWritable(false);
		fileNotWritable.setWritable(true);
		assertFalse(fileString.isWritable());
		assertTrue(fileNotWritable.isWritable());
	}
	
	private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    */

}
