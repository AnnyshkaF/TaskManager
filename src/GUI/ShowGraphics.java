package GUI;

import Model.Calendar.Calendar;
import Model.Calendar.CurrentMonth;
import Model.Calendar.Date;
import Model.Task.TaskMap;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

class ViewGraphics extends Component {
    /*private String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};*/
    private String[] monthNames = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    Calendar calendar;
    TaskMap taskMap;
    Date date;
    ChooseTime time;
    ArrayList<Date> dateList;
    int amountOfYears;
    private int otstup = 0;
    public ViewGraphics(Calendar calendar, TaskMap taskMap, Date date, ChooseTime time){
        this.calendar = calendar;
        this.taskMap = taskMap;
        this.date = date;
        this.time = time;
        amountOfYears = calendar.getYearsAmount();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (time == ChooseTime.WEEK) {
            drawWeekAxis(g);
            calcWeekValues(g, date);
        }
        if (time == ChooseTime.MONTH) {
            drawMonthAxis(g);
            calcMonthValues(g, date);
        }
        if (time == ChooseTime.WEEKS) {
            setPreferredSize(new Dimension(amountOfYears * 500, 500));
            calcAllWeeksValues(g);
            drawAllWeeksAxis(g);
        }
        if(time == ChooseTime.YEAR){
            calcYearWeeksValues(g, date.getYear());
            drawYearAxis(g, date.getYear());
        }
    }

