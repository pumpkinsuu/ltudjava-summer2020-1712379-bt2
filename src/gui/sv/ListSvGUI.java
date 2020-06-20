package gui.sv;

import dbUtil.LopDAO;
import dbUtil.SinhVienDAO;
import dbUtil.TkbDAO;
import pojo.LopHoc;
import pojo.SinhVien;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * gui
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 10:31 PM
 * @Description
 */
public class ListSvGUI {
    private JButton closeBtn;
    private JTextField textField;
    private JPanel panel;
    private JTable table;
    private TableRowSorter<javax.swing.table.TableModel> tableRowSorter;
    private JFrame frame;

    public ListSvGUI() {

        closeBtn.addActionListener(e -> this.frame.dispose());
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    tableRowSorter.setRowFilter(null);
                } else {
                    tableRowSorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
    }

    public void init(String type, String id) {
        if (!this.createTab(type, id)) {
            JOptionPane.showMessageDialog(this.frame, "Không có sinh viên nào!");
            return;
        }

        String title = "Danh sách sinh viên";
        if (id != null)
            title += (' ' + id);

        this.frame = new JFrame(title);
        this.frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    boolean createTab(String type, String id) {
        List<SinhVien> list;

        switch (type) {
            case "lop" -> list = LopDAO.get(id).getSinhVien();
            case "lopHoc" -> {
                List<LopHoc> lopHocList = TkbDAO.get(id).getLopHoc();
                list = new ArrayList<>();
                for (LopHoc lopHoc : lopHocList)
                    list.add(lopHoc.getSinhVien());
            }
            case "all" -> list = SinhVienDAO.getList();
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
        TableModel tableModel = new DefaultTableModel(colVal, colName);
        this.table.setModel(tableModel);
        this.tableRowSorter = new TableRowSorter<>(tableModel);
        this.table.setRowSorter(this.tableRowSorter);

        return !list.isEmpty();
    }

}
