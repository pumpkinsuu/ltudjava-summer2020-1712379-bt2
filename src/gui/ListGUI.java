package gui;

import dbUtil.LopHocDAO;
import pojo.LopHoc;
import util.TableList;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * gui
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 10:31 PM
 * @Description
 */
public class ListGUI {
    private JTextField textField;
    private JPanel panel;
    private JTable table;
    private JButton tkBtn;
    private JFrame frame;

    public ListGUI() {
    }

    public void init(String type, String id) {
        boolean flag = false;
        String title = "";

        switch (type) {
            case "sv_all", "sv_lop", "sv_lopHoc" -> {
                flag = TableList.setSvTab(this.table, this.textField, type, id);
                title = "Danh sách sinh viên";
            }
            case "tkb_mon", "tkb_all", "tkb_lop" -> {
                flag = TableList.setTkbTab(this.table, this.textField, type, id);
                title = "Thời khóa biểu";
            }
            case "diem_lop" -> {
                flag = this.setDiemLopGUI(type, id);
                title = "Danh sách điểm";
            }
        }

        if (!flag) {
            JOptionPane.showMessageDialog(this.frame, "Danh sách rỗng!");
            return;
        }


        if (id != null)
            title += (' ' + id);

        this.frame = new JFrame(title);
        this.frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    boolean setDiemLopGUI(String type, String id) {
        String hql = "from LopHoc lopHoc where lopHoc.maTkb = '" + id + "'";

        List<LopHoc> list = LopHocDAO.getAll(hql);
        if (list == null)
            return false;

        list.removeIf(lh -> lh.getDiem() == null);
        if (list.isEmpty())
            return false;

        int count = 0;
        int sum = list.size();
        for (LopHoc lh : list) {
            if (lh.getDiem().getDiemTong() >= 5 && lh.getDiem().getDiemTong() >= 3)
                ++count;
        }
        String tk = "Tổng: " + sum + '\n'
                + "Đậu: " + count + "(" + String.format("%d",(count * 100 / sum)) + "%)\n"
                + "Rớt: " + (sum - count) + "(" + String.format("%d",((sum - count) * 100 / sum)) + "%)";

        this.tkBtn.setVisible(true);
        this.tkBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(tkBtn, tk);
        });

        return TableList.setDiemTab(this.table, this.textField, list);
    }
}