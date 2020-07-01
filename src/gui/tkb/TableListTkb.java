package gui.tkb;

import dbUtil.LopDAO;
import dbUtil.MonDAO;
import dbUtil.TkbDAO;
import pojo.Lop;
import pojo.Mon;
import pojo.Tkb;
import util.MouseAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * gui.tkb
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 30-Jun-20 - 4:17 PM
 * @Description
 */
public class TableListTkb {
    public static void setTab(JTable table, JButton button, List<Tkb> list, int mode) {
        List<Tkb> tkbs = TkbDAO.getList();

        String[] colName = new String[]{
                "STT", "Mã lớp", "Mã môn", "Tên môn", "Phòng học"
        };
        String[][] colVal = new String[tkbs.size()][5];

        for (int i = 0; i < tkbs.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = tkbs.get(i).getMaLop();
            colVal[i][2] = tkbs.get(i).getMaMon();
            colVal[i][3] = tkbs.get(i).getMon().getTenMon();
            colVal[i][4] = tkbs.get(i).getMon().getPhong();
        }

        TableModel tableModel = new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column != 0) && mode == 1;
            }
        };
        table.setModel(tableModel);

        switch (mode) {
            case 1 -> TableListTkb.setUpdateBtn(table, button);
            case 2 -> TableListTkb.setRemoveBtn(table, button);
        }
    }

    public static void setUpdateBtn(JTable table, JButton button) {
        button.setVisible(true);
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(button, "Chọn môn!");
                return;
            }

            Lop lop = new Lop();
            lop.setMaLop(table.getValueAt(row, 1).toString());
            LopDAO.add(lop);

            Mon mon = new Mon();
            mon.setMaMon(table.getValueAt(row, 2).toString());
            mon.setTenMon(table.getValueAt(row, 3).toString());
            mon.setPhong(table.getValueAt(row, 4).toString());
            MonDAO.add(mon);

            Tkb tkb = new Tkb();
            String maTkb = table.getValueAt(row, 1).toString()
                    + '-' + table.getValueAt(row, 2).toString();
            tkb.setMaTkb(maTkb);
            tkb.setMaLop(table.getValueAt(row, 1).toString());
            tkb.setMaMon(table.getValueAt(row, 2).toString());

            JOptionPane.showMessageDialog(button, "Cập nhật "
                    + (TkbDAO.add(tkb) ? "thành công!" : "thất bại!"));
        });
    }

    public static void setRemoveBtn(JTable table, JButton button) {
        button.setText("Xóa");
        button.setVisible(true);
        button.addActionListener(e -> {
            int[] rows = table.getSelectedRows();
            if (rows.length == 0) {
                JOptionPane.showMessageDialog(button, "Chọn môn!");
                return;
            }

            for (int row : rows) {
                String maTkb = table.getValueAt(row, 1).toString()
                        + '-' + table.getValueAt(row, 2).toString();

                if (!TkbDAO.remove(maTkb)) {
                    JOptionPane.showMessageDialog(button, "Lỗi!");
                    break;
                }
                ((DefaultTableModel)table.getModel()).removeRow(row);
            }
        });
    }
}