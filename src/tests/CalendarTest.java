package tests;

import io.CalendarIO;
import junit.framework.TestCase;
import model.calendar.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalendarTest extends TestCase {
    @Test
    public void testGetLoadedMonthArraySize() {
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/test/months.xml", calendar);
        } catch (Exception e) {
            fail("ErrorTaskLoading");
        }
        assertEquals(calendar.getLoadedMonthArraySize(), 14);
    }

    @Test
    public void testGetLoadedMonth() {
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/test/monthsNull.xml", calendar);
            assertNull(calendar.getLoadedMonth(12, 2018));
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ErrorTaskLoading");
            fail("ErrorTaskLoading");
        }
    }
    @Test
    public void testGetYears() {
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/test/months.xml", calendar);
        } catch (Exception e) {
            fail("ErrorTaskLoading");
        }
        int[] a = {2017, 2018, 2019};
        int b[] = calendar.getYears();
        assertArrayEquals(a, b);
    }
}