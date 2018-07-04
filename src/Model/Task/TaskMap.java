package model.task;

import model.calendar.Calendar;
import model.calendar.Date;

import java.util.*;

public class TaskMap {
    private TreeMap<Date, ArrayList<Task>> taskMap = new TreeMap<Date, ArrayList<Task>>(new DateComparator());

    public TaskMap(){}

    public TreeMap<Date, ArrayList<Task>> getTaskMap() {
        return taskMap;
    }

    public ArrayList<Task> getTaskArray(Date date){
        return taskMap.get(date);
    }

    public boolean containsDate(Date date){
        return taskMap.containsKey(date);
    }

    public void addTask(Date date, Task task) {
        if (taskMap.containsKey(date)) {
            taskMap.get(date).add(task);
        } else {
            taskMap.put(date, new ArrayList<>());
            taskMap.get(date).add(task);
        }
    }

    public void deleteTask(Date date, int index) {
        taskMap.get(date).remove(index);
        if(taskMap.get(date).size() == 0){
            taskMap.remove(date);
        }
    }

    public int calculateTotalTasksPerDay(Date date, boolean condition) {  //current month.date
        int total = 0;
        if(taskMap.containsKey(date)){
            for (Task temp : taskMap.get(date)) {
                if (temp.getCondition()== condition) {
                    total = total + 1;
                }
            }
        }
        return total;
    }

    public WeekStat calculateTotalTasksPerWeek(Calendar calendar, Date currentDate, boolean  condition, int startWeekDay) {  //current month.date
        WeekStat res = new WeekStat();
        for (Map.Entry<Date, ArrayList<Task>> entry : taskMap.entrySet()) {
            if (entry.getKey().getDay() >= startWeekDay && entry.getKey().getDay() <= startWeekDay + 6
                    && currentDate.getMonth() == entry.getKey().getMonth() && currentDate.getYear() == entry.getKey().getYear()) {
                for (Task temp : taskMap.get(entry.getKey())) {
                    if (temp.getCondition() == condition) {
                        res.setTotal(res.getTotal() + 1);
                    }
                    if (temp.getCondition()) {
                        res.setDone(entry.getKey().getDay() - startWeekDay, res.getDone(entry.getKey().getDay() - startWeekDay) + 1);
                    }
                    if (!temp.getCondition()) {
                        res.setUndone(entry.getKey().getDay() - startWeekDay,res.getUndone(entry.getKey().getDay() - startWeekDay) + 1);
                    }
                }
            }
        }
        if (calendar.getLoadedMonth(currentDate.getMonth(), currentDate.getYear()).getAmountOfDaysInMonth() - startWeekDay < 7) {
            Date newDate = new Date(1, currentDate.getMonth() + 1, currentDate.getYear());
            int prevWeek = calendar.getLoadedMonth(currentDate.getMonth(), currentDate.getYear()).getAmountOfDaysInMonth() - startWeekDay + 1;
            int toAdd = 7 - prevWeek;
            for (Map.Entry<Date, ArrayList<Task>> entry : taskMap.entrySet()) {
                if (entry.getKey().getDay() >= 0 && entry.getKey().getDay() <= toAdd
                        && newDate.getMonth() == entry.getKey().getMonth() && newDate.getYear() == entry.getKey().getYear()) {
                    for (Task temp : taskMap.get(entry.getKey())) {
                        if (temp.getCondition() == condition) {
                            res.setTotal(res.getTotal() + 1);
                        }
                        if (temp.getCondition()) {
                            res.setDone(prevWeek + entry.getKey().getDay() - 1 , res.getDone(prevWeek + entry.getKey().getDay() - 1) + 1);;
                        }
                        if (!temp.getCondition()) {
                            res.setUndone(prevWeek + entry.getKey().getDay() - 1, res.getUndone(prevWeek + entry.getKey().getDay() - 1) + 1);
                        }
                    }
                }
            }
        }
        return res;
    }

    public int calculateTotalTasksPerMonth(Date date, boolean condition) {  //current month.date
        int total = 0;
        for (Map.Entry<Date, ArrayList<Task>> entry : taskMap.entrySet()) {
            if (entry.getKey().getMonth() == date.getMonth() && entry.getKey().getYear() == date.getYear()) {
                for (Task temp : taskMap.get(entry.getKey())) {
                    if (temp.getCondition() == condition) {
                        total = total + 1;
                    }
                }
            }
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Date, ArrayList<Task>> entry : taskMap.entrySet()) {
            str.append("-------------");
            str.append("\n");
            int dayTasks = calculateTotalTasksPerDay(entry.getKey(), false) - calculateTotalTasksPerDay(entry.getKey(), true);
            if(dayTasks < 0) {dayTasks = 0;}
            str.append(entry.getKey() + " " + dayTasks + " | ");
            str.append("\n");
            str.append("-------------");
            str.append("\n");
            for (Task temp : taskMap.get(entry.getKey())) {
                str.append(temp);
                str.append("\n");
                str.append("**********");
                str.append("\n");
            }
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass() || obj == null) {
            return false;
        }
        TaskMap o = (TaskMap) obj;
        if (this.taskMap.size() != o.taskMap.size()) {
            return false;
        }
        Map.Entry<Date, ArrayList<Task>> entry1;
        for (Map.Entry<Date, ArrayList<Task>> entry2 : this.taskMap.entrySet()) {
            entry1 = this.taskMap.ceilingEntry(entry2.getKey());
            if (entry1.getKey() == entry2.getKey()) {
                if (entry1.getValue().equals(entry2.getValue())) {
                    break;
                } else return false;
            } else return false;
        }
        return true;
    }
}

