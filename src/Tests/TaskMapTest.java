package Tests;

import IO.CalendarIO;
import Model.Calendar.Calendar;
import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;

import org.junit.Test;
import static org.junit.Assert.*;


public class TaskMapTest {

    @Test
    public void addTask() {
        TaskMap taskMap = new TaskMap();
        for (int i = 1; i < 11; i++) {
            String s = String.valueOf(i);
            taskMap.addTask(new Date(i, 1, 2018),
                    new Task(s, s, s, s, false));
            taskMap.addTask(new Date(i + 4, 2, 2018),
                    new Task(s, s, s, s, true));
        }
        assertEquals(20, taskMap.getTaskMap().size());
    }

    @Test
    public void deleteTask() {
        TaskMap taskMap = new TaskMap();
        for (int i = 1; i < 11; i++) {
            String s = String.valueOf(i);
            taskMap.addTask(new Date(i, 1, 2018),
                    new Task(s, s, s, s, false));
            taskMap.addTask(new Date(i + 4, 2, 2018),
                    new Task(s, s, s, s, true));
        }
        for (int i = 1; i < 6; i++) {
            taskMap.deleteTask(new Date(i, 1, 2018), 0);
            taskMap.deleteTask(new Date(i + 4, 2, 2018), 0);
        }
        assertEquals(10, taskMap.getTaskMap().size());
    }

    @Test
    public void calculateTotalTasksPerDay() {
        TaskMap taskMap = new TaskMap();
        for (int i = 1; i < 11; i++) {
            String s = String.valueOf(i);
            taskMap.addTask(new Date(1, 1, 2018),
                    new Task(s, s, s, s, true));
        }
        assertEquals(10, taskMap.calculateTotalTasksPerDay(new Date(1,1,2018), true));
    }

    @Test
    public void calculateTotalTasksPerWeek() {
        Calendar cal = new Calendar();
        new CalendarIO().loadCalendarFromFile("C:/Users/Anna/Desktop/months.xml", cal);
        TaskMap taskMap = new TaskMap();
        for (int i = 1; i < 11; i++) {
            String s = String.valueOf(i);
            taskMap.addTask(new Date(i, 1, 2018),
                    new Task(s, s, s, s, true));
        }
        assertEquals(7,
                taskMap.calculateTotalTasksPerWeek(cal, new Date(1, 1, 2018),true, 1/*8*/).getTotal());
    }

    @Test
    public void calculateTotalTasksPerMonth() {
        TaskMap taskMap = new TaskMap();
        for (int i = 1; i < 11; i++) {
            String s = String.valueOf(i);
            taskMap.addTask(new Date(i, 1, 2018),
                    new Task(s, s, s, s, false));
        }
        assertEquals(10, taskMap.calculateTotalTasksPerMonth(new Date(1, 1, 2018), false));
    }
}