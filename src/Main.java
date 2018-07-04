import gui.login.LoginWindow;
import model.userbase.UserBase;

public class Main {
    public static void main(String[] args){
        UserBase userBase = new UserBase();
        LoginWindow loginWindow = new LoginWindow(userBase);
    }
}
