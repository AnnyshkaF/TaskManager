package GUI.Login;

import IO.UserBaseIO;
import Model.UserBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUsernameWindow {
    String newName;

    public EditUsernameWindow(UserBase userBase, String oldName, LoginWindowListener loginWindowListener) {
        JDialog dialog = new JDialog();
        dialog.setSize(250, 150);
        dialog.setResizable(false);
        dialog.setTitle("Editing username");
        JLabel labelUsername = new JLabel("Username: ");
        JTextField textUsername = new JTextField(oldName);
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
                newName = textUsername.getText();
                if(newName == null || newName.equals("null") || newName.equals("")){
                    JOptionPane.showMessageDialog(null, "Enter name!");
                    return;
                }
                userBase.editNameFolder(oldName, newName );
                new UserBaseIO().editNameFolder(oldName, newName);
                loginWindowListener.updateNames();
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
