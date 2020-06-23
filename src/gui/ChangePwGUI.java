package gui;

import dbUtil.GiaoVuDao;
import dbUtil.SinhVienDAO;
import pojo.GiaoVu;
import pojo.SinhVien;
import util.LayoutSwitch;
import util.PopMenu;

import javax.swing.*;
import java.util.Arrays;

/**
 * gui
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 1:29 PM
 * @Description
 */
public class ChangePwGUI {
    private JPasswordField oldPWField;
    private JPasswordField newPWField;
    private JButton changeBtn;
    private JPanel panel;
    private JPasswordField curPWField;
    private JButton backBtn;
    private final JPanel viewPanel;

    public ChangePwGUI(JPanel viewPanel, String username, boolean type) {
        this.viewPanel = viewPanel;

        changeBtn.addActionListener(e -> {
            String oldPW = "";
            String newPW = "";

            if (this.oldPWField.getPassword().length > 0)
                oldPW = String.valueOf(this.oldPWField.getPassword());
            if (this.newPWField.getPassword().length > 0)
                newPW = String.valueOf(this.newPWField.getPassword());

            if (oldPW.isBlank() || newPW.isBlank())
                return;

            if (newPW.length() > 45) {
                JOptionPane.showMessageDialog(this.newPWField, "Mật khẩu mới quá dài!");
                return;
            }

            if (!Arrays.equals(this.newPWField.getPassword(), this.curPWField.getPassword())) {
                JOptionPane.showMessageDialog(this.oldPWField, "Mật khẩu mới không khớp!");
                return;
            }

            if (type) {
                GiaoVu gv = GiaoVuDao.get(username);

                if (!gv.getPassword().equals(oldPW)) {
                    JOptionPane.showMessageDialog(this.oldPWField, "Mật khẩu cũ không đúng!");
                    return;
                }

                gv.setPassword(newPW);

                if (!GiaoVuDao.update(gv)) {
                    JOptionPane.showMessageDialog(this.oldPWField, "Đổi mật khẩu thất bại!");
                    return;
                }
            }
            else {
                SinhVien sv = SinhVienDAO.get(username);

                if (!sv.getPassword().equals(oldPW)) {
                    JOptionPane.showMessageDialog(this.oldPWField, "Mật khẩu cũ không đúng!");
                    return;
                }
                sv.setPassword(newPW);

                if (!SinhVienDAO.update(sv)) {
                    JOptionPane.showMessageDialog(this.oldPWField, "Đổi mật khẩu thất bại!");
                    return;
                }
            }

            JOptionPane.showMessageDialog(this.panel, "Đổi mật khẩu thành công!");
            LayoutSwitch.back(this.viewPanel, this.panel);
        });
        backBtn.addActionListener(e -> {
            LayoutSwitch.back(this.viewPanel, this.panel);
        });
    }

    public void init() {
        this.oldPWField.setComponentPopupMenu(PopMenu.getCP());
        this.newPWField.setComponentPopupMenu(PopMenu.getCP());

        LayoutSwitch.next(this.viewPanel, this.panel);
    }

}
