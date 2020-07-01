package gui.sv;

import dbUtil.SinhVienDAO;
import pojo.SinhVien;
import util.MouseAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * gui.sv
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 30-Jun-20 - 3:44 PM
 * @Description
 */
public class TableListSv {
    public static void setTab(JTable table, JButton button, List<SinhVien> list, int mode) {
        String[] colName = new String[]{
                "STT", "MSSV", "Họ tên", "Giới tính", "CMND", "Lớp", "Password"
        };
        String[][] colVal = new String[list.size()][7];
        for (int i = 0; i < list.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = list.get(i).getMssv();
            colVal[i][2] = list.get(i).getHoTen();
            colVal[i][3] = list.get(i).getGioiTinh();
            colVal[i][4] = list.get(i).getCmnd();
            colVal[i][5] = list.get(i).getMaLop();
            colVal[i][6] = list.get(i).getPassword();
        }

        TableModel tableModel = new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return (column != 0) && mode == 1;
            }
        };
        table.setModel(tableModel);
        if (mode != 1)
            table.removeColumn(table.getColumnModel().getColumn(6));

        switch (mode) {
            case 1 -> TableListSv.setUpdateBtn(table, button);
            case 2 -> TableListSv.setRemoveBtn(table, button);
        }
    }

    static void setUpdateBtn(JTable table, JButton button) {
        button.setVisible(true);
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(button, "Chọn sinh viên!");
                return;
            }

            SinhVien sv = new SinhVien();
            sv.setMssv(table.getValueAt(row, 1).toString());
            sv.setHoTen(table.getValueAt(row, 2).toString());
            sv.setGioiTinh(table.getValueAt(row, 3).toString());
            sv.setCmnd(table.getValueAt(row, 4).toString());
            sv.setMaLop(table.getValueAt(row, 5).toString());
            sv.setPassword(table.getModel().getValueAt(row, 6).toString());

            JOptionPane.showMessageDialog(button, "Cập nhật "
                    + (SinhVienDAO.update(sv) ? "thành công!" : "thất bại!"));
        });
    }

    static void setRemoveBtn(JTable table, JButton button) {
        button.setText("Xóa");
        button.setVisible(true);
        button.addActionListener(e -> {
            int[] rows = table.getSelectedRows();
            if (rows.length == 0) {
                JOptionPane.showMessageDialog(button, "Chọn sinh viên!");
                return;
            }

            for (int row : rows) {
                if (!SinhVienDAO.remove(table.getValueAt(row, 1).toString())) {
                    JOptionPane.showMessageDialog(button, "Lỗi!");
                    break;
                }
                ((DefaultTableModel)table.getModel()).removeRow(row);
            }
        });
    }
}
