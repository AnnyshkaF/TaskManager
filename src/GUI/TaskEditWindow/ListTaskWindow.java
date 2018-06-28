package GUI.TaskEditWindow;

import GUI.Calendar.CalendarWindowListener;
import GUI.StatisticWindow.Statistics;
import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListTaskWindow{
    public ListTaskWindow(TaskMap taskMap, Date date, CalendarWindowListener calendarWindowListener) {
        ActionListener menuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Popup menu item ["
                        + event.getActionCommand() + "] was pressed.");
            }
        };

        JDialog dialog = new JDialog();
        DefaultListModel<String> dlm = new DefaultListModel<>();
        JList<String> list = new JList<>(dlm);
        ArrayList<Task> currentList = new ArrayList<>();
        JScrollPane scrollPane = new JScrollPane(list);

        JPopupMenu jPopupMenu = new JPopupMenu() {
            @Override
            public void show(Component invoker, int x, int y) {
                int row = list.locationToIndex(new Point(x, y));
                if (row != -1) {
                    list.setSelectedIndex(row);
                }
                super.show(invoker, x, y);
            }
        };

        if(taskMap.containsDate(date)) {
            for (Task temp : taskMap.getTaskArray(date)) {
                if (!temp.getCondition()) {
                    dlm.addElement(temp.getName() + " | " + temp.getComplexity());
                    currentList.add(temp);
                }
            }
        }

        dialog.setSize(450, 500);
        dialog.setLayout(null);
        JLabel dateLabel = new JLabel(date.toString());
        JCheckBox showDone = new JCheckBox("Show done?");

        dialog.add(dateLabel);
        dialog.add(showDone);
        dialog.add(scrollPane);

        JMenuItem itemAdd = new JMenuItem("Add", new ImageIcon("icons/add.gif"));
        JMenuItem itemDelete = new JMenuItem("Delete", new ImageIcon("icons/delete.gif"));
        JMenuItem itemEdit = new JMenuItem("Edit", new ImageIcon("icons/edit.gif"));
        JMenuItem itemDone = new JMenuItem("Done", new ImageIcon("icons/tick.gif"));
        JMenuItem itemInfo = new JMenuItem("Information", new ImageIcon("icons/info.gif"));
        JMenuItem itemStatistics = new JMenuItem("Statistics", new ImageIcon("icons/stat.gif"));

        jPopupMenu.add(itemAdd);
        itemAdd.setHorizontalTextPosition(JMenuItem.RIGHT);
        itemAdd.addActionListener(menuListener);
        jPopupMenu.add(itemDelete);
        itemDelete.setHorizontalTextPosition(JMenuItem.RIGHT);
        itemDelete.addActionListener(menuListener);
        jPopupMenu.add(itemEdit);
        itemEdit.setHorizontalTextPosition(JMenuItem.RIGHT);
        itemEdit.addActionListener(menuListener);
        jPopupMenu.add(itemDone);
        itemDone.setHorizontalTextPosition(JMenuItem.RIGHT);
        itemDone.addActionListener(menuListener);
        jPopupMenu.add(itemInfo);
        itemInfo.setHorizontalTextPosition(JMenuItem.RIGHT);
        itemInfo.addActionListener(menuListener);
        jPopupMenu.add(itemStatistics);
        itemStatistics.setHorizontalTextPosition(JMenuItem.RIGHT);
        itemStatistics.addActionListener(menuListener);

        list.setFont(new Font("Lucida Fax",Font.BOLD,16));
        list.setComponentPopupMenu(jPopupMenu);
        dialog.setVisible(true);

        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                calendarWindowListener.deleteAndUpdate();
            }
        });

        dialog.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                dateLabel.setBounds(e.getComponent().getWidth()/2, 0, e.getComponent().getWidth()/2, 20);
                scrollPane.setBounds(0, 20, e.getComponent().getWidth(),  e.getComponent().getHeight() - 40);
                showDone.setBounds(0, 0, e.getComponent().getWidth()/2, 20);
                list.setFixedCellHeight(50);
            }
        });

        itemAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskWindow etw = new AddTaskWindow(date, taskMap, dlm, currentList, calendarWindowListener);
            }
        });
        itemDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Element to delete is not chosen!");
                    return;
                }
                int optionPane = JOptionPane.showConfirmDialog(null, "Delete task?");
                if (optionPane == JOptionPane.YES_OPTION) {
                    if(!showDone.isSelected()){
                        int i = taskMap.getTaskArray(date).indexOf(currentList.get(index));
                        taskMap.deleteTask(date, i);
                        dlm.remove(index);
                        calendarWindowListener.deleteAndUpdate();
                        return;
                    }
                    dlm.remove(index);
                    taskMap.deleteTask(date, index);
                }
                calendarWindowListener.deleteAndUpdate();
            }
        });
        itemInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if(!showDone.isSelected()) {
                    InfoTaskWindow itw = new InfoTaskWindow(date, currentList.get(index), index);
                    return;
                }
                InfoTaskWindow itw = new InfoTaskWindow(date, taskMap.getTaskArray(date).get(index), index);
            }
        });
        itemEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Element to edit is not chosen!");
                    return;
                }
                if(!showDone.isSelected()) {
                    EditTaskWindow itw = new EditTaskWindow(date, taskMap, currentList.get(index), index);
                    return;
                }
                EditTaskWindow itw = new EditTaskWindow(date, taskMap, taskMap.getTaskArray(date).get(index), index);
            }
        });
        itemDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if(!showDone.isSelected()){
                    dlm.remove(index);
                    int i = taskMap.getTaskArray(date).indexOf(currentList.get(index));
                    Task t = taskMap.getTaskArray(date).get(i);
                    taskMap.deleteTask(date, i);
                    t.setCondition(true);
                    taskMap.addTask(date, t);
                    calendarWindowListener.deleteAndUpdate();
                    return;
                }
                /*dlm.remove(index);
                Task t = taskMap.getTaskArray(date).get(index);
                taskMap.deleteTask(date, index);
                t.setCondition(true);
                taskMap.addTask(date, t);
                calendarWindowListener.deleteAndUpdate();*/
            }
        });
        itemStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statistics statistics = new Statistics(taskMap, date);
            }
        });
        showDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dlm.removeAllElements();
                if(showDone.isSelected()){

                    if(taskMap.containsDate(date)) {
                        for (Task temp : taskMap.getTaskArray(date)) {
                            dlm.addElement(temp.getName() + " | " + temp.getComplexity());
                        }
                    }
                }
                if(!showDone.isSelected()){
                    dlm.removeAllElements();
                    if(taskMap.containsDate(date)) {
                        for (Task temp : taskMap.getTaskArray(date)) {
                            if (!temp.getCondition()) {
                                dlm.addElement(temp.getName() + " | " + temp.getComplexity());
                            }
                        }
                    }
                }
            }
        });

        class Mouse extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                checkPopup(e);
            }

            public void mouseClicked(MouseEvent e) {
                checkPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
                checkPopup(e);
            }

            private void checkPopup(MouseEvent e) {
                if (e.isPopupTrigger()) { //if the event shows the menu
                    list.setSelectedIndex(list.locationToIndex(e.getPoint())); //select the item
                    jPopupMenu.show(list, e.getX(), e.getY()); //and show the menu
                }
            }
        }
    }


}
