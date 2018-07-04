package gui.taskeditwindow;

import gui.calendar.CalendarWindowListener;
import model.calendar.Date;
import model.task.Task;
import model.task.TaskMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AddTaskWindow {
    public AddTaskWindow(Date date, TaskMap taskMap, DefaultListModel<String> dlm, ArrayList<Task> currentTaskList, CalendarWindowListener calendarWindowListener, ListTaskListener listTaskListener) {
        JFrame frame = new JFrame();
        JLabel nameLable = new JLabel("Name:");
        JLabel descriptionLable = new JLabel("Description:");
        JLabel durabilityLable = new JLabel("Durability:");
        JLabel complexityLable = new JLabel("Complexity:");
        JTextField nameText = new JTextField("name");
        JTextField descriptionText = new JTextField("description");
        JTextField durabilityText = new JTextField("durability");
        JTextField complexityText = new JTextField("complexity");
        JButton addButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        Container c = frame.getContentPane();

        frame.setSize(300, 300);
        c.setLayout(new GridLayout(5, 2));
        c.add(nameLable);
        c.add(nameText);
        c.add(descriptionLable);
        c.add(descriptionText);
        c.add(durabilityLable);
        c.add(durabilityText);
        c.add(complexityLable);
        c.add(complexityText);
        c.add(addButton);
        c.add(cancelButton);

        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameText.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Name task!");
                    return;
                }
                Task tmpTask = new Task(nameText.getText(), descriptionText.getText(), durabilityText.getText(), complexityText.getText(), false);
                taskMap.addTask(date, tmpTask);

                calendarWindowListener.deleteAndUpdate();
                listTaskListener.updateList();
                frame.dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}

