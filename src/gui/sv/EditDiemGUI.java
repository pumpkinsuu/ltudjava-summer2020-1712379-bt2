package gui.sv;

import dbUtil.DiemDAO;
import pojo.Diem;
import pojo.LopHoc;

import javax.swing.*;

/**
 * gui.sv
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 21-Jun-20 - 10:46 PM
 * @Description
 */
public class EditDiemGUI {
    private JTextField lopField;
    private JTextField svField;
    private JTextField gkField;
    private JTextField ckField;
    private JTextField khacField;
    private JTextField tongField;
    private JButton updateBtn;
    private JPanel panel;
    private JFrame frame;
    private final LopHoc lopHoc;

    public EditDiemGUI(LopHoc lopHoc) {
        this.lopHoc = lopHoc;

        updateBtn.addActionListener(e -> {
            if (this.gkField == null || this.ckField == null || this.khacField == null || this.tongField == null) {
                JOptionPane.showMessageDialog(this.updateBtn, "Không được bỏ trống!");
                return;
            }

            Diem diem = new Diem();
            try {
                double d;

                diem.setMaLopHoc(this.lopHoc.getMaLopHoc());
                d = Double.parseDouble(this.gkField.getText());
                if (d < 0 || d > 10) {
                    JOptionPane.showMessageDialog(this.gkField, "Điểm GK không hợp lệ");
                    return;
                }
                diem.setDiemGk(d);
                d = Double.parseDouble(this.ckField.getText());
                if (d < 0 || d > 10) {
                    JOptionPane.showMessageDialog(this.ckField, "Điểm CK không hợp lệ");
                    return;
                }
                diem.setDiemCk(d);
                d = Double.parseDouble(this.khacField.getText());
                if (d < 0 || d > 10) {
                    JOptionPane.showMessageDialog(this.khacField, "Điểm khác không hợp lệ");
                    return;
                }
                diem.setDiemKhac(d);
                d = Double.parseDouble(this.tongField.getText());
                if (d < 0 || d > 10) {
                    JOptionPane.showMessageDialog(this.tongField, "Điểm tổng không hợp lệ");
                    return;
                }
                diem.setDiemTong(d);

                if (!DiemDAO.update(diem)) {
                    JOptionPane.showMessageDialog(this.updateBtn, "Cập nhật thất bại!");
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.updateBtn, "Điểm không hợp lệ");
                System.err.println(ex.getMessage());
                return;
            }

            JOptionPane.showMessageDialog(this.updateBtn, "Cập nhật thành công!");
            this.frame.dispose();
        });
    }

    public void init() {
        if (!this.createLopBox()) {
            JOptionPane.showMessageDialog(this.frame, "Không tồn tại!");
        }

        this.frame = new JFrame("Sửa điểm sinh viên");
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    boolean createLopBox() {

        this.lopField.setText(this.lopHoc.getMaTkb());
        this.svField.setText(this.lopHoc.getSinhVien().getHoTen());
        this.gkField.setText(String.valueOf(this.lopHoc.getDiem().getDiemGk()));
        this.ckField.setText(String.valueOf(this.lopHoc.getDiem().getDiemCk()));
        this.khacField.setText(String.valueOf(this.lopHoc.getDiem().getDiemKhac()));
        this.tongField.setText(String.valueOf(this.lopHoc.getDiem().getDiemTong()));

        return true;
    }
}