    void drawWeekAxis(Graphics g){
        g.drawLine(20  ,20 ,20 ,430 );         //ось yWeek
        g.drawLine(20 , 430 , 590 , 430);      //ось xWeek
        g.drawLine(20 ,20 ,15 ,35);            //стрелка   yWeek
        g.drawLine(20 ,20 ,25 ,35);            //стрелка   yWeek
        g.drawLine(590 ,430 ,585 ,425);        //стрелка   xWeek
        g.drawLine(590 ,430 ,585 ,435);        //стрелка   xWeek
        g.drawString("days", 580, 420);
        g.drawString("tasks", 30, 25);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int j = 0;
        for(int i = 110; i < 660; i+=90){    //xWeek
            g.drawLine(i - 90,425,i - 90,435);
            g.drawString(days[j++], -110 + i,  450);
        }
        int task = 0;
        for(int i = 61; i < 440; i+=41){    //yWeek
            g.drawLine(15, i,25, i) ;
            g.drawString(String.valueOf(task++), 3, 495 - i);
        }

        g.setColor(Color.RED);
        g.drawLine(630, 440, 680, 440);
        g.drawString("UNDONE", 630, 435);

        g.setColor(Color.GREEN);
        g.drawLine(630, 415, 680, 415);
        g.drawString("DONE", 630, 410);
    }
    void drawMonthAxis(Graphics g){
        g.drawLine(20  ,20 ,20 ,430 );         //ось yWeek
        g.drawLine(20 , 430 , 670 , 430);      //ось xWeek
        g.drawLine(20 ,20 ,15 ,35);            //стрелка   yWeek
        g.drawLine(20 ,20 ,25 ,35);            //стрелка   yWeek
        g.drawLine(670 ,430 ,655 ,425);        //стрелка   xWeek
        g.drawLine(670 ,430 ,655 ,435);        //стрелка   xWeek
        g.drawString("days", 650, 420);
        g.drawString("tasks", 30, 25);

        int daysInMonth = calendar.getLoadedMonth(date.getMonth(), date.getYear()).getAmountOfDaysInMonth();
        //set max day
        int max = 0;
        if(daysInMonth == 28){max = 600;}
        if(daysInMonth == 29){max = 620;}
        if(daysInMonth == 30){max = 640;}
        if(daysInMonth == 31){max = 660;}

        int j = 1;
        for(int i = 40; i < max; i+=20){    //x
            g.drawLine(i,425,i,435);
            g.drawString(String.valueOf(j++), i - 5,  450);
        }
        int task = 0;
        for(int i = 61; i < 440; i+=41){    //y
            g.drawLine(15, i,25, i);
            g.drawString(String.valueOf(task++), 7, 495 - i);
        }

        g.setColor(Color.RED);
        g.drawLine(680, 440, 730, 440);
        g.drawString("UNDONE", 680, 435);

        g.setColor(Color.GREEN);
        g.drawLine(680, 415, 730, 415);
        g.drawString("DONE", 680, 410);
    }
    void drawYearAxis(Graphics g, int year){
        g.setColor(Color.BLACK);
        g.drawLine(20  ,10 ,20 ,440 );         //ось yWeek
        g.drawLine(20 , 430 , 650 , 430);      //ось xWeek
        g.drawLine(20 ,10 ,15 ,25);            //стрелка   yWeek
        g.drawLine(20 ,10 ,25 ,25);            //стрелка   yWeek
        g.drawLine(650 ,430 ,635 ,425);        //стрелка   xWeek
        g.drawLine(650 ,430 ,635 ,435);        //стрелка   xWeek
        g.drawString("weeks", 650, 420);
        g.drawString("tasks", 30, 25);

        g.setColor(Color.BLACK);
        int j = 1;
        for(int i = 20; i < 630; i+=12){    //x
            g.drawLine(i,425,i,435);
        }

        int task = 0;
        for(int i = 31; i < 440; i+=21){    //y
            g.drawLine(15, i,25, i);
            g.drawString(String.valueOf(task++), 0, 465 - i);
        }

        g.setColor(Color.RED);
        g.drawLine(700, 450, 750, 450);
        g.drawString("UNDONE", 700, 445);

        g.setColor(Color.GREEN);
        g.drawLine(700, 425, 750, 425);
        g.drawString("DONE", 700, 420);

        drawVerticalWeekText(g, year);
    }
    void drawAllWeeksAxis(Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(20  ,10 ,20 ,440 );         //ось yWeek
        g.drawLine(20 , 430 , 500 * amountOfYears , 430);      //ось xWeek
        g.drawLine(20 ,10 ,15 ,25);            //стрелка   yWeek
        g.drawLine(20 ,10 ,25 ,25);            //стрелка   yWeek
        g.drawLine(500 * amountOfYears ,430 ,495 * amountOfYears ,425);        //стрелка   xWeek
        g.drawLine(500 * amountOfYears ,430 ,495 * amountOfYears ,435);        //стрелка   xWeek
        g.drawString("weeks", 485 * amountOfYears, 420);
        g.drawString("tasks", 30, 25);

        g.setColor(Color.BLACK);
        int j = 1;
        for(int i = 20; i < 495 * amountOfYears - 10; i+=12){    //x
            g.drawLine(i,425,i,435);
        }

        int task = 0;
        for(int i = 31; i < 440; i+=21){    //y
            g.drawLine(15, i,25, i);
            g.drawString(String.valueOf(task++), 0, 465 - i);
        }

        g.setColor(Color.RED);
        g.drawLine(400, 20, 450, 20);
        g.drawString("UNDONE", 400, 15);

        g.setColor(Color.GREEN);
        g.drawLine(400, 40, 450, 40);
        g.drawString("DONE", 400, 35);

        drawVerticalWeekText(g);
    }

