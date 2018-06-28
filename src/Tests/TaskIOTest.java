package Tests;

import IO.TaskIO;
import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TaskIOTest {
    @Before
    public void before(){
        System.out.println("Test started");
    }
    @After
    public void after(){
        System.out.println("Test finished");
    }
    @Test
    public void loadTasksFromFile() {
        TaskMap taskMap = new TaskMap();
        TaskIO taskIO = new TaskIO();
        try {
            taskIO.loadTasksFromFile("C:/Users/Anna/Desktop/test/readTest.xml", taskMap);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ErrorTaskLoading");
            fail("ErrorTaskLoading");
        }
    }

    @Test
    public void saveTasksToFile() {
        TaskMap taskMap1 = new TaskMap();
        TaskIO taskIO = new TaskIO();
        for (int i = 1; i < 11; i++) {
            String str = String.valueOf(i);
            taskMap1.addTask(
                    new Date(i, 1, 2018),
                    new Task(str, str, str, str, true));
        }
        try {
            synchronized (taskIO) {
                taskIO.saveTasksToFile("C:/Users/Anna/Desktop/test/test.xml", taskMap1);
            }
        } catch (Exception e) {
            assertEquals(e.getMessage(), "ErrorTaskSaving");
            fail("ErrorTaskSaving");
        }
    }
    public static void main(String[] args) throws Exception{
        new TaskIOTest().loadTasksFromFile();
        new TaskIOTest().saveTasksToFile();
    }
}