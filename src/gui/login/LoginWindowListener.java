package gui.login;

import model.userbase.UserBase;

public class LoginWindowListener {
    private LoginWindow loginWindow;
    private UserBase userBase;

    public LoginWindowListener(LoginWindow loginWindow, UserBase userBase) {
        this.loginWindow = loginWindow;
        this.userBase = userBase;
    }

    public void updateNames() {
        loginWindow.update(userBase);
    }
    public void updateFiles(){
        loginWindow.update(userBase, loginWindow.getName());
    }
}
