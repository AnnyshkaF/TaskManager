package Tests;
/*
import IO.CalendarIO;
import IO.TaskIO;
import Model.Calendar.Calendar;
import Model.Calendar.CurrentMonth;
import Model.Calendar.Date;
import Model.Calendar.LoadedMonth;
import Model.Task.DateComparator;
import Model.Task.Task;
import Model.Task.TaskMap;

class Test {
    boolean Calendar_getMonth(String filename, int month, int year) {
        Calendar cal = new Calendar();
        LoadedMonth l = cal.getLoadedMonth(month, year);
        if (l == null) {
            return false;
        }
        return true;
    }

    boolean CurrentMonth_checkDate() {
        CurrentMonth cm = new CurrentMonth();
        if (cm.getDay() == java.time.MonthDay.now().getDayOfMonth() &&
                cm.getMonth() == java.time.MonthDay.now().getMonth().getValue() &&
                cm.getYear() == java.time.Year.now().getValue()) {
            return true;
        } else {
            return false;
        }
    }

    boolean DateComparator_FirstIfMore(Date date1, Date date2) {
        DateComparator d = new DateComparator();
        if (d.compare(date1, date2) == 1) {
            return true;
        } else {
            return false;
        }
    }

    boolean TaskMap_Output(String filename, int numOfTasksToAdd) {   //num = 10 to get TRUE
        TaskMap taskMapArray = new TaskMap();
        for (int i = 1; i < numOfTasksToAdd + 1; i++) {
            String s = String.valueOf(i);
            taskMapArray.addTask(new Date(i, 1, 2018),
                    new Task(s, s, s, s, false));
            taskMapArray.addTask(new Date(i + 4, 2, 2018),
                    new Task(s, s, s, s, true));
        }
        if(taskMapArray.calculateTotalTasksPerMonth(new Date(1, 1, 2018), false) == 10 &&
                taskMapArray.calculateTotalTasksPerMonth(new Date(1, 2, 2018), true) == 10){
            new TaskIO().saveTasksToFile(filename, taskMapArray);
            return true;
        }
        new TaskIO().saveTasksToFile(filename, taskMapArray);
        return false;
    }
    boolean TaskMap_Input(String fileTask, String fileCal, int numOfTasksToCompare) throws Exception{
        TaskMap taskMapArray = new TaskMap();
        new TaskIO().loadTasksFromFile(fileTask, taskMapArray);

        Calendar cal = new Calendar();

        new CalendarIO().loadCalendarFromFile(fileCal, cal);
        int a = 0;
        int x1 = taskMapArray.calculateTotalTasksPerMonth(new Date(1, 1, 2018), false);
        int x2  = taskMapArray.calculateTotalTasksPerMonth(new Date(1, 2, 2018), true);
        if(x1 == 10 && x2 == 10) {
            a++;
        }
        x1 = taskMapArray.calculateTotalTasksPerWeek(cal, new Date(1,1,2018), false,1).total;
        x2 = taskMapArray.calculateTotalTasksPerWeek(cal, new Date(1,2,2018), true,5).total;
        if(x1 == x2){
            a++;
        }

        x1 = taskMapArray.calculateTotalTasksPerDay(new Date(1,1,2018), false);
        x2 = taskMapArray.calculateTotalTasksPerDay(new Date(5,2,2018), true);
        if(x1 == x2) {
            a++;
        }
        if(a == 3){
            return true;
        }
        return false;
    }
}

public class TestsMain {
    public static void main(String[] args) throws Exception{
        Test test = new Test();
        System.out.println("Find Month in Base: " + test.Calendar_getMonth("C:/Users/Anna/Desktop/test/months.xml",2, 2018));
        System.out.println("Check current date: " + test.CurrentMonth_checkDate());

        try {
            System.out.print("Check DateComparator: ");
            System.out.println(test.DateComparator_FirstIfMore(new Date(3, 50, 2018),
                    new Date(2, 1, 2018)));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Check addition/calculating/saving of tasks: " + test.TaskMap_Output("C:/Users/Anna/Desktop/test/SAVED.xml", 10));
        System.out.println("Check loading/calculating of tasks: " + test.TaskMap_Input("C:/Users/Anna/Desktop/test/SAVED.xml", "C:/Users/Anna/Desktop/test/months.xml", 10));
    }
}
*/