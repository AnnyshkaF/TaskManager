package Model.Calendar;

public class LoadedMonth {
    private int month;
    private int year;
    private int numOfFirstDay;
    private int amountOfDaysInMonth;
    private int amountOfWeeks;

    public LoadedMonth(int month, int year, int numOfFirstDay, int amountOfDaysInMonth, int amountOfWeeks) {
        this.month = month; //1-12
        this.year = year;
        this.numOfFirstDay = numOfFirstDay; //0-6
        this.amountOfDaysInMonth = amountOfDaysInMonth; //1-31
        this.amountOfWeeks = amountOfWeeks; //0-5
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getNumOfFirstDay() {
        return numOfFirstDay;
    }

    public int getAmountOfDaysInMonth() {
        return amountOfDaysInMonth;
    }

    public int getAmountOfWeeks() {
        return amountOfWeeks;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(month);
        str.append(" ");
        str.append(year);
        str.append(" ");
        str.append(numOfFirstDay);
        str.append(" ");
        str.append(amountOfDaysInMonth);
        str.append(" ");
        str.append(amountOfWeeks);
        return str.toString();
    }
}
