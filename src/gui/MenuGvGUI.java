package gui;

import gui.diem.DiemOptionGUI;
import gui.lop.LopOptionPanel;
import gui.sv.SvOptionGUI;
import gui.tkb.TkbOptionGUI;

import javax.swing.*;

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

        logoutBtn.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI(this.frame);
            loginGUI.init();
        });
        newPWsBtn.addActionListener(e -> {
            ChangePwGUI cPW = new ChangePwGUI(this.accPanel, this.username, true);
            cPW.init();
        });
    }

    public void init() {
        SvOptionGUI svOptionGUI = new SvOptionGUI(this.svPanel);
        svOptionGUI.init();
        TkbOptionGUI tkbOptionGUI = new TkbOptionGUI(this.tkbPanel);
        tkbOptionGUI.init();
        LopOptionPanel lopOptionPanel = new LopOptionPanel(this.lopPanel);
        lopOptionPanel.init();
        DiemOptionGUI diemOptionGUI = new DiemOptionGUI(this.diemPanel);
        diemOptionGUI.init();

        this.frame.setTitle("Quản lý sinh viên");
        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}
