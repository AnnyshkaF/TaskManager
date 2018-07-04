package gui.login;

import gui.calendar.CalendarWindow;
import io.TaskIO;
import io.UserBaseIO;
import model.task.TaskMap;
import model.userbase.UserBase;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class LoginWindow {
    private String name;
    private String filename;
    private JComboBox<String> comboBoxUsersNames = new JComboBox<>();
    private JComboBox<String> comboBoxUsersFiles = new JComboBox<>();
    private JButton buttonOk = new JButton("Open calendar");
    private JButton buttonAddUser = new JButton("Add");
    private JButton buttonDeleteUser = new JButton("Delete");
    private JButton buttonEditUser= new JButton("Edit");
    private JButton buttonAddCalendar = new JButton("Add");
    private JButton buttonDeleteCalendar = new JButton("Delete");
    private JButton buttonEditCalendar = new JButton("Edit");

    public LoginWindow(UserBase userBase) {
        JFrame frame = new JFrame();
        frame.setTitle("login");
        frame.setResizable(false);
        frame.setSize(570, 180);

        UserBaseIO loginIO = new UserBaseIO();
        loginIO.loadUsersFiles(userBase);
        LoginWindowListener loginWindowListener = new LoginWindowListener(this, userBase);

        if (userBase.getUsersNames() != null) {
            String[] names = userBase.getUsersNames();
            for (String i : names) {
                comboBoxUsersNames.addItem(i);
            }
            name = names[0];
        }
        update(userBase, name);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        buttonAddUser.setBounds(10, 50, 80, 30);
        buttonEditUser.setBounds(100, 50, 80, 30);
        buttonDeleteUser.setBounds(190, 50, 80, 30);
        comboBoxUsersNames.setBounds(10, 10, 260, 30);
        buttonAddCalendar.setBounds(280, 50, 80, 30);
        buttonEditCalendar.setBounds(370, 50, 80, 30);
        buttonDeleteCalendar.setBounds(460, 50, 80, 30);
        comboBoxUsersFiles.setBounds(280, 10, 260, 30);
        buttonOk.setBounds(205, 90, 130, 30);

        frame.add(buttonAddUser);
        frame.add(buttonDeleteUser);
        frame.add(buttonEditUser);
        frame.add(comboBoxUsersNames);
        frame.add(buttonAddCalendar);
        frame.add(buttonDeleteCalendar);
        frame.add(buttonEditCalendar);
        frame.add(comboBoxUsersFiles);
        frame.add(buttonOk);

        frame.setVisible(true);

        buttonAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserWindow newUserWindow = new AddUserWindow(userBase, loginWindowListener);
            }
        });

        buttonEditUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxUsersNames.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(null, "Username to edit is not chosen!");
                    return;
                }
                EditUsernameWindow editUsernameWindow = new EditUsernameWindow(userBase, name, loginWindowListener);
            }
        });

        buttonDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxUsersNames.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(null, "User to delete is not chosen!");
                    return;
                }
                userBase.deleteUser(name);
                new UserBaseIO().deleteUserFolder(name);
                update(userBase);
            }
        });

        buttonAddCalendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalendarFileWindow CalendarFileWindow = new CalendarFileWindow(userBase, name, loginWindowListener);
            }
        });

        buttonEditCalendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxUsersFiles.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(null, "File to edit is not chosen!");
                    return;
                }
                EditCalendarNameWindow editCalendarNameWindow = new EditCalendarNameWindow(userBase, name, filename, loginWindowListener);
            }
        });

        buttonDeleteCalendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxUsersFiles.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(null, "File to delete is not chosen!");
                    return;
                }
                userBase.deleteCalendar(name, filename);
                new UserBaseIO().deleteCalendarFile(name, filename);
                update(userBase, name);
            }
        });
        comboBoxUsersNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = String.valueOf(comboBoxUsersNames.getSelectedItem());
                if (userBase.getUsersCalendars(name) == null) {
                    comboBoxUsersFiles.removeAllItems();
                    return;
                }
                if (userBase.getUsersCalendars(name) != null) {
                    comboBoxUsersFiles.removeAllItems();
                    String[] files = userBase.getUsersCalendars(name);
                    for (String file : files) {
                        comboBoxUsersFiles.addItem(file);
                    }
                }
            }
        });
        comboBoxUsersFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filename = String.valueOf(comboBoxUsersFiles.getSelectedItem());
                if (filename.equals("Add new calendar")) {
                    CalendarFileWindow newUserWindow = new CalendarFileWindow(userBase, name, loginWindowListener);
                }
            }
        });

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(filename == null || filename.equals("null")){
                    JOptionPane.showMessageDialog(null, "calendar is not chosen!");
                    return;
                }
                String fileCalendar = "saves/months.xml";
                String fileTask = new UserBaseIO().getFilePath(name, filename);

                TaskMap taskMap = new TaskMap();
                if(new File(fileTask).exists()) {
                    try {
                        new TaskIO().loadTasksFromFile(fileTask, taskMap);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                CalendarWindow calendarWindow = new CalendarWindow(taskMap, fileCalendar, fileTask);
                //frame.dispose();
            }
        });
    }
    public String getName(){
        return name;
    }

    public String getFilename() {
        return filename;
    }

    //names
    void update(UserBase userBase) {
        comboBoxUsersNames.removeAllItems();
        if (userBase.getUsersNames() != null) {
            String[] users = userBase.getUsersNames();
            for (String user : users) {
                comboBoxUsersNames.addItem(user);
            }
        }
    }
    //files
    void update(UserBase userBase, String name){
        comboBoxUsersFiles.removeAllItems();
        if (userBase.getUsersCalendars(name) != null) {
            String[] files = userBase.getUsersCalendars(name);
            for (String i : files) {
                comboBoxUsersFiles.addItem(i);
            }
            filename = files[0];
        }
    }
}
