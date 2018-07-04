package tests;

import junit.textui.TestRunner;

public class MainTest {
    public static void main(String[] args){

        TestRunner.run(CalendarIOTest.class);
        TestRunner.run(CalendarTest.class);
        TestRunner.run(DateComparatorTest.class);
        TestRunner.run(TaskIOTest.class);
        TestRunner.run(TaskMapTest.class);
        TestRunner.run(UserBaseIOTest.class);
        TestRunner.run(UserBaseTest.class);
    }

}
