package tests;

import junit.framework.TestCase;
import model.calendar.Date;
import model.task.DateComparator;
import org.junit.Test;

public class DateComparatorTest extends TestCase {

    @Test
    public void testCompare() {
        DateComparator d = new DateComparator();
        assertEquals(1, d.compare(new Date(3, 1, 2018), new Date(1, 1, 2018)));
    }
}