    void drawVerticalWeekText(Graphics g, int year){
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = new AffineTransform();
        at.setToRotation(-Math.PI / 2.0, getWidth() / 2.0, getHeight() / 2.0);
        g2d.setTransform(at);
        int j = 0;
        for(int i = -115 - otstup*15; i < 700; i+=15) {    //x
            if (dateList.get(j).getYear() == year) {
                if (dateList.get(j).getMonth() / 10 != 1) {
                    g2d.drawString(dateList.get(j).getMonth() + "/" + dateList.get(j).getYear(), 42, i);
                } else {
                    g2d.drawString(dateList.get(j).getMonth() + "/" + dateList.get(j).getYear(), 35, i);
                }
            }
            if (j < dateList.size() - 1) {
                j++;
            } else break;
        }
    }
    void drawVerticalWeekText(Graphics g) {
        g.setColor(Color.BLACK);
        int month = 0, week = 1, year = 0;
        int[] years = calendar.getYears();
        boolean upDown = false;
        int yMonth;
        g.drawString(monthNames[11], 17, 460);
        g.drawString(String.valueOf(years[year++]), 17, 495);
        for (int i = 17; i < 495 * amountOfYears - 10; i += 12) {    //x
            if (month < calendar.getLoadedMonthArraySize()) {
                if (week < calendar.getLoadedMonthsArray().get(month).getAmountOfWeeks() + 1) {
                    g.drawString(String.valueOf(week++), i, 450);
                } else {
                    week = 1;
                    if (month == 11) {
                        month = 0;
                        g.drawString(String.valueOf(years[year++]), i, 495);
                    }
                    if (upDown) {
                        yMonth = 465;
                    } else {
                        yMonth = 475;
                    }
                    upDown = !upDown;
                    g.drawString(monthNames[month++], i, yMonth);
                    g.drawString(String.valueOf(week++), i, 450);
                }
            }
        }
    }

