import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Date;

import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;

/**
 * A JUnit test class for testing the public methods of the File Class  
 * @author Tommy Messelis
 * @author Team 2: Dieter Demuynck, Hannes Ingelaere en Ine Malfait
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
		directoryStringBoolean = new Directory("dir1",true);
		directoryDirectoryStringBoolean = new Directory(root,"dir2",true);
		directoryDirectoryString = new Directory(root, "dir3");

		fileDirectoryStringIntBooleanType = new File(root,"file1",100, true,Type.PDF_FILE);
		fileDirectoryStringType = new File(root,"file2",Type.PDF_FILE);

		linkDirectoryStringItem = new Link(root, "link", fileDirectoryStringIntBooleanType);

		timeAfterConstruction = new Date();

		timeBeforeConstructionNotWritable = new Date();

		fileNotWritable = new File(root,"file3",100, false,Type.PDF_FILE);
		directoryNotWritable = new Directory(root,"dir4",false);

		timeAfterConstructionNotWritable = new Date();
	}

	// DIRECTORY CONSTRUCTOR TEST---------------------------------------------------------------------------------------
	@Test
	public void testEmptyRoot_LegalCase(){
		timeBeforeConstruction = new Date();
		root = new Directory("root");
		timeAfterConstruction = new Date();
		assertEquals("root", root.getName());
		assertTrue(root.isWritable());
		assertNull(root.getModificationTime());
		assertFalse(timeBeforeConstruction.after(root.getCreationTime()));
		assertFalse(root.getCreationTime().after(timeAfterConstruction));
		assertNull(root.getParentDirectory());
		assertEquals(0,root.getNbItems()); //TODO is this a good test to check contents?
	}

	@Test
	public void testEmptyRoot_IllegalCase(){
		timeBeforeConstruction = new Date();
		root = new Directory("r..t");
		timeAfterConstruction = new Date();
		assertFalse(Directory.isValidName("r..t"));
		assertTrue(Directory.isValidName(root.getName()));
		assertTrue(root.isWritable());
		assertNull(root.getModificationTime());
		assertFalse(timeBeforeConstruction.after(root.getCreationTime()));
		assertFalse(root.getCreationTime().after(timeAfterConstruction));
		assertNull(root.getParentDirectory());
		assertEquals(0,root.getNbItems()); //TODO is this a good test to check contents?
	}

	@Test
	public void testNonEmptyRoot_LegalCase(){
		assertEquals("root", root.getName());
		assertTrue(root.isWritable());
		assertNotNull(root.getModificationTime()); //Because things where added to root
		assertFalse(timeBeforeConstruction.after(root.getCreationTime()));
		assertFalse(root.getCreationTime().after(timeAfterConstruction));
		assertNull(root.getParentDirectory());
		assertEquals(7,root.getNbItems()); // 3 directories + 2 files + 1 link
	}

	@Test
	public void testDirectoryStringBoolean_LegalCase(){
		assertEquals("dir1", directoryStringBoolean.getName());
		assertTrue(directoryStringBoolean.isWritable());
		assertNull(directoryStringBoolean.getModificationTime());
		assertFalse(timeBeforeConstruction.after(directoryStringBoolean.getCreationTime()));
		assertFalse(directoryStringBoolean.getCreationTime().after(timeAfterConstruction));
		assertNull(directoryStringBoolean.getParentDirectory());
		assertEquals(0,directoryStringBoolean.getNbItems());
	}

	@Test
	public void testDirectoryStringBoolean_IllegalCase(){
		timeBeforeConstruction = new Date();
		directoryStringBoolean = new Directory("d.r1",false);
		timeAfterConstruction = new Date();
		assertFalse(Directory.isValidName("d.r1"));
		assertTrue(Directory.isValidName(directoryStringBoolean.getName()));
		assertFalse(directoryStringBoolean.isWritable());
		assertNull(directoryStringBoolean.getModificationTime());
		assertFalse(timeBeforeConstruction.after(directoryStringBoolean.getCreationTime()));
		assertFalse(directoryStringBoolean.getCreationTime().after(timeAfterConstruction));
		assertNull(directoryStringBoolean.getParentDirectory());
		assertEquals(0,directoryStringBoolean.getNbItems());
	}

	@Test
	public void testDirectoryDirectoryStringBoolean_LegalCase(){
		assertEquals("dir2", directoryDirectoryStringBoolean.getName());
		assertTrue(directoryDirectoryStringBoolean.isWritable());
		assertNull(directoryDirectoryStringBoolean.getModificationTime());
		assertFalse(timeBeforeConstruction.after(directoryDirectoryStringBoolean.getCreationTime()));
		assertFalse(directoryDirectoryStringBoolean.getCreationTime().after(timeAfterConstruction));
		assertEquals(root,directoryDirectoryStringBoolean.getParentDirectory());
		assertEquals(0,directoryDirectoryStringBoolean.getNbItems());
	}

	@Test
	public void testDirectoryDirectoryStringBoolean_IllegalCase(){
		timeBeforeConstruction = new Date();
		directoryDirectoryStringBoolean = new Directory(root,"d.r2",false);
		timeAfterConstruction = new Date();
		assertFalse(Directory.isValidName("d.r2"));
		assertTrue(Directory.isValidName(directoryStringBoolean.getName()));
		assertFalse(directoryDirectoryStringBoolean.isWritable());
		assertNull(directoryDirectoryStringBoolean.getModificationTime());
		assertFalse(timeBeforeConstruction.after(directoryDirectoryStringBoolean.getCreationTime()));
		assertFalse(directoryDirectoryStringBoolean.getCreationTime().after(timeAfterConstruction));
		assertEquals(root,directoryDirectoryStringBoolean.getParentDirectory());
		assertEquals(0,directoryDirectoryStringBoolean.getNbItems());
	}

	@Test
	public void testDirectoryDirectoryString_LegalCase(){
		assertEquals("dir3", directoryDirectoryString.getName());
		assertTrue(directoryDirectoryString.isWritable());
		assertNull(directoryDirectoryString.getModificationTime());
		assertFalse(timeBeforeConstruction.after(directoryDirectoryString.getCreationTime()));
		assertFalse(directoryDirectoryString.getCreationTime().after(timeAfterConstruction));
		assertEquals(root,directoryDirectoryString.getParentDirectory());
		assertEquals(0,directoryDirectoryString.getNbItems());
	}

	@Test
	public void testDirectoryDirectoryString_IllegalCase(){
		timeBeforeConstruction = new Date();
		directoryDirectoryString = new Directory(root, "d.r3");
		timeAfterConstruction = new Date();
		assertFalse(Directory.isValidName("d.r3"));
		assertTrue(Directory.isValidName(directoryStringBoolean.getName()));
		assertTrue(directoryDirectoryString.isWritable());
		assertNull(directoryDirectoryString.getModificationTime());
		assertFalse(timeBeforeConstruction.after(directoryDirectoryString.getCreationTime()));
		assertFalse(directoryDirectoryString.getCreationTime().after(timeAfterConstruction));
		assertEquals(root,directoryDirectoryString.getParentDirectory());
		assertEquals(0,directoryDirectoryString.getNbItems());
	}


	// FILE CONSTRUCTOR TESTS-------------------------------------------------------------------------------------------
	@Test
	public void testFileDirectoryStringIntBooleanType_LegalCase() {
		assertEquals("file1", fileDirectoryStringIntBooleanType.getName());
		assertEquals(fileDirectoryStringIntBooleanType.getSize(),100);
		assertTrue(fileDirectoryStringIntBooleanType.isWritable());
		assertNull(fileDirectoryStringIntBooleanType.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileDirectoryStringIntBooleanType.getCreationTime()));
		assertFalse(fileDirectoryStringIntBooleanType.getCreationTime().after(timeAfterConstruction));
		assertEquals(root, fileDirectoryStringIntBooleanType.getParentDirectory());
		assertEquals(Type.PDF_FILE, fileDirectoryStringIntBooleanType.getType());
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
		assertEquals(root, fileDirectoryStringIntBooleanType.getParentDirectory());
		assertEquals(Type.PDF_FILE, fileDirectoryStringIntBooleanType.getType());
	}

	@Test
	public void testFileDirectoryStringType_LegalCase() {
		assertEquals("file2", fileDirectoryStringType.getName());
		assertEquals(0, fileDirectoryStringType.getSize());
		assertTrue(fileDirectoryStringType.isWritable());
		assertNull(fileDirectoryStringType.getModificationTime());
		assertFalse(timeBeforeConstruction.after(fileDirectoryStringType.getCreationTime()));
		assertFalse(fileDirectoryStringType.getCreationTime().after(timeAfterConstruction));
		assertEquals(root, fileDirectoryStringIntBooleanType.getParentDirectory());
		assertEquals(Type.PDF_FILE, fileDirectoryStringIntBooleanType.getType());
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
		assertEquals(root, fileDirectoryStringIntBooleanType.getParentDirectory());
		assertEquals(Type.PDF_FILE, fileDirectoryStringIntBooleanType.getType());
	}

	// LINK CONSTRUCTOR TEST--------------------------------------------------------------------------------------------
	@Test
	public void testLinkDirectoryStringItem_LegalCase(){
		assertEquals("link", linkDirectoryStringItem.getName());
		assertNull(linkDirectoryStringItem.getModificationTime());
		assertFalse(timeBeforeConstruction.after(linkDirectoryStringItem.getCreationTime()));
		assertFalse(linkDirectoryStringItem.getCreationTime().after(timeAfterConstruction));
		assertEquals(root, linkDirectoryStringItem.getParentDirectory());
		assertEquals(fileDirectoryStringIntBooleanType, linkDirectoryStringItem.getLinkedItem());
	}

	@Test
	public void testLinkDirectoryStringItem_IllegalCase(){
		timeBeforeConstruction = new Date();
		linkDirectoryStringItem = new Link(root,"$link$",fileDirectoryStringIntBooleanType);
		timeAfterConstruction = new Date();
		assertFalse(File.isValidName("$link$"));
		assertTrue(File.isValidName(linkDirectoryStringItem.getName()));
		assertNull(linkDirectoryStringItem.getModificationTime());
		assertFalse(timeBeforeConstruction.after(linkDirectoryStringItem.getCreationTime()));
		assertFalse(linkDirectoryStringItem.getCreationTime().after(timeAfterConstruction));
		assertEquals(root, linkDirectoryStringItem.getParentDirectory());
		assertEquals(fileDirectoryStringIntBooleanType, linkDirectoryStringItem.getLinkedItem());
	}


	// FILE NAME TESTS--------------------------------------------------------------------------------------------------
	@Test
	public void testFileIsValidName_LegalCase() {
		assertTrue(File.isValidName("abcDEF123-_."));
	}

	@Test
	public void testFileIsValidName_IllegalCase() {
		assertFalse(File.isValidName(null));
		assertFalse(File.isValidName(""));
		assertFalse(File.isValidName("%illegalSymbol"));
	}

	@Test
	public void testFileChangeName_LegalCase() {
		Date timeBeforeSetName = new Date();
		fileDirectoryStringType.changeName("NewLegalName");
		Date timeAfterSetName = new Date();

		assertEquals("NewLegalName", fileDirectoryStringType.getName());
		assertNotNull(fileDirectoryStringType.getModificationTime());
		assertFalse(fileDirectoryStringType.getModificationTime().before(timeBeforeSetName));
		assertFalse(timeAfterSetName.before(fileDirectoryStringType.getModificationTime()));
	}

	@Test
	public void testFileChangeName_IllegalName() {
		fileDirectoryStringType.changeName("$IllegalName$");
		assertEquals("file2", fileDirectoryStringType.getName());
		assertNull(fileDirectoryStringType.getModificationTime());
	}

	@Test (expected = ItemNotWritableException.class)
	public void testFileChangeName_FileNotWritable() {
		fileNotWritable.changeName("NewLegalName");
	}


	// DIRECTORY NAME TESTS---------------------------------------------------------------------------------------------
	@Test
	public void testDirectoryIsValidName_LegalCase() {
		assertTrue(Directory.isValidName("abcDEF123-_"));
	}

	@Test
	public void testDirectoryIsValidName_IllegalCase() {
		assertFalse(Directory.isValidName(null));
		assertFalse(Directory.isValidName(""));
		assertFalse(Directory.isValidName("%illegalSymbol"));
	}

	@Test
	public void testDirectoryChangeName_LegalCase() {
		Date timeBeforeSetName = new Date();
		directoryDirectoryString.changeName("NewLegalName");
		Date timeAfterSetName = new Date();

		assertEquals("NewLegalName", directoryDirectoryString.getName());
		assertNotNull(directoryDirectoryString.getModificationTime());
		assertFalse(directoryDirectoryString.getModificationTime().before(timeBeforeSetName));
		assertFalse(timeAfterSetName.before(directoryDirectoryString.getModificationTime()));
	}

	@Test (expected = ItemNotWritableException.class)
	public void testDirectoryChangeName_FileNotWritable() {
		directoryNotWritable.changeName("NewLegalName");
	}

	@Test
	public void testDirectoryChangeName_IllegalName() {
		directoryDirectoryString.changeName("$IllegalName$");
		assertEquals("dir3", directoryDirectoryString.getName());
		assertNull(directoryDirectoryString.getModificationTime());
	}

	// LINK NAME TESTS--------------------------------------------------------------------------------------------------
	@Test
	public void testLinkIsValidName_LegalCase() {
		assertTrue(Link.isValidName("abcDEF123-_."));
	}

	@Test
	public void testLinkIsValidName_IllegalCase() {
		assertFalse(Link.isValidName(null));
		assertFalse(Link.isValidName(""));
		assertFalse(Link.isValidName("%illegalSymbol"));
	}

	@Test
	public void testLinkChangeName() {
		Date timeBeforeSetName = new Date();
		linkDirectoryStringItem.changeName("NewLegalName");
		Date timeAfterSetName = new Date();

		assertEquals("NewLegalName", linkDirectoryStringItem.getName());
		assertNotNull(linkDirectoryStringItem.getModificationTime());
		assertFalse(linkDirectoryStringItem.getModificationTime().before(timeBeforeSetName));
		assertFalse(timeAfterSetName.before(linkDirectoryStringItem.getModificationTime()));
	}

	@Test
	public void testLinkChangeName_IllegalName() {
		linkDirectoryStringItem.changeName("$IllegalName$");
		assertEquals("link", linkDirectoryStringItem.getName());
		assertNull(linkDirectoryStringItem.getModificationTime());
	}


	// FILE SIZE TESTS--------------------------------------------------------------------------------------------------
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
		File file = new File(root,"file", File.getMaximumSize()-1,true,Type.PDF_FILE);
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
		fileDirectoryStringIntBooleanType.shorten(1);
		Date timeAfterShorten = new Date();		
		assertEquals(fileDirectoryStringIntBooleanType.getSize(),99);
		assertNotNull(fileDirectoryStringIntBooleanType.getModificationTime());
		assertFalse(fileDirectoryStringIntBooleanType.getModificationTime().before(timeAfterConstruction));
		assertFalse(timeAfterShorten.before(fileDirectoryStringIntBooleanType.getModificationTime()));
	}

	@Test (expected = ItemNotWritableException.class)
	public void testShorten_FileNotWritable() {
		fileNotWritable.shorten(1);
	}


	// FILE CREATION TIME TESTS-----------------------------------------------------------------------------------------
	@Test
	public void testFileIsValidCreationTime_LegalCase() {
		Date now = new Date();
		assertTrue(File.isValidCreationTime(now));
	}
	
	@Test
	public void testFileIsValidCreationTime_IllegalCase() {
		assertFalse(File.isValidCreationTime(null));

		Date inFuture = new Date(System.currentTimeMillis() + 1000*60*60);
		assertFalse(File.isValidCreationTime(inFuture));
	}


	// DIRECTORY CREATION TIME TESTS------------------------------------------------------------------------------------
	@Test
	public void testDirectoryIsValidCreationTime_LegalCase() {
		Date now = new Date();
		assertTrue(Directory.isValidCreationTime(now));
	}

	@Test
	public void testDirectoryIsValidCreationTime_IllegalCase() {
		assertFalse(Directory.isValidCreationTime(null));

		Date inFuture = new Date(System.currentTimeMillis() + 1000*60*60);
		assertFalse(Directory.isValidCreationTime(inFuture));
	}


	// LINK CREATION TIME TESTS-----------------------------------------------------------------------------------------
	@Test
	public void testLinkIsValidCreationTime_LegalCase() {
		Date now = new Date();
		assertTrue(Link.isValidCreationTime(now));
	}

	@Test
	public void testLinkIsValidCreationTime_IllegalCase() {
		assertFalse(Link.isValidCreationTime(null));

		Date inFuture = new Date(System.currentTimeMillis() + 1000*60*60);
		assertFalse(Link.isValidCreationTime(inFuture));
	}


	// FILE MODIFICATION TIME TESTS-------------------------------------------------------------------------------------
	@Test
	public void testFileCanHaveAsModificationTime_LegalCase() {
		assertTrue(fileDirectoryStringType.canHaveAsModificationTime(null));
		assertTrue(fileDirectoryStringType.canHaveAsModificationTime(new Date()));
	}
	
	@Test
	public void testFileCanHaveAsModificationTime_IllegalCase() {
		assertFalse(linkDirectoryStringItem.canHaveAsModificationTime(new Date(timeAfterConstruction.getTime() - 1000*60*60)));
		assertFalse(linkDirectoryStringItem.canHaveAsModificationTime(new Date(System.currentTimeMillis() + 1000*60*60)));
	}

	@Test
	public void testFileHasOverlappingUsePeriod_UnmodifiedFiles() {
		// one = implicit argument ; other = explicit argument
		File one = new File(root,"one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		File other = new File(root,"other",Type.PDF_FILE);
		
		//1 Test unmodified case
		assertFalse(one.hasOverlappingUsePeriod(other));
		
		//2 Test one unmodified case
		other.enlarge(File.getMaximumSize());
		assertFalse(one.hasOverlappingUsePeriod(other));
		
		//3 Test other unmodified case
		//so re-initialise the other file by first terminating existing other file and then recreating that file
		other.deleteRecursive();
		other = new File(root, "other",Type.PDF_FILE);
		one.enlarge(File.getMaximumSize());
		assertFalse(one.hasOverlappingUsePeriod(other));
		
	}

	@Test
	public void testFileHasOverlappingUsePeriod_ModifiedNoOverlap() {
		// one = implicit argument ; other = explicit argument
		File one, other;
		one = new File(root,"one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File(root,"other",Type.PDF_FILE);
		
		//1 Test one created and modified before other created and modified case
		one.enlarge(File.getMaximumSize());
        sleep();
        //re-initialise the other
		other.deleteRecursive();
        other = new File(root,"other",Type.PDF_FILE);
        other.enlarge(File.getMaximumSize());
	    assertFalse(one.hasOverlappingUsePeriod(other));
	    
	    //2 Test other created and modified before one created and modified
		other.enlarge(File.getMaximumSize());
        sleep();
		one.deleteRecursive();
        one = new File(root,"one",Type.PDF_FILE);
        one.enlarge(File.getMaximumSize());
        assertFalse(one.hasOverlappingUsePeriod(other));

	}

	@Test
	public void testFileHasOverlappingUsePeriod_ModifiedOverlap_A() {
		// one = implicit argument ; other = explicit argument
		//A Test one created before other created before one modified before other modified
	    File one, other;
		one = new File(root,"one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File(root,"other",Type.PDF_FILE);
	
		one.enlarge(File.getMaximumSize());
        sleep();
        other.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testFileHasOverlappingUsePeriod_ModifiedOverlap_B() {
		// one = implicit argument ; other = explicit argument
		//B Test one created before other created before other modified before one modified
       	File one, other;
		one = new File(root,"one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new File(root,"other",Type.PDF_FILE);
	
		other.enlarge(File.getMaximumSize());
        sleep();
        one.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testFileHasOverlappingUsePeriod_ModifiedOverlap_C() {
		// one = implicit argument ; other = explicit argument
		//C Test other created before one created before other modified before one modified
        File one, other;
		other = new File(root,"other",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new File(root,"one",Type.PDF_FILE);
		
		other.enlarge(File.getMaximumSize());
        sleep();
        one.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}
	
	@Test
	public void testFileHasOverlappingUsePeriod_ModifiedOverlap_D() {
		// one = implicit argument ; other = explicit argument
		//D Test other created before one created before one modified before other modified
		File one, other;
		other = new File(root,"one",Type.PDF_FILE);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new File(root,"other",Type.PDF_FILE);
	
		one.enlarge(File.getMaximumSize());
        sleep();
        other.enlarge(File.getMaximumSize());
        assertTrue(one.hasOverlappingUsePeriod(other));
	}


	// DIRECTORY MODIFICATION TIME TESTS--------------------------------------------------------------------------------
	@Test
	public void testDirectoryCanHaveAsModificationTime_LegalCase() {
		assertTrue(root.canHaveAsModificationTime(null));
		assertTrue(root.canHaveAsModificationTime(new Date()));
	}

	@Test
	public void testDirectoryCanHaveAsModificationTime_IllegalCase() {
		assertFalse(root.canHaveAsModificationTime(new Date(timeAfterConstruction.getTime() - 1000*60*60)));
		assertFalse(root.canHaveAsModificationTime(new Date(System.currentTimeMillis() + 1000*60*60)));
		}

	@Test
	public void testDirectoryHasOverlappingUsePeriod_UnmodifiedFiles() {
		// one = implicit argument ; other = explicit argument
		Directory one = new Directory(root,"one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		Directory other = new Directory(root,"other");

		//1 Test unmodified case
		assertFalse(one.hasOverlappingUsePeriod(other));

		//2 Test one unmodified case
		File file = new File(one,"file1",Type.PDF_FILE);
		assertFalse(one.hasOverlappingUsePeriod(other));

		//3 Test other unmodified case
		//so re-initialise the other file by first terminating existing other file and then recreating that file
		other.deleteRecursiveRaw();
		other = new Directory(root, "other");
		File file2 = new File(one,"file2",Type.PDF_FILE);
		assertFalse(one.hasOverlappingUsePeriod(other));

	}

	@Test
	public void testDirectoryHasOverlappingUsePeriod_ModifiedNoOverlap() {
		// one = implicit argument ; other = explicit argument
		Directory one, other;
		one = new Directory(root,"one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new Directory(root,"other");

		//1 Test one created and modified before other created and modified case
		File file = new File(one,"file1",Type.PDF_FILE);
		sleep();
		//re-initialise the other
		other.deleteRecursive();
		other = new Directory(root,"other");
		File file2 = new File(other,"file2",Type.PDF_FILE);
		assertFalse(one.hasOverlappingUsePeriod(other));

		//2 Test other created and modified before one created and modified
		File file3 = new File(other,"file3",Type.PDF_FILE);
		sleep();
		one.deleteRecursive();
		one = new Directory(root,"one");
		File file4 = new File(root,"file4",Type.PDF_FILE);
		assertFalse(one.hasOverlappingUsePeriod(other));

	}

	@Test
	public void testDirectoryHasOverlappingUsePeriod_ModifiedOverlap_A() {
		// one = implicit argument ; other = explicit argument
		//A Test one created before other created before one modified before other modified
		Directory one, other;
		one = new Directory(root,"one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new Directory(root,"other");

		File file = new File(one,"file1",Type.PDF_FILE);
		sleep();
		File file2 = new File(other,"file2",Type.PDF_FILE);
		assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testDirectoryHasOverlappingUsePeriod_ModifiedOverlap_B() {
		// one = implicit argument ; other = explicit argument
		//B Test one created before other created before other modified before one modified
		Directory one, other;
		one = new Directory(root,"one");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new Directory(root,"other");

		File file = new File(other,"file1",Type.PDF_FILE);
		sleep();
		File file2 = new File(one,"file2",Type.PDF_FILE);
		assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testDirectoryHasOverlappingUsePeriod_ModifiedOverlap_C() {
		// one = implicit argument ; other = explicit argument
		//C Test other created before one created before other modified before one modified
		Directory one, other;
		other = new Directory(root,"other");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new Directory(root,"one");

		File file = new File(other,"file1",Type.PDF_FILE);
		sleep();
		File file2 = new File(one,"file2",Type.PDF_FILE);
		assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testDirectoryHasOverlappingUsePeriod_ModifiedOverlap_D() {
		// one = implicit argument ; other = explicit argument
		//D Test other created before one created before one modified before other modified
		Directory one, other;
		other = new Directory(root,"other");
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new Directory(root,"one");

		File file = new File(one,"file1",Type.PDF_FILE);
		sleep();
		File file2 = new File(other,"file2",Type.PDF_FILE);
		assertTrue(one.hasOverlappingUsePeriod(other));
	}

	// LINK MODIFICATION TIME TESTS-------------------------------------------------------------------------------------
	@Test
	public void testLinkCanHaveAsModificationTime_LegalCase() {
		assertTrue(linkDirectoryStringItem.canHaveAsModificationTime(null));
		assertTrue(linkDirectoryStringItem.canHaveAsModificationTime(new Date()));
	}

	@Test
	public void testLinkCanHaveAsModificationTime_IllegalCase() {
		assertFalse(linkDirectoryStringItem.canHaveAsModificationTime(new Date(timeAfterConstruction.getTime() - 1000*60*60)));
		assertFalse(linkDirectoryStringItem.canHaveAsModificationTime(new Date(System.currentTimeMillis() + 1000*60*60)));
	}

	@Test
	public void testLinkHasOverlappingUsePeriod_UnmodifiedFiles() {
		// one = implicit argument ; other = explicit argument
		Link one = new Link(root,"one",fileDirectoryStringIntBooleanType);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		Link other = new Link(root,"other",fileDirectoryStringIntBooleanType);

		//1 Test unmodified case
		assertFalse(one.hasOverlappingUsePeriod(other));

		//2 Test one unmodified case
		one.changeName("1");
		assertFalse(one.hasOverlappingUsePeriod(other));

		//3 Test other unmodified case
		//so re-initialise the other file by first terminating existing other file and then recreating that file
		other.deleteRecursive();
		sleep();
		other = new Link(root,"other",fileDirectoryStringIntBooleanType);
		other.changeName("2");
		assertFalse(one.hasOverlappingUsePeriod(other));

	}

	@Test
	public void testLinkHasOverlappingUsePeriod_ModifiedNoOverlap() {
		// one = implicit argument ; other = explicit argument
		Link one, other;
		one = new Link(root,"one",fileDirectoryStringIntBooleanType);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new Link(root,"other",fileDirectoryStringIntBooleanType);

		//1 Test one created and modified before other created and modified case
		one.changeName("1");
		sleep();
		//re-initialise the other
		other.deleteRecursive();
		other = new Link(root,"other",fileDirectoryStringIntBooleanType);
		other.changeName("2");
		assertFalse(one.hasOverlappingUsePeriod(other));

		//2 Test other created and modified before one created and modified
		other.changeName("3");
		sleep();
		one.deleteRecursive();
		one = new Link(root,"one",fileDirectoryStringIntBooleanType);
		one.changeName("4");
		assertFalse(one.hasOverlappingUsePeriod(other));

	}

	@Test
	public void testLinkHasOverlappingUsePeriod_ModifiedOverlap_A() {
		// one = implicit argument ; other = explicit argument
		//A Test one created before other created before one modified before other modified
		Link one, other;
		one = new Link(root,"one",fileDirectoryStringIntBooleanType);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new Link(root,"other",fileDirectoryStringIntBooleanType);

		one.changeName("1");
		sleep();
		other.changeName("2");
		assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testLinkHasOverlappingUsePeriod_ModifiedOverlap_B() {
		// one = implicit argument ; other = explicit argument
		//B Test one created before other created before other modified before one modified
		Link one, other;
		one = new Link(root,"one",fileDirectoryStringIntBooleanType);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		other = new Link(root,"other",fileDirectoryStringIntBooleanType);

		other.changeName("1");
		sleep();
		one.changeName("2");
		assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testLinkHasOverlappingUsePeriod_ModifiedOverlap_C() {
		// one = implicit argument ; other = explicit argument
		//C Test other created before one created before other modified before one modified
		Link one, other;
		other = new Link(root,"other",fileDirectoryStringIntBooleanType);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new Link(root,"one",fileDirectoryStringIntBooleanType);

		other.changeName("1");
		sleep();
		one.changeName("2");
		assertTrue(one.hasOverlappingUsePeriod(other));
	}

	@Test
	public void testLinkHasOverlappingUsePeriod_ModifiedOverlap_D() {
		// one = implicit argument ; other = explicit argument
		//D Test other created before one created before one modified before other modified
		Link one, other;
		other = new Link(root,"other",fileDirectoryStringIntBooleanType);
		sleep(); // sleep() to be sure that one.getCreationTime() != other.getCreationTime()
		one = new Link(root,"one",fileDirectoryStringIntBooleanType);

		one.changeName("1");
		sleep();
		other.changeName("2");
		assertTrue(one.hasOverlappingUsePeriod(other));
	}


	// FILE PARENT DIRECTORY TESTS--------------------------------------------------------------------------------------
	@Test
	public void testFileParentDirectory(){
		assertEquals(root,fileDirectoryStringType.getParentDirectory());
		fileDirectoryStringType.changeParentDirectory(directoryStringBoolean);
		assertEquals(directoryStringBoolean,fileDirectoryStringType.getParentDirectory());
	}

	@Test (expected = ItemNotWritableException.class)
	public void testFileParentDirectory_NotWritableDirectory(){
		Directory rootNotWritable = new Directory("not_writable_root",false);
		fileDirectoryStringType.changeParentDirectory(rootNotWritable);

	}

	@Test (expected = ItemNotWritableException.class)
	public void testFileParentDirectory_NotWritableFile(){
		fileNotWritable.changeParentDirectory(directoryStringBoolean);
	}


	// DIRECTORY PARENT DIRECTORY TESTS---------------------------------------------------------------------------------
	@Test
	public void testDirectoryParentDirectory(){
		Directory newRoot = new Directory("new_root");
		assertNull(root.getParentDirectory());
		root.changeParentDirectory(newRoot);
		assertEquals(newRoot,root.getParentDirectory());

		assertEquals(root,directoryDirectoryString.getParentDirectory());
		directoryDirectoryString.changeParentDirectory(newRoot);
		assertEquals(newRoot,directoryDirectoryString.getParentDirectory());
	}

	@Test (expected = ItemNotWritableException.class)
	public void testDirectoryParentDirectory_NotWritableRoot(){
		Directory rootNotWritable = new Directory("not_writable_root",false);
		rootNotWritable.changeParentDirectory(root);
	}

	@Test (expected = ItemNotWritableException.class)
	public void testDirectoryParentDirectory_NotWritableDirectory(){
		Directory newRoot = new Directory("new_root");
		directoryNotWritable.changeParentDirectory(newRoot);
	}


	// LINK PARENT DIRECTORY TESTS--------------------------------------------------------------------------------------
	@Test
	public void testLinkParentDirectory(){
		assertEquals(root,linkDirectoryStringItem.getParentDirectory());
		linkDirectoryStringItem.changeParentDirectory(directoryStringBoolean);
		assertEquals(directoryStringBoolean,linkDirectoryStringItem.getParentDirectory());
	}


	// FILE WRITABLE TESTS----------------------------------------------------------------------------------------------
	@Test
	public void testFileSetWritable() {
		fileDirectoryStringType.setWritable(false);
		fileNotWritable.setWritable(true);
		assertFalse(fileDirectoryStringType.isWritable());
		assertTrue(fileNotWritable.isWritable());
	}


	// DIRECTORY WRITABLE TESTS-----------------------------------------------------------------------------------------
	@Test
	public void testDirectorySetWritable() {
		directoryDirectoryString.setWritable(false);
		directoryNotWritable.setWritable(true);
		assertFalse(directoryDirectoryString.isWritable());
		assertTrue(directoryNotWritable.isWritable());
	}


	// FILE TYPE TEST---------------------------------------------------------------------------------------------------
	@Test
	public void testFileType(){
		File pdfFile = new File(root,"Pdf_file",Type.PDF_FILE);
		File javaFile = new File(root,"Java_file",Type.JAVA_FILE);
		File textFile = new File(root,"Text_file",Type.TEXT_FILE);
		assertEquals(Type.PDF_FILE,pdfFile.getType());
		assertEquals(Type.JAVA_FILE,javaFile.getType());
		assertEquals(Type.TEXT_FILE,textFile.getType());
	}


	// DIRECTORY CONTENTS TESTS-----------------------------------------------------------------------------------------
	@Test
	public void testDirectoryGetNbItem(){
		Directory root = new Directory("root");
		assertEquals(0,root.getNbItems());
		File file = new File(root,"file",Type.PDF_FILE);
		assertEquals(1, root.getNbItems());
		Link link = new Link(root,"link",file);
		assertEquals(2,root.getNbItems());
	}

	@Test
	public void testDirectoryGetItemAt(){
		Directory root = new Directory("root");
		File file = new File(root,"file",Type.PDF_FILE);
		Link link = new Link(root,"link",file);
		assertEquals(file,root.getItemAt(1));
		assertEquals(link,root.getItemAt(2));
	}

	@Test
	public void testDirectoryGetItem(){
		Directory root = new Directory("root");
		File file = new File(root,"file",Type.PDF_FILE);
		Link link = new Link(root,"link",file);
		assertEquals(file,root.getItem("file"));
		assertEquals(link,root.getItem("link"));
	}

	@Test
	public void testDirectoryContainsDiskItemWhitName(){
		Directory root = new Directory("root");
		File file = new File(root,"file",Type.PDF_FILE);
		assertTrue(root.containsDiskItemWithName("file"));
		assertFalse(root.containsDiskItemWithName("link"));
	}

	@Test
	public void testDirectoryGetIndexOf(){
		Directory root = new Directory("root");
		File file = new File(root,"file",Type.PDF_FILE);
		Link link = new Link(root,"link",file);
		assertEquals(0,root.getIndexOf(file));
		assertEquals(1,root.getIndexOf(link));
	}

	@Test
	public void testDirectoryHasAsItem(){
		Directory root = new Directory("root");
		File file = new File(root,"file",Type.PDF_FILE);
		assertTrue(root.hasAsItem(file));
		assertFalse(root.hasAsItem(linkDirectoryStringItem));
	}


	// LINK LINKED ITEM TESTS-------------------------------------------------------------------------------------------
	@Test
	public void testLinkLinkedItem(){
		assertEquals(fileDirectoryStringIntBooleanType,linkDirectoryStringItem.getLinkedItem());
	}


	// SLEEP METHOD
	private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
