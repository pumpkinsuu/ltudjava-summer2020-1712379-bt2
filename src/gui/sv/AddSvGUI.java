package gui.sv;

import dbUtil.LopDAO;
import dbUtil.SinhVienDAO;
import pojo.Lop;
import pojo.SinhVien;
import util.LayoutSwitch;
import util.OptionMsg;

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
    private JPanel panel;
    private JButton backBtn;
    private final JPanel viewPanel;

    public AddSvGUI(JPanel viewPanel) {
        this.viewPanel = viewPanel;

        this.addBtn.addActionListener(e -> {
            if (this.mssvField.getText() == null || this.mssvField.getText().isBlank()) {
                OptionMsg.infoMsg(this.panel, "Nhập MSSV!");
                return;
            }
            if (SinhVienDAO.get(this.mssvField.getText()) != null) {
                OptionMsg.infoMsg(this.panel, "Mã số sinh viên đã tồn tại!");
                return;
            }
            if (this.nameField.getText() == null || this.nameField.getText().isBlank()) {
                OptionMsg.infoMsg(this.panel, "Nhập họ và tên!");
                return;
            }
            if (this.cmndField.getText() == null || this.cmndField.getText().isBlank()) {
                OptionMsg.infoMsg(this.panel, "Nhập CMND!");
                return;
            }
            if (!this.mRadioBtn.isSelected() && !this.fRadioBtn.isSelected()) {
                OptionMsg.infoMsg(this.panel, "Chọn nam hoặc nữ!");
                return;
            }
            if (this.lopBox.getSelectedItem() == null) {
                OptionMsg.infoMsg(this.panel, "Chọn lớp!");
                return;
            }

            SinhVien sv = new SinhVien();
            sv.setMssv(this.mssvField.getText());
            sv.setHoTen(this.nameField.getText());
            sv.setGioiTinh(((this.mRadioBtn.isSelected()) ? "Nam" : "Nữ"));
            sv.setCmnd(this.cmndField.getText());
            sv.setMaLop(Objects.requireNonNull(this.lopBox.getSelectedItem()).toString());
            sv.setPassword(this.cmndField.getText());

            OptionMsg.checkMsg(this.panel, "Thêm", SinhVienDAO.add(sv));
        });
        this.backBtn.addActionListener(e -> {
            LayoutSwitch.back(this.viewPanel, this.panel);
        });
    }

    public void init() {
        this.createLopBox();
        LayoutSwitch.next(this.viewPanel, this.panel);
    }

    private void createLopBox() {
        List<Lop> lopList = LopDAO.getList();
        for (Lop lop : lopList)
            this.lopBox.addItem(lop.getMaLop());
    }

}
