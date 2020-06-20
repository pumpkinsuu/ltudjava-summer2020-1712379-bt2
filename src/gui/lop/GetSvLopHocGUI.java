package gui.lop;

import dbUtil.LopHocDAO;
import dbUtil.MonDAO;
import dbUtil.SinhVienDAO;
import dbUtil.TkbDAO;
import pojo.LopHoc;
import pojo.Mon;
import pojo.SinhVien;
import pojo.Tkb;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton closeBtn;
    private JPanel panel;
    private JLabel lopLable;
    private JFrame frame;
    private final List<String> list;
    private final String type;

    public GetSvLopHocGUI(String type) {
        this.list = new ArrayList<>();
        this.type = type;

        closeBtn.addActionListener(e -> this.frame.dispose());
        addBtn.addActionListener(e -> {
            if (this.svField == null || this.lopField == null) {
                JOptionPane.showMessageDialog(this.panel, "Không được bỏ trống!");
                return;
            }
            if (!this.list.contains(this.lopField.getText())) {
                switch (this.type) {
                    case "addLopHoc" -> JOptionPane.showMessageDialog(this.lopField, "Lớp học không tồn tại!");
                    case "removeLopHoc" -> JOptionPane.showMessageDialog(this.lopField, "Môn học không tồn tại!");
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
            }
        });
    }

    public void init() {
        initBox();
        if (this.list.isEmpty()) {
            JOptionPane.showMessageDialog(this.panel, "Chưa có lớp học!");
            return;
        }

        this.frame = new JFrame("Chọn sinh viên và lớp học");
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
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
}
