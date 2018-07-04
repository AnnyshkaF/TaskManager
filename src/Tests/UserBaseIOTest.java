package tests;

import io.UserBaseIO;
import junit.framework.TestCase;
import model.userbase.UserBase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserBaseIOTest extends TestCase {

    @Test
    public void testLoadUsersFiles() {
        UserBase userBase = new UserBase();
        new UserBaseIO().loadUsersFiles(userBase);
        assertEquals(4, userBase.getUsersNames().length);
    }

    @Test
    public void testGetFilePath() {
        File folder = new File("tst/");
        assertEquals("tst", folder.getPath());
    }

    @Test
    public void testCreateNameFolder() {
        String foldername = "nameCreate";
        File folder = new File("tst/" + foldername);
        folder.mkdir();
        assertTrue(new File("tst/" + foldername).exists());
    }

    @Test
    public void testDeleteNameFolder() {
        String foldername = "nameDelete";
        File folder = new File("tst/" + foldername);
        folder.mkdir();
        folder.delete();
        assertFalse(new File("tst/" + foldername).exists());
    }

    @Test
    public void testCreateCalendarFile() {
        String filename = "1.xml";
        File folder = new File("tst/" + filename);
        try {
            folder.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(new File("tst/" + filename).exists());
    }

    @Test
    public void testDeleteCalendarFile() {
        String filename = "2.xml";
        File folder = new File("tst/" + filename);
        try {
            folder.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        folder.delete();
        assertFalse(new File("tst/" + filename).exists());
    }

    //renaming after end of the program
    @Test
    public void testEditNameFolder() {
        String oldFolder = "oldFolder";
        String newFolder = "newFolder";
        File folder = new File("tst/" + oldFolder);
        folder.mkdir();
        folder.renameTo(new File("tst/" + newFolder));
        assertEquals("tst/" + newFolder, folder.toString());
    }

    @Test
    public void testEditCalendarFile() {
        String oldFile = "oldFile.xml";
        String newFile = "newFile.xml";
        File folder = new File("tst/" + oldFile);
        try {
            folder.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }        folder.renameTo(new File("tst/" + newFile));

        assertEquals("tst/" + newFile, folder.toString());
    }
}