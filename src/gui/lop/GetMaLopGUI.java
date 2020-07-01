package gui.lop;

import dbUtil.LopDAO;
import dbUtil.TkbDAO;
import gui.ListGUI;
import pojo.Lop;
import pojo.Tkb;
import util.LayoutSwitch;
import util.PopMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * gui.sv
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 20-Jun-20 - 11:05 AM
 * @Description
 */
public class GetMaLopGUI {
    private JTextField textField;
    private JComboBox<String> selectBox;
    private JButton getBtn;
    private JPanel panel;
    private JButton backBtn;
    private final JPanel viewPanel;
    private final List<String> list;

    public GetMaLopGUI(JPanel viewPanel) {
        this.viewPanel = viewPanel;
        this.list = new ArrayList<>();
        this.textField.setComponentPopupMenu(PopMenu.getCP());

        selectBox.addActionListener(e -> {
            if (Objects.requireNonNull(this.selectBox.getSelectedItem()).toString() != null)
                this.textField.setText(this.selectBox.getSelectedItem().toString());
        });
        backBtn.addActionListener(e -> {
            LayoutSwitch.back(this.viewPanel, this.panel);
        });
    }

    public void init(String type, int mode) {
        List<Tkb> tkbs = TkbDAO.getList();
        for (Tkb tkb : tkbs) {
            this.selectBox.addItem(tkb.getMaTkb());
            this.list.add(tkb.getMaTkb());
        }

        if (this.list.isEmpty()) {
            JOptionPane.showMessageDialog(this.panel, "Không có danh sách!");
            return;
        }

        getBtn.addActionListener(e -> {
            if (this.textField.getText() == null) {
                JOptionPane.showMessageDialog(this.textField, "Nhập mã lớp!");
                return;
            }
            if (!this.list.contains(this.textField.getText())) {
                JOptionPane.showMessageDialog(this.textField, "Mã lớp không tồn tại!");
                return;
            }

            switch (type) {
                case "add_lop", "add_diem" -> {}
                default -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init(type, this.textField.getText(), mode);
                }
            }
        });

        LayoutSwitch.next(this.viewPanel, this.panel);
    }
}
