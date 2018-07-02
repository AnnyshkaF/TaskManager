package GUI.Calendar;
import GUI.Graphics.ShowGraphics;
import GUI.TaskEditWindow.ListTaskWindow;
import GUI.ChooseTime;
import IO.CalendarIO;
import IO.TaskIO;
import Model.Calendar.*;
import Model.Task.TaskMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;  //шрифт
import java.awt.event.*;


public class CalendarWindow {
    private String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", ""};
    private String[] monthNames = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    private Object[][] dates = {
            {"", "", "", "", "", "", "", "x"},
            {"", "", "", "", "", "", "", "x"},
            {"", "", "", "", "", "", "", "x"},
            {"", "", "", "", "", "", "", "x"},
            {"", "", "", "", "", "", "", "x"},
            {"", "", "", "", "", "", "", "x"}};


    private TableModel model = new DefaultTableModel(dates, dayNames) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public void removeRow(int row) {
            super.removeRow(row);
        }
    };
    private Calendar calendar;
    private JTable table = new JTable(model);
    private TaskMap taskMap;
    private JFrame frame = new JFrame();
    public CurrentMonth currentMonth = new CurrentMonth();

    public CalendarWindow(TaskMap taskMap, String calendarFile, String saveFile) {
        this.calendar = new Calendar();
        CalendarIO calendarIO = new CalendarIO();
        calendarIO.loadCalendarFromFile(calendarFile, calendar);
        this.taskMap = taskMap;
        CalendarWindowListener calendarWindowListener = new CalendarWindowListener(this, calendar);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.setSize(715, 700);
        frame.setMinimumSize(new Dimension(780, 550));

        table.setCellSelectionEnabled(true);

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem itemAllYears = new JMenuItem("All Statistics");
        JMenuItem itemYear = new JMenuItem("Year Statistics");

        menu.add(itemAllYears);
        menu.add(itemYear);
        bar.add(menu);

        JLabel monthLabel;
        JButton previousMonth = new JButton( new ImageIcon("icons/bP.png"));
        JButton nextMonth = new JButton(new ImageIcon("icons/bN.png"));
        JButton monthButton = new JButton(new ImageIcon("icons/monthStat.png"));
        monthLabel = new JLabel(monthNames[java.time.MonthDay.now().getMonth().getValue() - 1] + "/" + currentMonth.getYear());


        frame.setLayout(null);
        frame.add(scrollPane);
        frame.add(previousMonth);
        frame.add(nextMonth);
        frame.add(monthLabel);
        frame.add(monthButton);
        frame.setJMenuBar(bar);

        updateTable(calendar.getLoadedMonth(java.time.MonthDay.now().getMonth().getValue(), java.time.Year.now().getValue()));
        frame.setVisible(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scrollPane.setBounds(0, 30, 100, 300);
                scrollPane.setSize(frame.getWidth(), frame.getHeight());
                table.setRowHeight(frame.getHeight() / 8 + 5);
                for (int i = 0; i < 8; i++) {
                    table.getColumnModel().getColumn(i).setPreferredWidth(frame.getWidth() / 8);
                }
                previousMonth.setBounds(10, 0, 100, 30);
                nextMonth.setBounds(120, 0, 100, 30);
                monthButton.setBounds(230, 0, 100, 30);
                monthLabel.setBounds(340, 0, 100, 30);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int startWeekDay = -1;
                    if (table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).equals("x")) {
                        try {
                            startWeekDay = getDate(table.getValueAt(table.getSelectedRow(), 0).toString());
                        } catch (Exception ex) {
                            //обрезанное начало месяца
                            int toSub = 7 - calendar.getLoadedMonth(currentMonth.getMonth(), currentMonth.getYear()).getNumOfFirstDay();
                            currentMonth.toPrevMonth();
                            startWeekDay = calendar.getLoadedMonth(currentMonth.getMonth(), currentMonth.getYear()).getAmountOfDaysInMonth() + toSub - 6;
                            ShowGraphics showGraphics = new ShowGraphics(calendar, taskMap, new CurrentMonth(new Date(startWeekDay, currentMonth.getMonth(), currentMonth.getYear())), ChooseTime.WEEK);
                            currentMonth.toNextMonth();
                            return;
                        }
                        ShowGraphics showGraphics = new ShowGraphics(calendar, taskMap, new CurrentMonth(new Date(startWeekDay, currentMonth.getMonth(), currentMonth.getYear())), ChooseTime.WEEK);
                        return;
                    }
                    ListTaskWindow taskListWindow = new ListTaskWindow(taskMap,
                            new Model.Calendar.Date(getDate(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString()),
                                    currentMonth.getMonth(), currentMonth.getYear()), calendarWindowListener);
                }
            }
        });

        previousMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMonth.toPrevMonth();
                LoadedMonth tmpMonth = calendar.getLoadedMonth(currentMonth.getMonth(), currentMonth.getYear());
                if (tmpMonth != null) {
                    updateTable(tmpMonth);
                    monthLabel.setText(monthNames[currentMonth.getMonth() - 1] + "/" + currentMonth.getYear());
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry! There is no this date in the base!");
                    currentMonth.toNextMonth();
                }
            }
        });

        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMonth.toNextMonth();
                LoadedMonth tmpMonth = calendar.getLoadedMonth(currentMonth.getMonth(), currentMonth.getYear());
                if (tmpMonth != null) {
                    updateTable(tmpMonth);
                    monthLabel.setText(monthNames[currentMonth.getMonth() - 1] + "/" + currentMonth.getYear());
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry! There is no this date in the base!");
                    currentMonth.toPrevMonth();
                }
            }
        });

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int answer = JOptionPane.showConfirmDialog(frame, "Save changes?");
                if (answer == JOptionPane.YES_OPTION) {
                    try {
                        new TaskIO().saveTasksToFile(saveFile, taskMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    frame.dispose();
                }
                if (answer == JOptionPane.NO_OPTION) {
                    frame.dispose();
                }
            }
        });

        monthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowGraphics showGraphics = new ShowGraphics(calendar, taskMap, currentMonth, ChooseTime.MONTH);
            }
        });

        itemAllYears.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowGraphics showGraphics = new ShowGraphics(calendar, taskMap, currentMonth, ChooseTime.WEEKS);
            }
        });

        itemYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                YearChoosing yearChoosing = new YearChoosing(calendar, currentMonth, taskMap);
            }
        });
    }

    public void updateTable(LoadedMonth tmpMonth) {
        boolean flag = false;
        table.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        try {
            if (tmpMonth.getAmountOfWeeks() == 5 && table.getModel().getRowCount() <= 5) {
                ((DefaultTableModel) table.getModel()).addRow(dayNames);
            }
            if (tmpMonth.getAmountOfWeeks() == 4 && table.getModel().getRowCount() > 4) {
                ((DefaultTableModel) table.getModel()).removeRow(tmpMonth.getAmountOfWeeks() + 1);
            }
        } catch (Exception e) { }
        //clear table
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            for (int j = 0; j < 7; j++) {
                table.setValueAt("", i, j);
            }
        }
        //load new dates
        int firstDay = tmpMonth.getNumOfFirstDay();
        int day = 1;
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            if (!flag) {
                flag = true;
            } else {
                firstDay = 0;
            }
            for (int j = firstDay; j < 7; j++) {
                int tasksNum = taskMap.calculateTotalTasksPerDay(new Model.Calendar.Date(day, tmpMonth.getMonth(), tmpMonth.getYear()), false);
                if (tasksNum > 0)
                    table.setValueAt(Integer.toString(day++) + taskStars(tasksNum), i, j);
                else {
                    table.setValueAt(Integer.toString(day++), i, j);
                }
                if (day - 1 == tmpMonth.getAmountOfDaysInMonth()) {
                    return;
                }
            }
        }
    }

    String taskStars(int numOfTasks) {
        StringBuilder str = new StringBuilder();
        str.append(" *");
        for (int i = 1; i < numOfTasks; i++) {
            str.append("*");
        }
        return str.toString();
    }

    public int getDate(String cellStr) {
        String[] str;
        str = cellStr.split(" ");
        return Integer.parseInt(str[0]);
    }
}