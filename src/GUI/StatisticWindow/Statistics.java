package GUI.StatisticWindow;

import Model.Calendar.Calendar;
import Model.Calendar.CurrentMonth;
import Model.Calendar.Date;
import Model.Task.TaskMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Statistics  extends JDialog{
    private JButton buttonOK = new JButton("OK");
    private JButton buttonCancel = new JButton("Cancel");
    Integer done;
    Integer undone;
    Integer res;

    //month
    public Statistics(CurrentMonth currentMonth, TaskMap taskMap){
        Date date = currentMonth.getCurrentMonth();
        JLabel monthNameLabel = new JLabel(String.valueOf(date.getMonth()) + "/" + String.valueOf(date.getYear()));
        JLabel addedLabel = new JLabel("Added:");
        JLabel doneLabel = new JLabel("Done:");
        JLabel leftLabel = new JLabel("Left:");
        undone = taskMap.calculateTotalTasksPerMonth(date, false);
        done = taskMap.calculateTotalTasksPerMonth(date, true);
        JLabel addedValue = new JLabel(undone.toString());
        JLabel doneValue = new JLabel(done.toString());
        res = undone - done;
        if(res < 0) res = 0;
        JLabel leftValue = new JLabel(String.valueOf(res));

        Container c = getContentPane();
        c.setLayout(new GridLayout(5, 2));
        c.add(monthNameLabel);
        c.add(addedLabel);
        c.add(addedValue);
        c.add(doneLabel);
        c.add(doneValue);
        c.add(leftLabel);
        c.add(leftValue);
        c.add(buttonOK);
        c.add(buttonCancel);

        pack();
        setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    //week
    public Statistics(Calendar calendar, CurrentMonth currentMonth, TaskMap taskMap, int startWeek) {
        Date date = currentMonth.getCurrentMonth();
        JLabel monthNameLabel = new JLabel(String.valueOf(date.getMonth()) + "/" + String.valueOf(date.getYear()));
        JLabel addedLabel = new JLabel("Added:");
        JLabel doneLabel = new JLabel("Done:");
        JLabel leftLabel = new JLabel("Left:");
        undone = taskMap.calculateTotalTasksPerWeek(calendar, date, false, startWeek).getTotal();
        done = taskMap.calculateTotalTasksPerWeek(calendar, date, true, startWeek).getTotal();
        JLabel addedValue = new JLabel(undone.toString());
        JLabel doneValue = new JLabel(done.toString());
        res = undone - done;
        if(res < 0) res = 0;
        JLabel leftValue = new JLabel(String.valueOf(res));

        Container c = getContentPane();
        c.setLayout(new GridLayout(5, 2));
        c.add(monthNameLabel);
        c.add(addedLabel);
        c.add(addedValue);
        c.add(doneLabel);
        c.add(doneValue);
        c.add(leftLabel);
        c.add(leftValue);
        c.add(buttonOK);
        c.add(buttonCancel);

        pack();
        setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    //day
    public Statistics(TaskMap taskMap, Date date) {   //day = (0 - 6)
        JLabel monthNameLabel = new JLabel(String.valueOf(date.getMonth()) + "/" + String.valueOf(date.getYear()));
        JLabel emptyLabel = new JLabel("");
        JLabel addedLabel = new JLabel("Added:");
        JLabel doneLabel = new JLabel("Done:");
        JLabel leftLabel = new JLabel("Left:");
        undone = taskMap.calculateTotalTasksPerDay(date, false);
        done = taskMap.calculateTotalTasksPerDay(date, true);
        JLabel addedValue = new JLabel(String.valueOf(undone + done));
        JLabel doneValue = new JLabel(String.valueOf(done));
        JLabel leftValue = new JLabel(String.valueOf(undone));

        Container c = getContentPane();
        c.setLayout(new GridLayout(5, 2));
        c.add(monthNameLabel);
        c.add(emptyLabel);
        c.add(addedLabel);
        c.add(addedValue);
        c.add(doneLabel);
        c.add(doneValue);
        c.add(leftLabel);
        c.add(leftValue);

        c.add(buttonOK);
        c.add(buttonCancel);

        pack();
        setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

    }
    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
