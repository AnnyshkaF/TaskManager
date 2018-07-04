package gui.login;

import io.UserBaseIO;
import model.userbase.UserBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCalendarNameWindow {
    String newFileName;

    public EditCalendarNameWindow(UserBase userBase, String name, String oldFileName, LoginWindowListener loginWindowListener) {
        JDialog dialog = new JDialog();
        dialog.setSize(250, 150);
        dialog.setResizable(false);
        dialog.setTitle("Editing calendar name");
        JLabel labelUsername = new JLabel("calendar: ");
        JTextField textUsername = new JTextField(oldFileName);
        JButton buttonOK = new JButton("OK");
        JButton buttonCancel = new JButton("Cancel");

        dialog.setLayout(null);
        labelUsername.setBounds(10, 10, 100, 30);
        textUsername.setBounds(120, 10, 100, 30);
        buttonOK.setBounds(10, 50, 100, 30);
        buttonCancel.setBounds(120, 50, 100, 30);

        dialog.add(labelUsername);
        dialog.add(textUsername);
        dialog.add(buttonOK);
        dialog.add(buttonCancel);

        dialog.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFileName = textUsername.getText();
                if(newFileName == null || newFileName.equals("null")|| newFileName.equals("")){
                    JOptionPane.showMessageDialog(null, "Enter name!");
                    return;
                }
                userBase.editCalendaName(name, oldFileName, newFileName);
                new UserBaseIO().editCalendarFile(name, oldFileName, newFileName);
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
