package Model.Calendar;

import java.util.ArrayList;
import java.util.Vector;

public class Calendar {
    private ArrayList<LoadedMonth> loadedMonthsArray = new ArrayList<>();

    public Calendar() { }

    public ArrayList<LoadedMonth> getLoadedMonthsArray() {
        return loadedMonthsArray;
    }

    public int getLoadedMonthArraySize() {
        return loadedMonthsArray.size();
    }

    public LoadedMonth getLoadedMonth(int month, int year) {
        for (LoadedMonth l : loadedMonthsArray) {
            if (l.getMonth() == month && l.getYear() == year) {
                return l;
            }
        }
        return null;
    }

    public int[] getYears() {
        Vector<Integer> v = new Vector<>();
        int i = 0;
        v.add(loadedMonthsArray.get(0).getYear());
        for (LoadedMonth l : loadedMonthsArray) {
            if (l.getYear() != v.get(i)) {
                v.add(l.getYear());
                i++;
            }
        }
        int a[] = new int[v.size()];
        for (int j = 0; j < a.length; j++) {
            a[j] = v.get(j);
        }
        return a;
    }

    public int getYearsAmount(){
        Vector<Integer> v = new Vector<>();
        int i = 0;
        v.add(loadedMonthsArray.get(0).getYear());
        for (LoadedMonth l : loadedMonthsArray) {
            if (l.getYear() != v.get(i)) {
                v.add(l.getYear());
                i++;
            }
        }
        return v.size();
    }
}






