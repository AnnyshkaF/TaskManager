package Model.Calendar;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if(day > 31 || day < 1) throw new RuntimeException("Wrong day");
        if(month > 13 || month < 1) throw new RuntimeException("Wrong month");

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass() || obj == null) {
            return false;
        }
        Date o = (Date) obj;
        return this.getDay() == o.getDay() || this.getMonth() == o.getMonth() || this.getYear() == o.getYear();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(day);
        str.append("/");
        str.append(month);
        str.append("/");
        str.append(year);
        return str.toString();
    }
}