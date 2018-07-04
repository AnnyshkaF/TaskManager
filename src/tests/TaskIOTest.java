package tests;

import io.TaskIO;
import junit.framework.TestCase;
import model.calendar.Date;
import model.task.Task;
import model.task.TaskMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TaskIOTest extends TestCase{
    @Test
    public void testLoadTasksFromFile(){
        TaskMap taskMap = new TaskMap();
        TaskIO taskIO = new TaskIO();
        try {
            taskIO.loadTasksFromFile("C:/Users/Anna/Desktop/test/readTest.xml", taskMap);/*readTest45.xml*/
        } catch (Exception e) {
            e.printStackTrace();
            fail("Loading error");
        }
    }

    @Test
    public void testSaveTasksToFile(){
        TaskMap taskMap1 = new TaskMap();
        TaskIO taskIO = new TaskIO();
        for (int i = 1; i < 11; i++) {
            String str = String.valueOf(i);
            taskMap1.addTask(
                    new Date(i, 1, 2018),
                    new Task(str, str, str, str, true));
        }
        try {
            taskIO.saveTasksToFile("C:/Users/Anna/Desktop/test/1.xml", taskMap1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Saving error");
        }

    }
}