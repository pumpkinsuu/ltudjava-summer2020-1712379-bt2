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
    private final String type;

    public GetMaLopGUI(JPanel viewPanel, String type) {
        this.viewPanel = viewPanel;
        this.list = new ArrayList<>();
        this.type = type;
        this.textField.setComponentPopupMenu(PopMenu.getCP());

        selectBox.addActionListener(e -> {
            if (Objects.requireNonNull(this.selectBox.getSelectedItem()).toString() != null)
                this.textField.setText(this.selectBox.getSelectedItem().toString());
        });
        getBtn.addActionListener(e -> {
            if (this.textField.getText() == null) {
                JOptionPane.showMessageDialog(this.textField, "Nhập mã lớp!");
                return;
            }
            if (!this.list.contains(this.textField.getText())) {
                JOptionPane.showMessageDialog(this.textField, "Mã lớp không tồn tại!");
                return;
            }

            switch (this.type) {
                case "sv_lop" -> this.getSvLop();
                case "sv_lopHoc" -> this.getSvlopHoc();
                case "tkb_lop" -> this.getTkbLop();
                case "diem_lop" -> this.getDiemLop();
            }
        });
        backBtn.addActionListener(e -> {
            LayoutSwitch.back(this.viewPanel, this.panel);
        });
    }

    public void init() {
        this.initBox();
        if (this.list.isEmpty()) {
            JOptionPane.showMessageDialog(this.panel, "Không có danh sách!");
            return;
        }

        LayoutSwitch.next(this.viewPanel, this.panel);
    }

    void initBox() {
        switch (this.type) {
            case "sv_lop", "tkb_lop" -> this.initLop();
            case "sv_lopHoc", "diem_lop" -> this.initLopHoc();
        }
    }

    void initLop() {
        List<Lop> lops = LopDAO.getList();
        for (Lop lop : lops) {
            this.selectBox.addItem(lop.getMaLop());
            this.list.add(lop.getMaLop());
        }
    }

    void initLopHoc() {
        List<Tkb> tkbs = TkbDAO.getList();
        for (Tkb tkb : tkbs) {
            this.selectBox.addItem(tkb.getMaTkb());
            this.list.add(tkb.getMaTkb());
        }
    }

    void getSvLop() {
        ListGUI listGUI = new ListGUI(this.viewPanel);
        listGUI.init("sv_lop", this.textField.getText());
    }

    void getSvlopHoc() {
        ListGUI listGUI = new ListGUI(this.viewPanel);
        listGUI.init("sv_lopHoc", this.textField.getText());
    }

    void getTkbLop() {
        ListGUI listGUI = new ListGUI(this.viewPanel);
        listGUI.init("tkb_lop", this.textField.getText());
    }

    void getDiemLop() {
        ListGUI listGUI = new ListGUI(this.viewPanel);
        listGUI.init("diem_lop", this.textField.getText());
    }
}
