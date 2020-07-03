package gui.tkb;

import dbUtil.TkbDAO;
import pojo.Tkb;
import util.OptionMsg;

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
        String[] colName = new String[]{
                "STT", "Mã lớp", "Mã môn", "Tên môn", "Phòng học"
        };
        Object[][] colVal = new Object[list.size()][5];

        for (int i = 0; i < list.size(); ++i) {
            colVal[i][0] = i + 1;
            colVal[i][1] = list.get(i).getMaLop();
            colVal[i][2] = list.get(i).getMaMon();
            colVal[i][3] = list.get(i).getMon().getTenMon();
            colVal[i][4] = list.get(i).getMon().getPhong();
        }

        TableModel tableModel = new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return switch (column) {
                    case 0, 1, 2 -> false;
                    default -> mode == 1;
                };
            }
        };
        table.setModel(tableModel);

        if (mode == 2)
            TableListTkb.setRemoveBtn(table, button);
    }

    private static void setRemoveBtn(JTable table, JButton button) {
        button.setText("Xóa");
        button.setVisible(true);
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                OptionMsg.infoMsg(table, "Chọn lớp học!");
                return;
            }
            String maTkb = table.getValueAt(row, 1).toString()
                    + '-' + table.getValueAt(row, 2).toString();

            if (OptionMsg.confirmMsg(table, "Xóa lớp " + maTkb + '?'))
                return;

            boolean flag = TkbDAO.remove(maTkb);
            OptionMsg.checkMsg(table, "Xóa", flag);
            if (flag)
                ((DefaultTableModel)table.getModel()).removeRow(row);
        });
    }
}