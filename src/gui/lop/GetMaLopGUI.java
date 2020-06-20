package gui.lop;

import dbUtil.LopDAO;
import dbUtil.TkbDAO;
import gui.sv.ListSvGUI;
import gui.tkb.ListTkbGUI;
import pojo.Lop;
import pojo.Tkb;

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
    private JButton closeBtn;
    private JPanel panel;
    private JFrame frame;
    private final List<String> list;
    private final String type;

    public GetMaLopGUI(String type) {
        this.list = new ArrayList<>();
        this.type = type;

        closeBtn.addActionListener(e -> {
            this.frame.dispose();
        });
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
                case "sv_lop" -> getSvLop();
                case "sv_lopHoc" -> getSvlopHoc();
                case "tkb_lop" -> getTkbLop();
            }
        });
    }

    public void init() {
        initBox();
        if (this.list.isEmpty()) {
            JOptionPane.showMessageDialog(this.panel, "Không có danh sách!");
            return;
        }

        this.frame = new JFrame("Chọn lớp");
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    void initBox() {
        switch (this.type) {
            case "sv_lop", "tkb_lop" -> initLop();
            case "sv_lopHoc", "diem_lopHoc" -> initLopHoc();
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
        ListSvGUI listSvGUI = new ListSvGUI();
        listSvGUI.init("lop", this.textField.getText());
    }

    void getSvlopHoc() {
        ListSvGUI listSvGUI = new ListSvGUI();
        listSvGUI.init("lopHoc", this.textField.getText());
    }

    void getTkbLop() {
        ListTkbGUI listTkbGUI = new ListTkbGUI();
        listTkbGUI.init(this.textField.getText());
    }
}
