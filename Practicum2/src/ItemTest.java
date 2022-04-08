import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;

import java.beans.JavaBean;
import java.security.DigestException;
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
		assertEquals(0,root.getNbItems());
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
		assertEquals(0,root.getNbItems());
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
		fileDirectoryStringType.move(directoryStringBoolean);
		assertEquals(directoryStringBoolean,fileDirectoryStringType.getParentDirectory());
	}

	@Test (expected = ItemNotWritableException.class)
	public void testFileParentDirectory_NotWritableDirectory(){
		Directory rootNotWritable = new Directory("not_writable_root",false);
		fileDirectoryStringType.move(rootNotWritable);
	}


	// DIRECTORY PARENT DIRECTORY TESTS---------------------------------------------------------------------------------
	@Test
	public void testDirectoryParentDirectory(){
		Directory newRoot = new Directory("new_root");
		assertNull(root.getParentDirectory());
		root.move(newRoot);
		assertEquals(newRoot,root.getParentDirectory());

		assertEquals(root,directoryDirectoryString.getParentDirectory());
		directoryDirectoryString.move(newRoot);
		assertEquals(newRoot,directoryDirectoryString.getParentDirectory());
	}

	// LINK PARENT DIRECTORY TESTS--------------------------------------------------------------------------------------
	@Test
	public void testLinkParentDirectory(){
		assertEquals(root,linkDirectoryStringItem.getParentDirectory());
		linkDirectoryStringItem.move(directoryStringBoolean);
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


	// LINK LINKED ITEM TEST--------------------------------------------------------------------------------------------
	@Test
	public void testLinkLinkedItem(){
		assertEquals(fileDirectoryStringIntBooleanType,linkDirectoryStringItem.getLinkedItem());
	}


	// DIRECTORY MAKE ROOT TESTS----------------------------------------------------------------------------------------
	@Test
	public void testDirectoryMakeRoot_WritableRootCase(){
		Directory writable = new Directory(root,"writable_directory",true);
		assertEquals(root,writable.getParentDirectory());
		assertTrue(root.containsDiskItemWithName("writable_directory"));
		writable.makeRoot();
		assertNull(writable.getParentDirectory());
		assertFalse(root.containsDiskItemWithName("writable_directory"));
	}

	@Test (expected = ItemNotWritableException.class)
	public void testDirectoryMakeRoot_NotWritableRootCase(){
		Directory root = new Directory("root",false);
		Directory writable = new Directory(root,"writable_directory",true);
		assertEquals(root,writable.getParentDirectory());
		assertTrue(root.containsDiskItemWithName("writable_directory"));
		writable.makeRoot();
	}


	// FILE MOVE TESTS--------------------------------------------------------------------------------------------------
	@Test
	public void testFileMove_WritableRoots(){
		Directory root1 = new Directory("root1");
		Directory root2 = new Directory("root2");
		File file = new File(root1,"file",Type.TEXT_FILE);
		assertEquals(root1, file.getParentDirectory());
		file.move(root2);
		assertEquals(root2,file.getParentDirectory());
	}

	@Test (expected = ItemNotWritableException.class)
	public void testFileMove_NotWritableRoot1(){
		Directory root1 = new Directory("root1",false);
		Directory root2 = new Directory("root2");
		File file = new File(root1,"file",Type.TEXT_FILE);
	}

	@Test (expected = ItemNotWritableException.class)
	public void testFileMove_NotWritableRoot2(){
		Directory root1 = new Directory("root1",false);
		Directory root2 = new Directory("root2");
		File file = new File(root1,"file",Type.TEXT_FILE);
		assertEquals(root1, file.getParentDirectory());
		file.move(root2);
	}

	// DIRECTORY MOVE TESTS---------------------------------------------------------------------------------------------
	@Test
	public void testDirectoryMove_WritableRoots(){
		Directory root1 = new Directory("root1");
		Directory root2 = new Directory("root2");
		Directory dir = new Directory(root1,"dir");
		assertEquals(root1, dir.getParentDirectory());
		dir.move(root2);
		assertEquals(root2,dir.getParentDirectory());
	}

	@Test (expected = ItemNotWritableException.class)
	public void testDirectoryMove_NotWritableRoot1(){
		Directory root1 = new Directory("root1",false);
		Directory root2 = new Directory("root2");
		Directory dir = new Directory(root1,"dir");
	}

	@Test (expected = ItemNotWritableException.class)
	public void testDirectoryMove_NotWritableRoot2(){
		Directory root1 = new Directory("root1",false);
		Directory root2 = new Directory("root2");
		Directory dir = new Directory(root1,"dir");
		assertEquals(root1, dir.getParentDirectory());
		dir.move(root2);
	}

	// LINK MOVE TESTS--------------------------------------------------------------------------------------------------
	@Test
	public void testLinkMove_WritableRoots(){
		Directory root1 = new Directory("root1");
		Directory root2 = new Directory("root2");
		Link link = new Link(root1,"link",fileDirectoryStringIntBooleanType);
		assertEquals(root1, link.getParentDirectory());
		link.move(root2);
		assertEquals(root2,link.getParentDirectory());
	}

	@Test (expected = ItemNotWritableException.class)
	public void testLinkMove_NotWritableRoot1(){
		Directory root1 = new Directory("root1",false);
		Directory root2 = new Directory("root2");
		Link link = new Link(root1,"link",fileDirectoryStringIntBooleanType);
	}

	@Test (expected = ItemNotWritableException.class)
	public void testLinkMove_NotWritableRoot2(){
		Directory root1 = new Directory("root1",false);
		Directory root2 = new Directory("root2");
		Link link = new Link(root1,"link",fileDirectoryStringIntBooleanType);
		assertEquals(root1, link.getParentDirectory());
		link.move(root2);
	}


	// FILE IS DIRECT OR INDIRECT CHILD OF TESTS------------------------------------------------------------------------
	@Test
	public void testFileIsDirectOrIndirectChildOf_directChildCase(){
		Directory root = new Directory("root",true);
		File file = new File(root,"file",Type.TEXT_FILE);
		assertTrue(file.isDirectOrIndirectChildOf(root));
	}

	@Test
	public void testFileIsDirectOrIndirectChildOf_indirectChildCase(){
		Directory root = new Directory("root",true);
		Directory dir = new Directory(root,"dir");
		File file = new File(dir,"file",Type.TEXT_FILE);
		assertTrue(file.isDirectOrIndirectChildOf(root));
	}

	@Test
	public void testFileIsDirectOrIndirectChildOf_neitherCase(){
		Directory root = new Directory("root",true);
		Directory otherRoot = new Directory("other_root");
		File file = new File(otherRoot,"file",Type.TEXT_FILE);
		assertFalse(file.isDirectOrIndirectChildOf(root));
	}


	// DIRECTORY IS DIRECT OR INDIRECT CHILD OF TESTS-------------------------------------------------------------------
	@Test
	public void testDirectoryIsDirectOrIndirectChildOf_directChildCase(){
		Directory root = new Directory("root",true);
		Directory directory = new Directory(root,"directory");
		assertTrue(directory.isDirectOrIndirectChildOf(root));
	}

	@Test
	public void testDirectoryIsDirectOrIndirectChildOf_indirectChildCase(){
		Directory root = new Directory("root",true);
		Directory dir = new Directory(root,"dir");
		Directory directory = new Directory(dir,"directory");
		assertTrue(directory.isDirectOrIndirectChildOf(root));
	}

	@Test
	public void testDirectoryIsDirectOrIndirectChildOf_neitherCase(){
		Directory root = new Directory("root",true);
		Directory otherRoot = new Directory("other_root");
		Directory directory = new Directory(otherRoot,"file");
		assertFalse(directory.isDirectOrIndirectChildOf(root));
	}


	// LINK IS DIRECT OR INDIRECT CHILD OF TESTS-------------------------------------------------------------------
	@Test
	public void testLinkIsDirectOrIndirectChildOf_directChildCase(){
		Directory root = new Directory("root",true);
		Link link = new Link(root,"directory",fileDirectoryStringIntBooleanType);
		assertTrue(link.isDirectOrIndirectChildOf(root));
	}

	@Test
	public void testLinkIsDirectOrIndirectChildOf_indirectChildCase(){
		Directory root = new Directory("root",true);
		Directory dir = new Directory(root,"dir");
		Link link = new Link(dir,"directory",fileDirectoryStringIntBooleanType);
		assertTrue(link.isDirectOrIndirectChildOf(root));
	}

	@Test
	public void testLinkIsDirectOrIndirectChildOf_neitherCase(){
		Directory root = new Directory("root",true);
		Directory otherRoot = new Directory("other_root");
		Link link = new Link(otherRoot,"directory",fileDirectoryStringIntBooleanType);
		assertFalse(link.isDirectOrIndirectChildOf(root));
	}


	// FILE GET ROOT TESTS----------------------------------------------------------------------------------------------
	@Test
	public void testFileGetRoot_directChildCase(){
		Directory root = new Directory("root",true);
		File file = new File(root,"file",Type.TEXT_FILE);
		assertEquals(root,file.getRoot());
	}

	@Test
	public void testFileGetRoot_indirectChildCase(){
		Directory root = new Directory("root",true);
		Directory dir = new Directory(root,"dir");
		File file = new File(dir,"file",Type.TEXT_FILE);
		assertEquals(root,file.getRoot());
	}


	// DIRECTORY GET ROOT TESTS----------------------------------------------------------------------------------------------
	@Test
	public void testDirectoryGetRoot_directChildCase(){
		Directory root = new Directory("root",true);
		Directory directory = new Directory(root,"directory");
		assertEquals(root,directory.getRoot());
	}

	@Test
	public void testDirectoryGetRoot_indirectChildCase(){
		Directory root = new Directory("root",true);
		Directory dir = new Directory(root,"dir");
		Directory directory = new Directory(dir,"directory");
		assertEquals(root,directory.getRoot());
	}


	// LINK GET ROOT TESTS----------------------------------------------------------------------------------------------
	@Test
	public void testLinkGetRoot_directChildCase(){
		Directory root = new Directory("root",true);
		Link link = new Link(root,"directory",fileDirectoryStringIntBooleanType);
		assertEquals(root,link.getRoot());
	}

	@Test
	public void testLinkGetRoot_indirectChildCase(){
		Directory root = new Directory("root",true);
		Directory dir = new Directory(root,"dir");
		Link link = new Link(dir,"directory",fileDirectoryStringIntBooleanType);
		assertEquals(root,link.getRoot());
	}


	// FILE GET TOTAL DISK USAGE TESTS----------------------------------------------------------------------------------
	@Test
	public void testFileGetTotalDiskUsage(){
		Directory root = new Directory("root");
		File file = new File(root,"file",100,true,Type.TEXT_FILE);
		assertEquals(file.getSize(), file.getTotalDiskUsage());
	}


	// DIRECTORY GET TOTAL DISK USAGE TESTS-----------------------------------------------------------------------------
	@Test
	public void testDirectoryGetTotalDiskUsage(){
		Directory directory = new Directory(root,"directory",true);
		File file = new File(directory,"file",100,true,Type.TEXT_FILE);
		assertEquals(file.getSize(), directory.getTotalDiskUsage());
		File file2 = new File(directory,"file2",1000,true,Type.TEXT_FILE);
		assertEquals(file.getSize() + file2.getSize(), directory.getTotalDiskUsage());
		Link link = new Link(directory,"link",file);
		assertEquals(file.getSize() + file2.getSize(), directory.getTotalDiskUsage());
	}


	// LINK GET TOTAL DISK USAGE TESTS----------------------------------------------------------------------------------
	@Test
	public void testLinkGetTotalDiskUsage(){
		Directory root = new Directory("root");
		Link link = new Link(root,"link",fileDirectoryStringIntBooleanType);
		assertEquals(0, link.getTotalDiskUsage());
	}


	// FILE GET ABSOLUTE PATH TESTS-------------------------------------------------------------------------------------
	@Test
	public void testFileGetAbsolutePath_directChildCase(){
		Directory root = new Directory("root");
		File file = new File(root,"file",Type.TEXT_FILE);
		assertEquals("/root/file.txt", file.getAbsolutePath());
	}

	@Test
	public void testFileGetAbsolutePath_indirectChildCase(){
		Directory root = new Directory("root");
		Directory dir = new Directory(root,"dir");
		File file = new File(dir,"file",Type.TEXT_FILE);
		assertEquals("/root/dir/file.txt", file.getAbsolutePath());
	}


	// DIRECTORY GET ABSOLUTE PATH TESTS--------------------------------------------------------------------------------
	@Test
	public void testDirectoryGetAbsolutePath_directChildCase(){
		Directory root = new Directory("root");
		Directory directory = new Directory(root,"directory");
		assertEquals("/root/directory", directory.getAbsolutePath());
	}

	@Test
	public void testDirectoryGetAbsolutePath_indirectChildCase(){
		Directory root = new Directory("root");
		Directory dir = new Directory(root,"dir");
		Directory directory = new Directory(dir,"directory");
		assertEquals("/root/dir/directory", directory.getAbsolutePath());
	}


	// LINK GET ABSOLUTE PATH TESTS-------------------------------------------------------------------------------------
	@Test
	public void testLinkGetAbsolutePath_directChildCase(){
		Directory root = new Directory("root");
		Link link = new Link(root,"link",fileDirectoryStringIntBooleanType);
		assertEquals("/root/link", link.getAbsolutePath());
	}

	@Test
	public void testLinkGetAbsolutePath_indirectChildCase(){
		Directory root = new Directory("root");
		Directory dir = new Directory(root,"dir");
		Link link = new Link(dir,"link",fileDirectoryStringIntBooleanType);
		assertEquals("/root/dir/link", link.getAbsolutePath());
	}


	// FILE TERMINATE TESTS---------------------------------------------------------------------------------------------
	@Test
	public void testFileTerminate_writableCase(){
		File file = new File(root, "file", Type.JAVA_FILE);
		assertFalse(file.isTerminated());
		file.terminate();
		assertTrue(file.isTerminated());
	}

	@Test
	public void testFileTerminate_notWritableCase(){
		File file = new File(root, "file",0, false, Type.JAVA_FILE);
		assertFalse(file.isTerminated());
		file.terminate();
		assertFalse(file.isTerminated());
	}


	// DIRECTORY TERMINATE TESTS----------------------------------------------------------------------------------------
	@Test
	public void testDirectoryTerminate_writableCase(){
		Directory directory = new Directory(root, "directory");
		assertFalse(directory.isTerminated());
		directory.terminate();
		assertTrue(directory.isTerminated());
	}

	@Test(expected = ItemNotWritableException.class)
	public void testDirectoryTerminate_notWritableCase(){
		Directory directory = new Directory(root, "directory",false);
		assertFalse(directory.isTerminated());
		try {
			directory.terminate();
		} catch (ItemNotWritableException e) {
			assertFalse(directory.isTerminated());
			assertSame(e.getItem(), directory);
			throw e;
		}
	}


	// LINK TERMINATE TESTS---------------------------------------------------------------------------------------------
	@Test
	public void testLinkTerminate(){
		Link link = new Link(root, "link1",fileDirectoryStringIntBooleanType);
		assertFalse(link.isTerminated());
		link.terminate();
		assertTrue(link.isTerminated());
	}


	// FILE DELETE RECURSIVE TESTS--------------------------------------------------------------------------------------
	@Test
	public void testFileDeleteRecursive_writableCase(){
		File file = new File(root, "file", Type.JAVA_FILE);
		assertFalse(file.isTerminated());
		file.deleteRecursive();
		assertTrue(file.isTerminated());
	}

	@Test
	public void testFileDeleteRecursive_notWritableCase(){
		File file = new File(root, "file",0, false, Type.JAVA_FILE);
		assertFalse(file.isTerminated());
		file.deleteRecursive();
		assertFalse(file.isTerminated());
	}


	// DIRECTORY DELETE RECURSIVE TESTS---------------------------------------------------------------------------------
	@Test
	public void testDirectoryDeleteRecursive_writableCase_emptyDirectory(){
		Directory directory = new Directory(root, "directory");
		assertFalse(directory.isTerminated());
		directory.deleteRecursive();
		assertTrue(directory.isTerminated());
	}

	@Test(expected = ItemNotWritableException.class)
	public void testDirectoryDeleteRecursive_notWritableCase_emptyDirectory(){
		Directory directory = new Directory(root, "directory",false);
		assertFalse(directory.isTerminated());
		directory.deleteRecursive();
	}

	@Test
	public void testDirectoryDeleteRecursive_writableCase_notEmptyDirectory_contentsWritable(){
		Directory directory = new Directory(root, "directory");
		File file = new File(directory,"file",Type.JAVA_FILE);
		assertFalse(directory.isTerminated());
		directory.deleteRecursive();
		assertTrue(directory.isTerminated());
		assertTrue(file.isTerminated());
	}

	@Test(expected = ItemNotWritableException.class)
	public void testDirectoryDeleteRecursive_notWritableCase_notEmptyDirectory_contentsWritable(){
		Directory directory = new Directory(root, "directory",false);
		File file = new File(root,"file",Type.JAVA_FILE);
		assertFalse(directory.isTerminated());
		try {
			directory.deleteRecursive();
		} catch (ItemNotWritableException e) {
			assertFalse(directory.isTerminated());
			assertFalse(file.isTerminated());
			throw e;
		}
	}

	@Test(expected = ItemNotWritableException.class)
	public void testDirectoryDeleteRecursive_writableCase_notEmptyDirectory_contentsNotWritable(){
		Directory directory = new Directory(root, "directory");
		File file = new File(directory,"file",10,false,Type.JAVA_FILE);
		assertFalse(directory.isTerminated());
		try {
			directory.deleteRecursive();
		} catch (ItemNotWritableException e) {
			assertFalse(directory.isTerminated());
			assertFalse(file.isTerminated());
			throw e;
		}
	}

	@Test(expected = ItemNotWritableException.class)
	public void testDirectoryDeleteRecursive_notWritableCase_notEmptyDirectory_contentsNotWritable(){
		Directory directory = new Directory(root, "directory",false);
		File file = new File(root,"file",10,false,Type.JAVA_FILE);
		assertFalse(directory.isTerminated());
		try {
			directory.deleteRecursive();
		} catch (ItemNotWritableException e) {
			assertFalse(directory.isTerminated());
			assertFalse(file.isTerminated());
			assertFalse(((Writability) e.getItem()).isWritable() && e.getItem().isDirectOrIndirectChildOf(directory));
			throw e;
		}
	}


	// LINK DELETE RECURSIVE TESTS--------------------------------------------------------------------------------------
	@Test
	public void testLinkDeleteRecursive(){
		Link link = new Link(root, "link1",fileDirectoryStringIntBooleanType);
		assertFalse(link.isTerminated());
		link.deleteRecursive();
		assertTrue(link.isTerminated());
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
