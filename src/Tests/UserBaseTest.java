package tests;

import junit.framework.TestCase;
import model.userbase.UserBase;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserBaseTest extends TestCase {
    @Test
    public void testAddUser() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addUser("Hanna");
        Assert.assertEquals(2, userBase.getUsersNames().length);
    }

    @Test
    public void testAddCalendar() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addCalendar("Anna", "cal1");
        userBase.addCalendar("Anna", "cal2");
        Assert.assertEquals(2, userBase.getUsersCalendars("Anna").length);
    }

    @Test
    public void testDeleteUser() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addUser("Hanna");
        userBase.deleteUser("Hanna");
        Assert.assertEquals(1, userBase.getUsersNames().length);
    }

    @Test
    public void testDeleteCalendar() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addCalendar("Anna", "cal1");
        userBase.deleteCalendar("Anna", "cal2");
        Assert.assertEquals(1, userBase.getUsersCalendars("Anna").length);
    }

    @Test
    public void testGetUsersNames() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addUser("Hanna");
        String[] names = {"Anna", "Hanna"};
        assertArrayEquals(names, userBase.getUsersNames());
    }

    @Test
    public void testGetUsersFiles() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addCalendar("Anna", "cal1");
        userBase.addCalendar("Anna", "cal2");
        String[] files = {"cal1", "cal2"};
        assertArrayEquals(files, userBase.getUsersCalendars("Anna"));
    }

    @Test
    public void testGetUserBase() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        Assert.assertNotNull(userBase.getUserBase());
    }

    @Test
    public void testEditNameFolder() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.editUserName("Anna", "Hanna");
        Assert.assertEquals("Hanna", userBase.getUsersNames()[0]);
    }

    @Test
    public void testEditCalendarFile() {
        UserBase userBase = new UserBase();
        userBase.addUser("Anna");
        userBase.addCalendar("Anna", "cal1");
        userBase.editCalendaName("Anna", "cal1", "cal2");
        Assert.assertEquals("cal2", userBase.getUsersCalendars("Anna")[0]);
    }
}