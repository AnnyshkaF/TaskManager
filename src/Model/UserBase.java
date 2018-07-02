package Model;

import IO.UserBaseIO;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class UserBase {
    private TreeMap<String, ArrayList<String>> userBase = new TreeMap<>();

    public boolean addUser(String name) {
        if (userBase.containsKey(name)) {
            return false;
        }
        userBase.put(name, new ArrayList<>());
        return true;
    }

    public void addCalendar(String name, String calendarName) {
        if (this.userBase.containsKey(name)) {
            userBase.get(name).add(calendarName);
        } else {
            userBase.put(name, new ArrayList<>());
            userBase.get(name).add(calendarName);
        }
    }

    public void deleteUser(String name) {
        if (userBase.containsKey(name)) {
            userBase.remove(name);
        }
    }

    public void deleteCalendar(String name, String calendarName) {
        if (userBase.containsKey(name)) {
            for (int i = 0; i < userBase.get(name).size(); i++) {
                if (userBase.get(name).get(i).equals(calendarName)) {
                    userBase.get(name).remove(i);
                    return;
                }
            }
        }
    }

    public String[] getUsersNames() {
        if (userBase.size() > 0) {
            String[] names = new String[userBase.size()];
            int i = 0;
            for (Map.Entry<String, ArrayList<String>> entry : userBase.entrySet()) {
                names[i++] = entry.getKey();
            }
            return names;
        }
        return null;
    }

    public String[] getUsersFiles(String name) {
        if (userBase.containsKey(name)) {
            if (userBase.get(name).size() > 0) {
                String[] files = new String[userBase.get(name).size()];
                for (int i = 0; i < userBase.get(name).size(); i++) {
                    files[i] = userBase.get(name).get(i);
                }
                return files;
            }
        }
        return null;
    }

    public TreeMap<String, ArrayList<String>> getUserBase() {
        return userBase;
    }

    public void editNameFolder(String oldName, String newName) {
        ArrayList<String> tmpArray = userBase.remove(oldName);
        addUser(newName);
        userBase.put(newName, tmpArray);
        new UserBaseIO().editNameFolder(oldName, newName);
    }

    public void editCalendarFile(String name, String oldFileName, String newFileName) {
        userBase.get(name).remove(oldFileName);
        userBase.get(name).add(newFileName);
        new UserBaseIO().editCalendarFile(name, oldFileName, newFileName);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String,  ArrayList<String>> entry : userBase.entrySet()) {
            str.append(entry.getKey());
            str.append("\n");
            for (int i = 0; i < entry.getValue().size(); i++) {
                str.append("calendar: " + entry.getValue().get(i));
                str.append("\n");
            }
            str.append("\n\n");
        }
        return str.toString();
    }
}
