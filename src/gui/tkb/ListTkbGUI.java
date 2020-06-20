package gui.tkb;

import dbUtil.LopDAO;
import pojo.Tkb;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.EventObject;
import java.util.List;

/**
 * gui.tkb
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 19-Jun-20 - 1:45 PM
 * @Description
 */
public class ListTkbGUI {
    private JPanel panel;
    private JButton closeBtn;
    private JTextField textField;
    private JTable table;
    private TableRowSorter<TableModel> tableRowSorter;
    private JFrame frame;

    public ListTkbGUI() {

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

    public void init(String maLop) {
        if (!this.createTab(maLop)) {
            JOptionPane.showMessageDialog(this.panel, "Thời khóa biểu rỗng!");
            return;
        }

        this.frame = new JFrame("Thời khóa biểu lớp" + maLop);
        this.frame.setContentPane(this.panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    boolean createTab(String maLop) {
        List<Tkb> list = LopDAO.get(maLop).getTkb();

        String[] colName = new String[]{
                "STT", "Mã môn", "Tên môn", "Phòng học"
        };
        String[][] colVal = new String[list.size()][4];
        for (int i = 0; i < list.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = list.get(i).getMaMon();
            colVal[i][2] = list.get(i).getMon().getTenMon();
            colVal[i][3] = list.get(i).getMon().getPhong();
        }
        TableModel tableModel = new DefaultTableModel(colVal, colName);
        this.table.setModel(tableModel);
        this.tableRowSorter = new TableRowSorter<>(tableModel);
        this.table.setRowSorter(this.tableRowSorter);

        return !list.isEmpty();
    }

}
