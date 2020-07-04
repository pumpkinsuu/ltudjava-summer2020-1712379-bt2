package gui;

import dbUtil.GiaoVuDao;
import dbUtil.SinhVienDAO;
import pojo.GiaoVu;
import pojo.SinhVien;
import util.LayoutSwitch;
import util.OptionMsg;
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
    private JTextField userField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JPanel panel;
    private JButton closeBtn;
    private final JFrame frame;

    public LoginGUI(JFrame frame) {
        this.frame = frame;

        this.userField.setComponentPopupMenu(PopMenu.getCP());
        this.passwordField.setComponentPopupMenu(PopMenu.getCP());

        this.loginButton.addActionListener(e -> {
            String username = this.userField.getText();
            if (username == null || username.isBlank()) {
                OptionMsg.infoMsg(this.panel, "Nhập tên đăng nhập!");
                return;
            }
            String password = "";
            if (this.passwordField.getPassword().length > 0)
                password = String.valueOf(this.passwordField.getPassword());

            if (password.isBlank()) {
                OptionMsg.infoMsg(this.panel, "Nhập mật khẩu!");
                return;
            }
            this.check(username, password);
        });
        this.closeBtn.addActionListener(e -> this.frame.dispose());
    }

    public void init() {
        LayoutSwitch.setFrame(this.frame, this.panel, "Đăng nhập", 275, 275);
    }

    private void check(String username, String password) {
        GiaoVu gv = GiaoVuDao.get(username);
        if (gv != null && gv.getPassword().equals(password)) {
            MenuGvGUI menuGvGUI = new MenuGvGUI(this.frame, username);
            menuGvGUI.init();
            return;
        }
        SinhVien sv = SinhVienDAO.get(username);
        if (sv != null && sv.getPassword().equals(password)) {
            MenuSvGUI menuSvGUI = new MenuSvGUI(this.frame, sv);
            menuSvGUI.init();
            return;
        }
        OptionMsg.infoMsg(this.panel, "Đăng nhập sai, xin vui lòng thử lại!");
    }
}
