package GUI.TaskEditWindow;

import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTaskWindow{
    public EditTaskWindow(Date date, TaskMap taskMap, Task taskToEdit, int index){
        JFrame frame = new JFrame();
        JLabel nameLabel = new JLabel("name");
        JLabel descriptionLabel = new JLabel("description");
        JLabel durabilityLabel = new JLabel("durability");
        JLabel complexityLabel = new JLabel("complexity");
        JTextField nameText = new JTextField(taskToEdit.getName());
        JTextField descriptionText = new JTextField(taskToEdit.getDescription());
        JTextField  durabilityText= new JTextField(taskToEdit.getDurability());
        JTextField  complexityText= new JTextField(taskToEdit.getComplexity());
        JButton cancelButton = new JButton("Cancel");
        JButton saveButton = new JButton("Save");
        Container c = frame.getContentPane();

        frame.setSize(300,300);
        c.setLayout(new GridLayout(5,2));
        c.add(nameLabel);
        c.add(nameText);
        c.add(descriptionLabel);
        c.add(descriptionText);
        c.add(durabilityLabel);
        c.add(durabilityText);
        c.add(complexityLabel);
        c.add(complexityText);
        c.add(cancelButton);
        c.add(saveButton);
        frame.setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task newTask = new Task(nameText.getText(), descriptionText.getText(), durabilityText.getText(), complexityText.getText(), Boolean.valueOf(taskToEdit.getComplexity()));
                taskMap.getTaskArray(date).remove(index);
                taskMap.addTask(date, newTask);

                JOptionPane.showMessageDialog(null, "Task is changed!");
                frame.dispose();
            }
        });
    }
}