package gui.taskeditwindow;

import model.calendar.Date;
import model.task.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoTaskWindow extends JDialog{
    public InfoTaskWindow(Date date, Task tmpTask){
        JLabel dateLabel = new JLabel("Date:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel descriptionLabel = new JLabel("Description:");
        JLabel durabilityLabel = new JLabel("Durability:");
        JLabel complexityLabel = new JLabel("Complexity:");
        JScrollPane dateText = new JScrollPane(new JLabel(String.valueOf(date.getDay()) +"/" + String.valueOf(date.getMonth()) +"/"+String.valueOf(date.getYear())));
        JScrollPane nameText = new JScrollPane(new JLabel(tmpTask.getName()));
        JScrollPane descriptionText = new JScrollPane(new JLabel(tmpTask.getDescription()));
        JScrollPane durabilityText= new JScrollPane(new JLabel(tmpTask.getDurability()));
        JScrollPane complexityText= new JScrollPane(new JLabel(tmpTask.getComplexity()));
        JButton cancelButton = new JButton("Cancel");

        dateLabel.setBounds(10,10, 100, 30);
        nameLabel.setBounds(10,40, 100, 30);
        descriptionLabel.setBounds(10,40,100,100);
        complexityLabel.setBounds(10,170, 100, 30);
        durabilityLabel.setBounds(10,200, 100, 30);
        dateText.setBounds(110, 10, 100, 30);
        nameText.setBounds(110, 40, 100, 30);
        descriptionText.setBounds(110, 70, 100, 100);
        complexityText.setBounds(110, 170, 100, 30);
        durabilityText.setBounds(110, 200, 100, 30);
        cancelButton.setBounds(65, 240, 100, 30);


        setSize(250,320);
        setResizable(false);

        setLayout(null);
        add(dateLabel);
        add(dateText);
        add(nameLabel);
        add(nameText);
        add(descriptionLabel);
        add(descriptionText);
        add(durabilityLabel);
        add(durabilityText);
        add(complexityLabel);
        add(complexityText);
        add(cancelButton);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}