package gui.sv;

import dbUtil.SinhVienDAO;
import pojo.SinhVien;
import util.OptionMsg;

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
        Object[][] colVal = new Object[list.size()][7];
        for (int i = 0; i < list.size(); ++i) {
            colVal[i][0] = i + 1;
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
                return column != 0 && column != 1 && mode == 1;
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

    private static void setUpdateBtn(JTable table, JButton button) {
        button.setVisible(true);
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                OptionMsg.infoMsg(table, "Chọn sinh viên!");
                return;
            }
            if (OptionMsg.confirmMsg(table, "Cập nhật thông tin của " + table.getValueAt(row, 2) + '?'))
                return;

            SinhVien sv = new SinhVien();
            sv.setMssv(table.getValueAt(row, 1).toString());
            sv.setHoTen(table.getValueAt(row, 2).toString());
            sv.setGioiTinh(table.getValueAt(row, 3).toString());
            sv.setCmnd(table.getValueAt(row, 4).toString());
            sv.setMaLop(table.getValueAt(row, 5).toString());
            sv.setPassword(table.getModel().getValueAt(row, 6).toString());

            OptionMsg.checkMsg(table, "Cập nhật", SinhVienDAO.update(sv));
        });
    }

    private static void setRemoveBtn(JTable table, JButton button) {
        button.setText("Xóa");
        button.setVisible(true);
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                OptionMsg.infoMsg(table, "Chọn sinh viên!");
                return;
            }
            if (OptionMsg.confirmMsg(table, "Xóa " + table.getValueAt(row, 2) + '?'))
                return;

            boolean flag = SinhVienDAO.remove(table.getModel().getValueAt(row, 1).toString());
            OptionMsg.checkMsg(table, "Xóa", flag);
            if (flag)
                ((DefaultTableModel)table.getModel()).removeRow(row);
        });
    }
}
