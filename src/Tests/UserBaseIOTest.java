package Tests;

import IO.UserBaseIO;
import Model.UserBase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserBaseIOTest {

    @Test
    public void loadUsersFiles() {
        UserBase userBase = new UserBase();
        new UserBaseIO().loadUsersFiles(userBase);
        assertEquals(4, userBase.getUsersNames().length);
    }

    @Test
    public void getFilePath() {
        File folder = new File("tst/");
        assertEquals("tst", folder.getPath());
    }

    @Test
    public void createNameFolder() {
        String foldername = "nameCreate";
        File folder = new File("tst/" + foldername);
        folder.mkdir();
        assertTrue(new File("tst/" + foldername).exists());
    }

    @Test
    public void deleteNameFolder() {
        String foldername = "nameDelete";
        File folder = new File("tst/" + foldername);
        folder.mkdir();
        folder.delete();
        assertFalse(new File("tst/" + foldername).exists());
    }

    @Test
    public void createCalendarFile() {
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
    public void deleteCalendarFile() {
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

    @Test
    public void editNameFolder() {
        String oldFolder = "oldFolder";
        String newFolder = "newFolder";
        File folder = new File("tst/" + oldFolder);
        folder.mkdir();
        folder.renameTo(new File("tst/" + newFolder));
        assertEquals("tst/" + newFolder, folder.toString());
    }

    @Test
    public void editCalendarFile() {
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