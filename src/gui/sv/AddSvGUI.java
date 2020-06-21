package gui.sv;

import dbUtil.LopDAO;
import dbUtil.SinhVienDAO;
import pojo.Lop;
import pojo.SinhVien;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

/**
 * gui.sv
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 11:34 PM
 * @Description
 */
public class AddSvGUI {
    private JRadioButton mRadioBtn;
    private JRadioButton fRadioBtn;
    private JTextField cmndField;
    private JComboBox<String> lopBox;
    private JButton addBtn;
    private JTextArea mssvField;
    private JTextField nameField;
    private JPanel AddSvPanel;
    private JFrame frame;

    public AddSvGUI() {
        addBtn.addActionListener(e -> {
            if (this.mssvField.getText() == null || this.mssvField.getText().isBlank()) {
                JOptionPane.showMessageDialog(this.mssvField, "Nhập MSSV!");
                return;
            }
            if (this.nameField.getText() == null || this.nameField.getText().isBlank()) {
                JOptionPane.showMessageDialog(this.nameField, "Nhập họ và !");
                return;
            }
            if (this.cmndField.getText() == null || this.cmndField.getText().isBlank()) {
                JOptionPane.showMessageDialog(this.cmndField, "Nhập CMND!");
                return;
            }
            if (!this.mRadioBtn.isSelected() && !this.fRadioBtn.isSelected()) {
                JOptionPane.showMessageDialog(this.mRadioBtn, "Chọn nam hoặc nữ!");
                return;
            }
            if (this.lopBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this.lopBox, "Chọn lớp!");
                return;
            }

            SinhVien sv = new SinhVien();
            sv.setMssv(this.mssvField.getText());
            sv.setHoTen(this.nameField.getText());
            sv.setGioiTinh(((this.mRadioBtn.isSelected()) ? "Nam" : "Nữ"));
            sv.setCmnd(this.cmndField.getText());
            sv.setMaLop(Objects.requireNonNull(this.lopBox.getSelectedItem()).toString());
            sv.setPassword(this.cmndField.getText());

            if (SinhVienDAO.add(sv)) {
                JOptionPane.showMessageDialog(this.AddSvPanel, "Thêm thành công!");
                this.frame.dispose();
                return;
            }

            JOptionPane.showMessageDialog(this.AddSvPanel, "Thêm thất bại!");
        });
    }

    public void init() {
        this.createLopBox();

        this.frame = new JFrame("Thêm sinh viên");
        this.frame.setContentPane(AddSvPanel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    void createLopBox() {
        List<Lop> lopList = LopDAO.getList();
        for (Lop lop : lopList)
            this.lopBox.addItem(lop.getMaLop());
    }

}
