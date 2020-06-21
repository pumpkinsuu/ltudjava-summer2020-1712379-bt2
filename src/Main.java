import gui.LoginGUI;
import gui.MenuGvGUI;

/**
 * PACKAGE_NAME
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 07-Jun-20 - 11:35 PM
 * @Description
 */
public class Main {
    public static void main(String[] args) {

        //LoginGUI loginGUI = new LoginGUI();
        //loginGUI.init();
        MenuGvGUI menuGvGUI = new MenuGvGUI("admin");
        menuGvGUI.init();
    }
}
