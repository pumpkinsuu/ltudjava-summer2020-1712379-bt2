package util;

import dbUtil.*;
import pojo.LopHoc;
import pojo.Mon;
import pojo.SinhVien;
import pojo.Tkb;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * util
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 21-Jun-20 - 6:48 PM
 * @Description
 */
public class TableList {
    public static boolean setSvTab(JTable table, JTextField textField, String type, String id) {
        List<SinhVien> list;

        switch (type) {
            case "sv_lop" -> list = LopDAO.get(id).getSinhVien();
            case "sv_lopHoc" -> {
                List<LopHoc> lopHocList = TkbDAO.get(id).getLopHoc();
                list = new ArrayList<>();
                for (LopHoc lopHoc : lopHocList)
                    list.add(lopHoc.getSinhVien());
            }
            case "sv_all" -> list = SinhVienDAO.getList();
            default -> {
                return false;
            }
        }

        String[] colName = new String[]{
                "STT", "MSSV", "Họ tên", "Giới tính", "CMND", "Lớp"
        };
        String[][] colVal = new String[list.size()][6];
        for (int i = 0; i < list.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = list.get(i).getMssv();
            colVal[i][2] = list.get(i).getHoTen();
            colVal[i][3] = list.get(i).getGioiTinh();
            colVal[i][4] = list.get(i).getCmnd();
            colVal[i][5] = list.get(i).getMaLop();
        }
        TableModel tableModel = new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        TableSearch.set(table, tableModel, textField);

        return !list.isEmpty();
    }

    public static boolean setTkbTab(JTable table, JTextField textField, String type, String id) {
        TableModel tableModel;
        switch (type) {
            case "tkb_mon" -> tableModel = tkbMon();
            case "tkb_all" -> tableModel = tkbAll();
            case "tkb_lop" -> tableModel = tkbLop(id);
            default -> {
                return false;
            }
        }

        TableSearch.set(table, tableModel, textField);

        return tableModel.getRowCount() != 0;
    }

    static TableModel tkbMon() {
        List<Mon> mons = MonDAO.getList();

        String[] colName = new String[]{
                "STT", "Mã môn", "Tên môn", "Phòng học"
        };
        String[][] colVal = new String[mons.size()][4];
        for (int i = 0; i < mons.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = mons.get(i).getMaMon();
            colVal[i][2] = mons.get(i).getTenMon();
            colVal[i][3] = mons.get(i).getPhong();
        }

        return new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
    }
    static TableModel tkbAll() {
        List<Tkb> tkbs = TkbDAO.getList();

        String[]colName = new String[]{
                "STT", "Mã lớp", "Mã môn", "Tên môn", "Phòng học"
        };
        String[][]colVal = new String[tkbs.size()][5];

        for (int i = 0; i < tkbs.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = tkbs.get(i).getMaTkb();
            colVal[i][2] = tkbs.get(i).getMaMon();
            colVal[i][3] = tkbs.get(i).getMon().getTenMon();
            colVal[i][4] = tkbs.get(i).getMon().getPhong();
        }

        return new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
    }
    static TableModel tkbLop(String maLop) {
        List<Tkb> tkbs = LopDAO.get(maLop).getTkb();

        String[] colName = new String[]{
                "STT", "Mã môn", "Tên môn", "Phòng học"
        };
        String[][]colVal = new String[tkbs.size()][4];

        for (int i = 0; i < tkbs.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = tkbs.get(i).getMaMon();
            colVal[i][2] = tkbs.get(i).getMon().getTenMon();
            colVal[i][3] = tkbs.get(i).getMon().getPhong();
        }

        return new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
    }

    public static boolean setDiemTab(JTable table, JTextField textField, List<LopHoc> list) {
        String[] colName = new String[]{
                "STT", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm Tổng", "Đậu/Rớt"
        };
        String[][] colVal = new String[list.size()][9];
        for (int i = 0; i < list.size(); ++i) {

            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = list.get(i).getMssv();
            colVal[i][2] = list.get(i).getSinhVien().getHoTen();
            colVal[i][3] = String.valueOf(list.get(i).getDiem().getDiemGk());
            colVal[i][4] = String.valueOf(list.get(i).getDiem().getDiemCk());
            colVal[i][5] = String.valueOf(list.get(i).getDiem().getDiemKhac());
            colVal[i][6] = String.valueOf(list.get(i).getDiem().getDiemTong());
            colVal[i][7] = (list.get(i).getDiem().getDiemTong() >= 5 ? "Đậu" : "Rớt");
        }
        TableModel tableModel = new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        TableSearch.set(table, tableModel, textField);

        return !list.isEmpty();
    }
}
