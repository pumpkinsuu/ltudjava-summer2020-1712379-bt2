package gui.diem;

import dbUtil.DiemDAO;
import pojo.Diem;
import pojo.LopHoc;
import util.OptionMsg;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * gui.diem
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 30-Jun-20 - 9:42 PM
 * @Description
 */
public class TableListDiem {
    public static void setTab(JTable table, JButton button, List<LopHoc> list, int mode) {
        String[] colName = new String[]{
                "STT", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm Tổng", "Đậu/Rớt", "Lớp"
        };
        Object[][] colVal = new Object[list.size()][9];
        for (int i = 0; i < list.size(); ++i) {

            colVal[i][0] = i + 1;
            colVal[i][1] = list.get(i).getMssv();
            colVal[i][2] = list.get(i).getSinhVien().getHoTen();
            colVal[i][3] = list.get(i).getDiem().getDiemGk();
            colVal[i][4] = list.get(i).getDiem().getDiemCk();
            colVal[i][5] = list.get(i).getDiem().getDiemKhac();
            colVal[i][6] = list.get(i).getDiem().getDiemTong();
            colVal[i][7] = (list.get(i).getDiem().getDiemTong() >= 5 ? "Đậu" : "Rớt");
            colVal[i][8] = list.get(i).getMaLopHoc();
        }
        TableModel tableModel = new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return switch (column) {
                    case 0, 1, 2, 7 -> false;
                    default -> mode == 1;
                };
            }
            final Class<?>[] types = new Class [] {
                    Integer.class, String.class, String.class,
                    Double.class, Double.class, Double.class, Double.class,
                    String.class, String.class
            };

            @Override
            public Class<?> getColumnClass(int col) {
                return types [col];
            }
        };
        table.setModel(tableModel);
        table.removeColumn(table.getColumnModel().getColumn(8));

        switch (mode) {
            case 0 -> setViewBtn(table, button, list);
            case 1 -> setUpdateBtn(table, button);
            case 2 -> setRemoveBtn(table, button);
        }
        button.setVisible(true);
    }

    private static void setViewBtn(JTable table, JButton button, List<LopHoc> list) {
        int count = 0;
        int sum = list.size();
        for (LopHoc lh : list) {
            if (lh.getDiem().getDiemTong() >= 5 && lh.getDiem().getDiemTong() >= 3)
                ++count;
        }
        String tk = "Tổng: " + sum + '\n'
                + "Đậu: " + count + "(" + String.format("%d", (count * 100 / sum)) + "%)\n"
                + "Rớt: " + (sum - count) + "(" + String.format("%d", ((sum - count) * 100 / sum)) + "%)";

        button.setText("Thống kê");
        button.addActionListener(e -> OptionMsg.infoMsg(table, tk));
    }

    private static void setUpdateBtn(JTable table, JButton button) {
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                OptionMsg.infoMsg(table, "Chọn sinh viên!");
                return;
            }
            if (OptionMsg.confirmMsg(table, "Cập nhật điểm của " + table.getValueAt(row, 2) + '?'))
                return;

            table.setValueAt(((Double) table.getValueAt(row, 6) >= 5 ? "Đậu" : "Rớt"), row, 7);

            Diem diem = new Diem();
            diem.setMaLopHoc(table.getModel().getValueAt(row, 8).toString());
            diem.setDiemGk((Double) table.getValueAt(row, 3));
            diem.setDiemCk((Double) table.getValueAt(row, 4));
            diem.setDiemKhac((Double) table.getValueAt(row, 5));
            diem.setDiemTong((Double) table.getValueAt(row, 6));

            OptionMsg.checkMsg(table, "Cập nhật", DiemDAO.update(diem));
        });
    }

    private static void setRemoveBtn(JTable table, JButton button) {
        button.setText("Xóa");
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                OptionMsg.infoMsg(table, "Chọn sinh viên!");
                return;
            }
            if (OptionMsg.confirmMsg(table, "Xóa điểm của " + table.getValueAt(row, 2) + '?'))
                return;

            boolean flag = DiemDAO.remove(table.getModel().getValueAt(row, 8).toString());
            OptionMsg.checkMsg(table, "Xóa", flag);
            if (flag)
                ((DefaultTableModel)table.getModel()).removeRow(row);
        });
    }
}
