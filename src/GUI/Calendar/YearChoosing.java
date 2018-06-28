package GUI.Calendar;

import GUI.ChooseTime;
import GUI.ShowGraphics;
import Model.Calendar.Calendar;
import Model.Calendar.CurrentMonth;
import Model.Calendar.Date;
import Model.Task.TaskMap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YearChoosing {
    private String comboBoxYear = "2017";
    public YearChoosing(Calendar calendar, CurrentMonth currentMonth, TaskMap taskMap){
        JDialog dialog = new JDialog();
        dialog.setLayout(null);
        dialog.setSize(300,130);

        JButton okButton = new JButton("Ok");
        JButton closeButton = new JButton("Cancel");

        int[] years = calendar.getYears();
        String[] items = new String[years.length];

        for (int i = 0; i < years.length; i++) {
            items[i] = String.valueOf(years[i]);
        }
        JComboBox<String> comboBox = new JComboBox<>(items);
        dialog.setResizable(false);

        comboBox.setBounds(10,10,265,30);
        okButton.setBounds(15,50,120,25);
        closeButton.setBounds(150,50,120,25);

        dialog.add(comboBox);
        dialog.add(okButton);
        dialog.add(closeButton);


        dialog.setVisible(true);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                if(item == null){
                    item = String.valueOf(years[0]);
                }
                int year = Integer.parseInt(item);
                CurrentMonth newCurrentMonth = new CurrentMonth(new Date(1, 1, year));
                ShowGraphics showGraphics = new ShowGraphics(calendar, taskMap, newCurrentMonth, ChooseTime.YEAR);
                dialog.dispose();
            }
        };

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                comboBoxYear = String.valueOf(comboBox.getSelectedItem());
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year = Integer.parseInt(comboBoxYear);
                CurrentMonth newCurrentMonth = new CurrentMonth(new Date(1, 1, year));
                ShowGraphics showGraphics = new ShowGraphics(calendar, taskMap, newCurrentMonth, ChooseTime.YEAR);
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }
}
