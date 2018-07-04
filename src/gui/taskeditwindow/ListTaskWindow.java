package gui.taskeditwindow;

import gui.calendar.CalendarWindowListener;
import gui.statisticwindow.Statistics;
import model.calendar.Date;
import model.task.Task;
import model.task.TaskMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListTaskWindow extends JDialog{

    private DefaultListModel<String> dlm = new DefaultListModel<>();
    private JList<String> list = new JList<>(dlm);
    private ArrayList<Task> currentList = new ArrayList<>();
    private TaskMap taskMap;
    private Date date;
    private boolean vDone = false;

    public ListTaskWindow(TaskMap taskMap, Date date, CalendarWindowListener calendarWindowListener) {
        ListTaskListener listTaskListener = new ListTaskListener(this, taskMap);
        JScrollPane scrollPane = new JScrollPane(list);
        this.taskMap = taskMap;
        this.date = date;
        list.setCellRenderer(new MyCellRenderer());

        ActionListener menuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Popup menu item ["
                        + event.getActionCommand() + "] was pressed.");
            }
        };

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

        if (taskMap.containsDate(date)) {
            for (Task temp : taskMap.getTaskArray(date)) {
                if (!temp.getCondition()) {
                    dlm.addElement(temp.getName());
                    currentList.add(temp);
                }
            }
        }
        
        setSize(450, 500);
        setLayout(null);
        JLabel dateLabel = new JLabel(date.toString());
        JCheckBox showDone = new JCheckBox("Show done?");

        add(dateLabel);
        add(showDone);
        add(scrollPane);

        JMenuItem itemAdd = new JMenuItem("Add", new ImageIcon("icons/plus.gif"));
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
        list.setComponentPopupMenu(jPopupMenu);

        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                calendarWindowListener.deleteAndUpdate();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                dateLabel.setBounds(e.getComponent().getWidth() / 2, 0, e.getComponent().getWidth() / 2, 20);
                scrollPane.setBounds(0, 20, e.getComponent().getWidth(), e.getComponent().getHeight() - 40);
                showDone.setBounds(0, 0, e.getComponent().getWidth() / 2, 20);
                list.setFixedCellHeight(50);
            }
        });

        itemAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskWindow etw = new AddTaskWindow(date, taskMap, dlm, currentList, calendarWindowListener, listTaskListener);
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
                    if (!showDone.isSelected()) {
                        int i = taskMap.getTaskArray(date).indexOf(currentList.get(index));
                        taskMap.deleteTask(date, i);
                    } else {
                        taskMap.deleteTask(date, index);
                    }
                }
                updateListTaskWindow();
                calendarWindowListener.deleteAndUpdate();
            }
        });

        itemInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (!showDone.isSelected()) {
                    InfoTaskWindow itw = new InfoTaskWindow(date, currentList.get(index));
                } else {
                    InfoTaskWindow itw = new InfoTaskWindow(date, taskMap.getTaskArray(date).get(index));
                }
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
                if (!showDone.isSelected()) {
                    EditTaskWindow itw = new EditTaskWindow(date, taskMap, currentList.get(index), index);
                    return;
                } else {
                    EditTaskWindow itw = new EditTaskWindow(date, taskMap, taskMap.getTaskArray(date).get(index), index);
                }
                updateListTaskWindow();
            }
        });

        itemDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCurrentList();
                int index = list.getSelectedIndex();
                if (!showDone.isSelected()) {
                    int i = taskMap.getTaskArray(date).indexOf(currentList.get(index));
                    taskMap.getTaskArray(date).get(i).setCondition(true);
                } else {
                    taskMap.getTaskArray(date).get(index).setCondition(true);
                }
                updateListTaskWindow();
                calendarWindowListener.deleteAndUpdate();
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
                vDone = showDone.isSelected();
                updateListTaskWindow();
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
    
    public void updateListTaskWindow(){
        dlm.removeAllElements();
        updateCurrentList();
        if (vDone) {
            if (taskMap.containsDate(date)) {
                for (Task temp : taskMap.getTaskArray(date)) {
                    dlm.addElement(temp.getName());
                }
            }
        }
        if (!vDone) {
            if (taskMap.containsDate(date)) {
                for (Task temp : taskMap.getTaskArray(date)) {
                    if (!temp.getCondition()) {
                        dlm.addElement(temp.getName());
                    }
                }
            }
        }
    }

    private void updateCurrentList() {
        currentList.clear();
        if (!taskMap.getTaskArray(date).isEmpty()) {
            if (vDone) {
                if (taskMap.containsDate(date)) {
                    currentList.addAll(taskMap.getTaskArray(date));
                }
            }
            if (!vDone) {
                for (Task temp : taskMap.getTaskArray(date)) {
                    if (!temp.getCondition()) {
                        currentList.add(temp);
                    }
                }
            }
        }
    }
    class MyCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (vDone) {
                if (currentList.get(index).getCondition()) {
                    c.setFont(c.getFont().deriveFont(Font.ITALIC));
                    return c;
                } else {
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                }
            }
            return c;
        }
    }

}



