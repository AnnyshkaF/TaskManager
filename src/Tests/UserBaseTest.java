package Tests;

import Model.UserBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBaseTest {

    @Test
    void addUser() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addUser("Hanna");
        assertEquals(2, userBase.getUsersNames().length);
    }

    @Test
    void addCalendar() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addCalendar("Anna", "cal1");
        userBase.addCalendar("Anna", "cal2");
        assertEquals(2, userBase.getUsersFiles("Anna").length);
    }

    @Test
    void deleteUser() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addUser("Hanna");
        userBase.deleteUser("Hanna");
        assertEquals(1, userBase.getUsersNames().length);
    }

    @Test
    void deleteCalendar() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addCalendar("Anna", "cal1");
        userBase.deleteCalendar("Anna", "cal2");
        assertEquals(1, userBase.getUsersFiles("Anna").length);
    }

    @Test
    void getUsersNames() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addUser("Hanna");
        String[] names = {"Anna", "Hanna"};
        assertArrayEquals(names, userBase.getUsersNames());
    }

    @Test
    void getUsersFiles() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addCalendar("Anna", "cal1");
        userBase.addCalendar("Anna", "cal2");
        String[] files = {"cal1", "cal2"};
        assertArrayEquals(files, userBase.getUsersFiles("Anna"));
    }

    @Test
    void getUserBase() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        assertNotNull(userBase.getUserBase());
    }

    @Test
    void editNameFolder() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.editNameFolder("Anna", "Hanna");
        assertEquals("Hanna", userBase.getUsersNames()[0]);
    }

    @Test
    void editCalendarFile() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addCalendar("Anna", "cal1");
        userBase.editCalendarFile("Anna", "cal1", "cal2");
        assertEquals("cal2", userBase.getUsersFiles("Anna")[0]);
    }
}