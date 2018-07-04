package gui.taskeditwindow;

import model.task.TaskMap;

public class ListTaskListener {
    private ListTaskWindow listTaskWindow;
    private TaskMap taskMap;

    public ListTaskListener(ListTaskWindow listTaskWindow, TaskMap taskMap){
        this.listTaskWindow = listTaskWindow;
        this.taskMap = taskMap;
    }

    public void updateList(){
        listTaskWindow.updateListTaskWindow();
    }
}