    void calcWeekValues(Graphics g, Date fDate) {    //week
        int[] undone = taskMap.calculateTotalTasksPerWeek(calendar, fDate, true, fDate.getDay()).undone;
        int[] done = taskMap.calculateTotalTasksPerWeek(calendar, fDate, true, fDate.getDay()).done;

        for (int i = 0; i < 6; i++) {
            g.setColor(Color.RED);
            recalculateWeekAndDraw(g, i, undone[i], i+1, undone[i+1]);
            g.setColor(Color.GREEN);
            recalculateWeekAndDraw(g, i, done[i], i+1, done[i+1]);
        }
        int totalUndone = 0, totalDone  = 0, res;
        for (int i = 0; i < undone.length; i++) {
            totalUndone += undone[i];
            totalDone += done[i];
        }
        res = totalUndone - totalDone;
        if(res < 0) res = 0;
        g.setColor(Color.BLACK);
        g.drawString(date.getMonth() + "/" + date.getYear(), 630, 20);
        g.drawString("Undone: " + totalUndone, 630, 40);
        g.drawString("Done: " + totalDone, 630, 60);
        g.drawString("Left: " + res, 630, 80);
    }
    void calcMonthValues(Graphics g, Date fDate) {    //month
        int lastday = calendar.getLoadedMonth(fDate.getMonth(), fDate.getYear()).getAmountOfDaysInMonth();
        int totalUndone = 0, totalDone  = 0, res = 0;
        boolean once = false;
        for (int i = 1; i < lastday; i++) {

            int undone1 = taskMap.calculateTotalTasksPerDay(new Date( i, fDate.getMonth(), fDate.getYear()), false);
            int undone2 = taskMap.calculateTotalTasksPerDay(new Date( i + 1, fDate.getMonth(), fDate.getYear()), false);

            int done1 = taskMap.calculateTotalTasksPerDay(new Date( i, fDate.getMonth(), fDate.getYear()), true);
            int done2 = taskMap.calculateTotalTasksPerDay(new Date( i + 1, fDate.getMonth(), fDate.getYear()), true);

            g.setColor(Color.RED);
            recalculateMonthAndDraw(g, i, undone1, i+1, undone2);
            g.setColor(Color.GREEN);
            recalculateMonthAndDraw(g, i, done1, i+1, done2);

            totalUndone += undone1;
            totalDone += done1;
            if(i == lastday  - 1){
                totalUndone += undone2;
                totalDone += done2;
            }
        }
        res = totalUndone - totalDone;
        if(res < 0) res = 0;
        g.setColor(Color.BLACK);
        g.drawString(date.getMonth() + "/" + date.getYear(), 680, 20);
        g.drawString("Undone: " + totalUndone, 680, 40);
        g.drawString("Done: " + totalDone, 680, 60);
        g.drawString("Left: " + res, 680, 80);
    }
    void calcAllWeeksValues(Graphics g) {    //week
        ArrayList<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
        ArrayList<Date> dateList = new ArrayList<Date>();

        int curMonth = 12;
        int curYear = 2017;
        int j = 0;
        while(j < calendar.getLoadedMonthArraySize()){
            int counterOnceDay = 1;
            int day = calendar.getLoadedMonth(curMonth, curYear).getNumOfFirstDay();
            while (day != 0) {
                day++;
                counterOnceDay++;
                if (day == 7) {
                    day = 0;
                }
            }
            int i = 0, done = 0, undone = 0;
            while (counterOnceDay < calendar.getLoadedMonth(curMonth, curYear).getAmountOfDaysInMonth()){
                undone = taskMap.calculateTotalTasksPerWeek(calendar, new Date(1, curMonth, curYear), false, counterOnceDay).total;
                done = taskMap.calculateTotalTasksPerWeek(calendar, new Date(1, curMonth, curYear), true, counterOnceDay).total;
                list.add(new Pair<>(undone, done));
                dateList.add(new Date(counterOnceDay, curMonth, curYear));
                counterOnceDay+=7;
                i++;
            }

            curMonth++;
            if (curMonth == 13) {
                curMonth = 1;
                curYear++;
            }
            j++;
        }
        this.dateList = dateList;
        for (int i = 0; i < list.size() - 1; i++) {
            g.setColor(Color.RED);
            recalculateAllWeeksAndDraw(g, i, list.get(i).getKey(), i+1, list.get(i + 1).getKey());
            g.setColor(Color.GREEN);
            recalculateAllWeeksAndDraw(g, i, list.get(i).getValue(), i+1, list.get(i + 1).getValue());
        }
    }
    void calcYearWeeksValues(Graphics g, int year) {    //week
        ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
        int totalUndone = 0, totalDone  = 0, res = 0;
        ArrayList<Date> dateList = new ArrayList<Date>();

        int curMonth = 12;
        int curYear = 2017;
        int j = 0;
        while (j < calendar.getLoadedMonthArraySize()){
            int counterOnceDay = 1;
            int day = calendar.getLoadedMonth(curMonth, curYear).getNumOfFirstDay();
            while (day != 0) {
                day++;
                counterOnceDay++;
                if (day == 7) {
                    day = 0;
                }
            }
            int i = 0, done = 0, undone = 0;
            while (counterOnceDay < calendar.getLoadedMonth(curMonth, curYear).getAmountOfDaysInMonth()) {
                undone = taskMap.calculateTotalTasksPerWeek(calendar, new Date(1, curMonth, curYear), false, counterOnceDay).total;
                done = taskMap.calculateTotalTasksPerWeek(calendar, new Date(1, curMonth, curYear), true, counterOnceDay).total;
                list.add(new Pair<>(undone, done));
                dateList.add(new Date(counterOnceDay, curMonth, curYear));
                counterOnceDay += 7;
                i++;
                if(curYear == year){
                    totalUndone += undone;
                    totalDone += done;
                }
            }

            curMonth++;
            if (curMonth == 13) {
                curMonth = 1;
                curYear++;
            }
            j++;
        }
        this.dateList = dateList;
        int k = 0;
        while (dateList.get(k).getYear() != year) {
            otstup++;
            k++;
        }
        for (int i = k; i < list.size() - 1; i++) {
            if (year == 2017) {
                g.setColor(Color.RED);
                recalculateAllWeeksAndDraw(g, i, list.get(i).getKey(), i + 1, list.get(i + 1).getKey());
                g.setColor(Color.GREEN);
                recalculateAllWeeksAndDraw(g, i, list.get(i).getValue(), i + 1, list.get(i + 1).getValue());
            }
            if (dateList.get(i).getYear() == year) {
                g.setColor(Color.RED);
                recalculateYearAndDraw(g, i, list.get(i).getKey(), i + 1, list.get(i + 1).getKey());
                g.setColor(Color.GREEN);
                recalculateYearAndDraw(g, i, list.get(i).getValue(), i + 1, list.get(i + 1).getValue());
            } else {
                break;
            }
        }
        res = totalUndone - totalDone;
        if(res < 0) res = 0;
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(date.getYear()), 700, 20);
        g.drawString("Undone: " + totalUndone, 700, 40);
        g.drawString("Done: " + totalDone, 700, 60);
        g.drawString("Left: " + res, 700, 80);
    }

    void recalculateWeekAndDraw(Graphics g, int d1, int t1, int d2, int t2){
        int x1 = xWeek(d1);
        int y1 = yWeek(t1);
        int x2 = xWeek(d2);
        int y2 = yWeek(t2);
        g.drawLine(x1, y1, x2, y2);
    }
    void recalculateMonthAndDraw(Graphics g, int d1, int t1, int d2, int t2){
        int x1 = xMonth(d1);
        int y1 = yMonth(t1);
        int x2 = xMonth(d2);
        int y2 = yMonth(t2);
        g.drawLine(x1, y1, x2, y2);
    }
    void recalculateAllWeeksAndDraw(Graphics g, int d1, int t1, int d2, int t2){
        int x1 = xAllWeeks(d1);
        int y1 = yAllWeeks(t1);
        int x2 = xAllWeeks(d2);
        int y2 = yAllWeeks(t2);
        g.drawLine(x1, y1, x2, y2);
    }
    void recalculateYearAndDraw(Graphics g, int d1, int t1, int d2, int t2){
        int x1 = xYear(d1);
        int y1 = yYear(t1);
        int x2 = xYear(d2);
        int y2 = yYear(t2);
        g.drawLine(x1, y1, x2, y2);
    }

    int xWeek(int day){
        return 110 + ((day - 1) *  90);
    }
    int yWeek(int tasks){
        return 430 - (tasks * 41);
    }
    int xMonth(int day){
        return 20 + ((day) *  20);
    }
    int yMonth(int tasks){
        return 430 - (tasks * 41);
    }
    int xAllWeeks(int day){
        return 20 + ((day) *  12);
    }
    int yAllWeeks(int tasks){
        return 430 - (tasks * 21);
    }
    int xYear(int day){
        return 20 + ((day) *  12) - (otstup * 12);
    }
    int yYear(int tasks){
        return 430 - (tasks * 21);
    }
}

public class ShowGraphics {
    private String[] monthNames = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    public ShowGraphics(Calendar calendar, TaskMap taskMap, CurrentMonth currentMonth, ChooseTime time) {
        Date date = currentMonth.getCurrentMonth();
        JDialog dialog = new JDialog();
        ViewGraphics viewGraphics = new ViewGraphics(calendar, taskMap, date, time);
        JScrollPane scrollPane = new JScrollPane(viewGraphics);
        dialog.setResizable(false);
        dialog.add(scrollPane);

        switch(time) {
            case  WEEKS:
                dialog.setMinimumSize(new Dimension(830, 560));
                dialog.setTitle( "Statistics: All years");
                break;
            case  MONTH:
                dialog.setTitle( "Statistics: " + monthNames[currentMonth.getMonth() - 1] + "/" + currentMonth.getYear());
                dialog.setSize(780, 520);
                dialog.setResizable(false);
                break;
            case  WEEK:
                dialog.setSize(750, 520);
                dialog.setTitle( "Statistics: " + "week");
                dialog.setResizable(false);
                break;
            case  YEAR:
                dialog.setSize(800, 530);
                dialog.setTitle( "Statistics: " + currentMonth.getYear());
        }
        dialog.setVisible(true);

    }
}

