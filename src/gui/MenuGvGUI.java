package gui;

import util.LayoutSwitch;

import javax.swing.*;
import java.awt.*;

/**
 * PACKAGE_NAME
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 11:19 PM
 * @Description
 */
public class MenuGvGUI {
    private JPanel panel;
    private JButton newPWsBtn;
    private JButton logoutBtn;
    private JPanel svPanel;
    private JPanel tkbPanel;
    private JPanel lopPanel;
    private JPanel diemPanel;
    private JPanel accPanel;
    private final JFrame frame;
    private final String username;

    public MenuGvGUI(JFrame frame, String username) {
        this.frame = frame;
        this.username = username;

        this.logoutBtn.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI(this.frame);
            loginGUI.init();
        });
        this.newPWsBtn.addActionListener(e -> {
            ChangePwGUI cPW = new ChangePwGUI(this.accPanel, this.username, true);
            cPW.init();
        });
    }

    public void init() {
        OptionGUI svOption = new OptionGUI(this.svPanel, "sv");
        svOption.init();
        OptionGUI tkbOption = new OptionGUI(this.tkbPanel, "tkb");
        tkbOption.init();
        OptionGUI lopOption = new OptionGUI(this.lopPanel, "lop");
        lopOption.init();
        OptionGUI diemOption = new OptionGUI(this.diemPanel, "diem");
        diemOption.init();

        LayoutSwitch.setFrame(this.frame, this.panel, "Quản lý sinh viên", 800, 600);
    }
}
