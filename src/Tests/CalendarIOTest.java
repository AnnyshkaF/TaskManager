package tests;

import io.CalendarIO;
import junit.framework.TestCase;
import model.calendar.Calendar;
import org.junit.Test;

public class CalendarIOTest extends TestCase {
    @Test
    public void testLoadCalendarFromFile(){
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/test/months.xml", calendar);
        } catch (Exception e) {
            //e.printStackTrace();
            fail("Loading error");
        }
    }
}