package GUI.TaskEditWindow;

import GUI.Calendar.CalendarWindowListener;
import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AddTaskWindow {
    public AddTaskWindow(Date date, TaskMap taskMap, DefaultListModel<String> dlm, ArrayList<Task> currentTaskList, CalendarWindowListener calendarWindowListener) {
        JFrame frame = new JFrame();
        JLabel nameLable = new JLabel("name");
        JLabel descriptionLable = new JLabel("description");
        JLabel durabilityLable = new JLabel("durability");
        JLabel complexityLable = new JLabel("complexity");
        JTextField nameText = new JTextField("name");
        JTextField descriptionText = new JTextField("description");
        JTextField durabilityText = new JTextField("durability");
        JTextField complexityText = new JTextField("complexity");
        JButton addButton = new JButton("Add");
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
                currentTaskList.add(tmpTask);

                dlm.addElement(tmpTask.getName() + " | " + tmpTask.getComplexity());
                calendarWindowListener.deleteAndUpdate();
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

