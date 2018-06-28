package Model.Calendar;

public class CurrentMonth {
    private Date currentMonth = new Date(java.time.MonthDay.now().getDayOfMonth(), java.time.MonthDay.now().getMonth().getValue(), java.time.Year.now().getValue());

    public CurrentMonth(){}
    public CurrentMonth(Date date) {
        currentMonth = date;
    }

    public Date getCurrentMonth() {
        return currentMonth;
    }
    public int getDay(){
        return currentMonth.getDay();
    }
    public int getMonth(){
        return currentMonth.getMonth();
    }
    public int getYear(){
        return currentMonth.getYear();
    }

    public void toPrevMonth(){
        currentMonth.setMonth(currentMonth.getMonth() - 1);
        if(currentMonth.getMonth() == 0){
            currentMonth.setYear(currentMonth.getYear() - 1);
            currentMonth.setMonth(12);
        }
    }
    public void toNextMonth(){
        currentMonth.setMonth(currentMonth.getMonth() + 1);
        if(currentMonth.getMonth() == 13){
            currentMonth.setYear(currentMonth.getYear() + 1);
            currentMonth.setMonth(1);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this.equals(obj);
    }

    @Override
    public String toString() {
        return currentMonth.toString();
    }
}