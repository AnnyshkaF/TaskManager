import GUI.Calendar.CalendarWindow;
import IO.TaskIO;
import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;

public class Main {
    public static void main(String[] args) throws Exception{
        String fileCalendar = "C:/Users/Anna/Desktop/months.xml";
        String fileTask = "C:/Users/Anna/Desktop/tasksSave.xml";

        TaskMap taskMap = new TaskMap();
        TaskIO taskIO = new TaskIO();
        taskIO.loadTasksFromFile(fileTask, taskMap);

        CalendarWindow calendarWindow = new CalendarWindow(taskMap, fileCalendar, fileTask);

    }
}
