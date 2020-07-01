package gui.lop;

import dbUtil.*;
import gui.ListGUI;
import gui.diem.UpdateDiemGUI;
import pojo.LopHoc;
import pojo.Mon;
import pojo.SinhVien;
import pojo.Tkb;
import util.LayoutSwitch;
import util.PopMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * gui.lop
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 20-Jun-20 - 12:43 PM
 * @Description
 */
public class GetSvLopHocGUI {
    private JTextField svField;
    private JTextField lopField;
    private JButton addBtn;
    private JPanel panel;
    private JLabel lopLable;
    private JButton svBtn;
    private JButton lopBtn;
    private JButton backBtn;
    private final JPanel viewPanel;
    private final List<String> list;
    private final String type;

    public GetSvLopHocGUI(JPanel viewPanel, String type) {
        this.viewPanel = viewPanel;
        this.list = new ArrayList<>();
        this.type = type;
        this.svField.setComponentPopupMenu(PopMenu.getCP());
        this.lopField.setComponentPopupMenu(PopMenu.getCP());

        addBtn.addActionListener(e -> {
            if (this.svField == null || this.lopField == null) {
                JOptionPane.showMessageDialog(this.panel, "Không được bỏ trống!");
                return;
            }
            if (!this.list.contains(this.lopField.getText())) {
                switch (this.type) {
                    case "addLopHoc" -> JOptionPane.showMessageDialog(this.lopField, "Lớp học không tồn tại!");
                    case "removeLopHoc", "updateDiem" -> JOptionPane.showMessageDialog(this.lopField, "Môn học không tồn tại!");
                }
                return;
            }

            SinhVien sv= SinhVienDAO.get(this.svField.getText());

            if (sv == null) {
                JOptionPane.showMessageDialog(this.svField, "Sinh viên không tồn tại!");
                return;
            }

            switch (this.type) {
                case "addLopHoc" -> addLopHoc(sv);
                case "removeLopHoc" -> removeLopHoc(sv);
                case "updateDiem" -> updateDiem(sv);
            }
        });
        svBtn.addActionListener(e -> {
            ListGUI listGUI = new ListGUI(this.viewPanel);
            listGUI.init("sv_all", null, 0);
        });
        lopBtn.addActionListener(e -> {
            ListGUI listGUI = new ListGUI(this.viewPanel);
            switch (this.type) {
                case "addLopHoc" -> listGUI.init("tkb_all", null, 0);
                case "removeLopHoc", "updateDiem" -> listGUI.init("tkb_mon", null, 0);
            }
        });
        backBtn.addActionListener(e -> {
            LayoutSwitch.back(this.viewPanel, this.panel);
        });
    }

    public void init() {
        initBox();
        if (this.list.isEmpty()) {
            JOptionPane.showMessageDialog(this.panel, "Chưa có lớp học!");
            return;
        }

        LayoutSwitch.next(this.viewPanel, this.panel);
    }

    void initBox() {
        switch (this.type) {
            case "addLopHoc" ->{
                List<Tkb> tkbs = TkbDAO.getList();
                for (Tkb tkb : tkbs) {
                    this.list.add(tkb.getMaTkb());
                }
            }
            case "removeLopHoc" -> {
                this.lopLable.setText("Môn học");
                this.addBtn.setText("Xóa");
                List<Mon> mos = MonDAO.getList();
                for (Mon mon : mos) {
                    this.list.add(mon.getMaMon());
                }
            }
            case "updateDiem" -> {
                this.lopLable.setText("Môn học");
                this.addBtn.setText("Chọn");
                List<Mon> mos = MonDAO.getList();
                for (Mon mon : mos) {
                    this.list.add(mon.getMaMon());
                }
            }
        }

    }

    void addLopHoc(SinhVien sv) {
        String maLopHoc = this.lopField.getText().substring(this.lopField.getText().indexOf('-') + 1)
                + '-' + sv.getMssv();

        if (LopHocDAO.get(maLopHoc) != null) {
            JOptionPane.showMessageDialog(this.svField, "Sinh viên đã đăng ký môn học!");
            return;
        }

        LopHoc lopHoc = new LopHoc();
        lopHoc.setMaLopHoc(maLopHoc);
        lopHoc.setMssv(sv.getMssv());
        lopHoc.setMaTkb(this.lopField.getText());

        if (LopHocDAO.add(lopHoc))
            JOptionPane.showMessageDialog(panel, "Đăng ký thành công!");
        else
            JOptionPane.showMessageDialog(panel, "Đăng ký viên thất bại!");
    }

    void removeLopHoc(SinhVien sv) {
        String maLopHoc = this.lopField.getText() + '-' + sv.getMssv();

        if (LopHocDAO.get(maLopHoc) == null) {
            JOptionPane.showMessageDialog(this.svField, "Sinh viên chưa đăng ký lớp học!");
            return;
        }

        if (LopHocDAO.remove(maLopHoc))
            JOptionPane.showMessageDialog(panel, "Rút sinh viên thành công!");
        else
            JOptionPane.showMessageDialog(panel, "Rút sinh viên thất bại!");
    }

    void updateDiem(SinhVien sv) {
        String maLopHoc = this.lopField.getText() + '-' + sv.getMssv();
        LopHoc lopHoc = LopHocDAO.get(maLopHoc);

        if (lopHoc == null) {
            JOptionPane.showMessageDialog(this.svField, "Sinh viên chưa đăng ký lớp học!");
            return;
        }

        if (lopHoc.getDiem() == null) {
            JOptionPane.showMessageDialog(this.svField, "Sinh viên chưa có điểm lớp học!");
            return;
        }

        UpdateDiemGUI updateDiemGUI = new UpdateDiemGUI(this.viewPanel, lopHoc);
        updateDiemGUI.init();
    }
}
