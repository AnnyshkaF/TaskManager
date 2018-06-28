package Tests;

import IO.CalendarIO;
import Model.Calendar.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class CalendarIOTest {

    @Before
    public void before(){
        System.out.println("Test started");
    }

    @After
    public void after(){
        System.out.println("Test finished");
    }


    @Test
    public void loadCalendarFromFile(){
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/test/months.xml", calendar);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ErrorCalendarLoading");
            fail("ErrorCalendarLoading");
        }
    }
}