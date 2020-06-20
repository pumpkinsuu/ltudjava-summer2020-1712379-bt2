package gui;

import dbUtil.GiaoVuDao;
import dbUtil.SinhVienDAO;
import pojo.GiaoVu;
import pojo.SinhVien;

import javax.swing.*;

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
    private JPanel changePWPanel;
    private JFrame frame;

    public ChangePwGUI(String username, boolean type) {

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

            JOptionPane.showMessageDialog(this.changePWPanel, "Đổi mật khẩu thành công!");
            this.frame.dispose();
        });
    }

    public void init() {
        frame = new JFrame("Login");
        frame.setContentPane(changePWPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
