package Tests;

import IO.CalendarIO;
import Model.Calendar.Calendar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {

    @Test
    public void getLoadedMonthArraySize() {
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/LoginWindow/months.xml", calendar);
        } catch (Exception e) {
            fail("ErrorTaskLoading");
        }
        assertEquals(calendar.getLoadedMonthArraySize(), 14);
    }

    @Test
    public void getLoadedMonth() {
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/LoginWindow/monthsNull.xml", calendar);
            assertNull(calendar.getLoadedMonth(12, 2018));
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ErrorTaskLoading");
            fail("ErrorTaskLoading");
        }
    }
    @Test
    public void getYears() {
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/LoginWindow/months.xml", calendar);
        } catch (Exception e) {
            fail("ErrorTaskLoading");
        }
        int[] a = {2017, 2018, 2019};
        int b[] = calendar.getYears();
        assertArrayEquals(a, b);
    }
}