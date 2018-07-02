import GUI.Calendar.CalendarWindow;
import GUI.Login.LoginWindow;
import IO.TaskIO;
import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;
import Model.UserBase;

public class Main {
    /*
    public static void main(String[] args) throws Exception{
        String fileCalendar = "C:/Users/Anna/Desktop/months.xml";
        String fileTask = "C:/Users/Anna/Desktop/tasksSave.xml";

        TaskMap taskMap = new TaskMap();
        TaskIO taskIO = new TaskIO();
        taskIO.loadTasksFromFile(fileTask, taskMap);

        CalendarWindow calendarWindow = new CalendarWindow(taskMap, fileCalendar, fileTask);
    }
*/
    public static void main(String[] args) throws Exception{
        UserBase userBase = new UserBase();
        LoginWindow loginWindow = new LoginWindow(userBase);

    }
}
