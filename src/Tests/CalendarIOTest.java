package Tests;

import IO.CalendarIO;
import Model.Calendar.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CalendarIOTest {

    @Before
    public void before(){
        System.out.println("Test started");
    }

    @After
    public void after(){
        System.out.println("Test finished");
    }


    @Test(expected = Exception.class)
    public void loadCalendarFromFile(){
        Calendar calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        try {
            calendarIO.loadCalendarFromFile("C:/Users/Anna/Desktop/test/month.xml", calendar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}