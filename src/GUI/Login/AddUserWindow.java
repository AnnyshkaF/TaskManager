package GUI.Login;

import IO.UserBaseIO;
import Model.UserBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserWindow extends JDialog{
    private String name;
    private boolean flag = false;

    public AddUserWindow(UserBase userBase, LoginWindowListener loginWindowListener){
        setSize(300, 140);
        JLabel labelName = new JLabel("Name:");
        JTextField textFieldName = new JTextField();
        JButton buttonCreate = new JButton("Create");
        JButton buttonCancel = new JButton("Cancel");

        setLayout(null);

        labelName.setBounds(10, 10, 50, 30);
        textFieldName.setBounds(60, 10, 200, 30);
        buttonCreate.setBounds(40, 50, 100, 30);
        buttonCancel.setBounds(160, 50, 100, 30);

        add(labelName);
        add(textFieldName);
        add(buttonCreate);
        add(buttonCancel);   
        
        setVisible(true);

        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                if(userBase.getUserBase().containsKey(name)){
                    JOptionPane.showMessageDialog(null, "This name is already exists!");
                    return;
                }
                userBase.addUser(textFieldName.getText());
                new UserBaseIO().createNameFolder(name);
                dispose();
                loginWindowListener.updateNames();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
