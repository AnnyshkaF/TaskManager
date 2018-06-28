package Tests;

import Model.Calendar.Date;
import Model.Task.DateComparator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateComparatorTest {

    @Test
    void comparePositive() {
        DateComparator d = new DateComparator();
        assertEquals(1, d.compare(new Date(3, 1, 2018), new Date(1, 1, 2018)));
    }
    @Test
    void compareNegative() {
        DateComparator d = new DateComparator();
        assertEquals(0, d.compare(new Date(3, 1, 2018), new Date(5, 1, 2018)));
    }
}