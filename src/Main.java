import gui.LoginGUI;

import javax.swing.*;

/**
 * PACKAGE_NAME
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 07-Jun-20 - 11:35 PM
 * @Description
 */
public class Main {
    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) { }

        JFrame frame = new JFrame("Đăng nhập");
        LoginGUI loginGUI = new LoginGUI(frame);
        loginGUI.init();
    }
}
