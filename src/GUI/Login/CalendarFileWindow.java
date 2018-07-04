package gui.login;

import model.userbase.UserBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarFileWindow {
    private String filename;

    public CalendarFileWindow(UserBase userBase, String name, LoginWindowListener loginWindowListener){
        JDialog dialog = new JDialog();
        dialog.setSize(300, 140);
        JLabel labelName = new JLabel("calendar:");
        JTextField textFieldName = new JTextField();
        JButton buttonOk = new JButton("Create");
        JButton buttonCancel = new JButton("Cancel");

        dialog.setLayout(null);

        labelName.setBounds(10, 10, 60, 30);
        textFieldName.setBounds(70, 10, 200, 30);
        buttonOk.setBounds(30, 50, 100, 30);
        buttonCancel.setBounds(140, 50, 100, 30);

        dialog.add(labelName);
        dialog.add(textFieldName);
        dialog.add(buttonOk);
        dialog.add(buttonCancel);

        dialog.setVisible(true);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filename = textFieldName.getText();
                if(filename.equals("")){
                    JOptionPane.showMessageDialog(null, "Enter name!");
                    return;
                }
                if(userBase.getUserBase().containsKey(filename)){
                    JOptionPane.showMessageDialog(null, "This calendar name is already exists!");
                    return;
                }
                userBase.addCalendar(name, filename);
                loginWindowListener.updateFiles();
                dialog.dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }
}
