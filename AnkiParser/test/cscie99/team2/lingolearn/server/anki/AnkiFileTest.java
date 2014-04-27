package cscie99.team2.lingolearn.server.anki;

import java.io.File;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cscie99.team2.lingolearn.server.anki.error.AnkiException;

@RunWith(JUnit4.class)
public class AnkiFileTest extends TestCase {

	private String randomString = "abcdefghijklmnopqrstuvwxyz1234567890";
	
	private String fakeFilename;
	private String tmpFilename;
	private File tmpFile;
	private Random randomGen;
	
	@Before
	public void setUp(){
		randomGen = new Random();
		generateFiles();
	}
	
	@After
	public void tearDown(){
		tmpFile.delete();
	}
	
	private void generateFiles(){
		int randomInt = (int)( Math.random() * (30 - 1) + 1);
		tmpFilename = String.valueOf(randomInt) + ".txt";
		fakeFilename = String.valueOf(randomInt) + ".noexist.txt";
		
		tmpFile = new File(tmpFilename);
		
	}
	
	/**
	 * Test the case that a file that doesn't exist is passed to the anki file
	 * class as its Anki input file. This should throw an AnkiException
	 * @throws AnkiException
	 */
	@Test(expected=AnkiException.class)
	public void ankiFile_fileNotExist_throwsException()
	throws AnkiException
	{
		AnkiFile ankiFile = new AnkiFile(fakeFilename);
	}
	
	/**
	 * Test the case that a file that is not an Anki file is passed
	 * to the AnkiFile class as its Anki input File.  This should throw
	 * an AnkiException
	 */
	@Test(expected=AnkiException.class)
	public void ankiFile_FileNotAnki_throwsException()
	throws AnkiException
	{
		AnkiFile ankiFile = new AnkiFile(tmpFilename);
	}
}
