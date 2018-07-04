package gui.calendar;


import model.calendar.Calendar;

public class CalendarWindowListener {
    private CalendarWindow calendarWindow;
    private Calendar calendar;

    public CalendarWindowListener(CalendarWindow calendarWindow, Calendar calendar) {
        this.calendarWindow = calendarWindow;
        this.calendar = calendar;
    }

    public void deleteAndUpdate() {
        calendarWindow.updateTable(calendar.getLoadedMonth(calendarWindow.currentMonth.getMonth(), calendarWindow.currentMonth.getYear()));
    }
}
