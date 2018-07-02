package Model.Task;

public class WeekStat{
    private int total = 0;
    private int[] done = new int[7];
    private int[] undone = new int[7];

    public int getTotal() {
        return total;
    }

    public int getDone(int i) {
        return done[i];
    }

    public int getUndone(int i) {
        return undone[i];
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setDone(int i, int value) {
        this.done[i] = value;
    }

    public void setUndone(int i, int value) {
        this.undone[i] = value;
    }

    public int[] getDone() {
        return done;
    }

    public int[] getUndone() {
        return undone;
    }
}

