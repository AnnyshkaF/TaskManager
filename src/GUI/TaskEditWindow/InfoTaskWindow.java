package GUI.TaskEditWindow;

import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;
import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoTaskWindow{
    public InfoTaskWindow(Date date, Task tmpTask, int index){
        JFrame frame = new JFrame();
        JLabel dateLabel = new JLabel("date");
        JLabel nameLabel = new JLabel("name");
        JLabel descriptionLabel = new JLabel("description");
        JLabel durabilityLabel = new JLabel("durability");
        JLabel complexityLabel = new JLabel("complexity");
        JLabel dateText = new JLabel(String.valueOf(date.getDay()) +"/" + String.valueOf(date.getMonth()) +"/"+String.valueOf(date.getYear()));
        JLabel nameText = new JLabel(tmpTask.getName());
        JLabel descriptionText = new JLabel(tmpTask.getDescription());
        JLabel durabilityText= new JLabel(tmpTask.getDurability());
        JLabel complexityText= new JLabel(tmpTask.getComplexity());
        JButton cancelButton = new JButton("Cancel");
        Container c = frame.getContentPane();

        frame.setSize(300,300);
        c.setLayout(new GridLayout(6,2));
        c.add(dateLabel);
        c.add(dateText);
        c.add(nameLabel);
        c.add(nameText);
        c.add(descriptionLabel);
        c.add(descriptionText);
        c.add(durabilityLabel);
        c.add(durabilityText);
        c.add(complexityLabel);
        c.add(complexityText);
        c.add(cancelButton);
        frame.setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}