package IO;

import Model.UserBase;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class UserBaseIO {
    final String saveFolder = "saves/";


    public void loadUsersFiles(UserBase usersBase) {
        File folder = new File(saveFolder);
        File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                File tmpFolder = new File(saveFolder + files[i].getName());
                usersBase.addUser(files[i].getName());
                for (int j = 0; j < Objects.requireNonNull(tmpFolder.listFiles()).length; j++) {
                    usersBase.addCalendar(files[i].getName(), Objects.requireNonNull(tmpFolder.listFiles())[j].getName());
                }
            }
        }
    }

    public String getFilePath(String name, String filename){
            return saveFolder + name + "/" + filename;
    }

    public void createNameFolder(String name) {
        File folder = new File(saveFolder + name);
        if (!folder.exists()) {
            folder.mkdir();
        } else {
            throw new RuntimeException("User " + name + " folder already exists");
        }
    }

    public void deleteNameFolder(String name) {
        File folder = new File(saveFolder + name);
        if (folder.exists()) {
            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        }
        folder.delete();
    }

    public void deleteCalendarFile(String name, String filename){
        File folder = new File(saveFolder + name + "/" + filename);
        folder.delete();
    }

    public void editNameFolder(String oldName, String newName){
        File folder = new File(saveFolder + oldName);
        folder.renameTo(new File(saveFolder + newName));
    }

    public void editCalendarFile(String name, String oldFileName, String newFileName){
        File folder = new File(saveFolder + name + "/" + oldFileName);
        folder.renameTo(new File(saveFolder + name + "/" + newFileName));
    }
}