package gui;

import dbUtil.GiaoVuDao;
import dbUtil.SinhVienDAO;
import pojo.GiaoVu;
import pojo.SinhVien;
import util.PopMenu;

import javax.swing.*;

/**
 * PACKAGE_NAME
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 9:11 PM
 * @Description
 */
public class LoginGUI {
    private JPanel loginPanel;
    private JTextField userField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JFrame frame;

    public LoginGUI() {
        loginButton.addActionListener(e -> {
            String username = this.userField.getText();
            if (username == null || username.isBlank()) {
                JOptionPane.showMessageDialog(this.userField, "Nhập username!");
                return;
            }

            String password = "";
            if (this.passwordField.getPassword().length > 0)
                password = String.valueOf(this.passwordField.getPassword());

            if (password.isBlank()) {
                JOptionPane.showMessageDialog(this.passwordField, "Nhập password!");
                return;
            }

            GiaoVu gv = GiaoVuDao.get(username);
            if (gv != null && gv.getPassword().equals(password)) {
                this.frame.dispose();

                MenuGvGUI menuGvGUI = new MenuGvGUI(username);
                menuGvGUI.init();

                return;
            }
            SinhVien sv = SinhVienDAO.get(username);
            if (sv != null && sv.getPassword().equals(password)) {
                this.frame.dispose();

                MenuSvGUI MenuSvGUI = new MenuSvGUI(sv);
                MenuSvGUI.init();

                return;
            }

            JOptionPane.showMessageDialog(this.loginPanel, "Đăng nhập sai, xin vui lòng thử lại!");
        });
    }

    public void init() {
        this.userField.setComponentPopupMenu(PopMenu.getCP());
        this.passwordField.setComponentPopupMenu(PopMenu.getCP());

        this.frame = new JFrame("Login");
        this.frame.setContentPane(this.loginPanel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(250,200);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

}
