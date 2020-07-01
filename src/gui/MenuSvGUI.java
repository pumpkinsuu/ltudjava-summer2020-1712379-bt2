package gui;

import dbUtil.LopHocDAO;
import pojo.LopHoc;
import pojo.SinhVien;
import util.MouseAction;
import util.PopMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * gui
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 22-Jun-20 - 10:10 AM
 * @Description
 */
public class MenuSvGUI {
    private JButton newPWsBtn;
    private JButton logoutBtn;
    private JTextField mssvField;
    private JTextField nameField;
    private JTextField gioiField;
    private JTextField cmndField;
    private JTextField lopField;
    private JPanel panel;
    private JLabel tkbLable;
    private JLabel diemLabel;
    private JScrollPane tkbPanel;
    private JScrollPane diemPanel;
    private JPanel accPanel;
    private JPanel svPanel;
    private JTextField tkbField;
    private JTable tkbTab;
    private JTable diemTab;
    private JTextField diemField;
    private JLabel tkbLabel;
    private final JFrame frame;
    private final SinhVien sv;

    public MenuSvGUI(JFrame frame, SinhVien sv) {
        this.frame = frame;
        this.sv = sv;

        logoutBtn.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI(this.frame);
            loginGUI.init();
        });
        newPWsBtn.addActionListener(e -> {
            ChangePwGUI cPW = new ChangePwGUI(this.accPanel, this.sv.getMssv(), false);
            cPW.init();
        });
    }

    public void init() {
        this.createSv();
        String hql = "from LopHoc lopHoc where lopHoc.mssv = '" + this.sv.getMssv() + "'";
        List<LopHoc> list = LopHocDAO.getAll(hql);
        if (list != null && !list.isEmpty()) {
            this.createTkb(list);
            this.createDiem(list);
        }

        this.frame.setTitle("Quản lý sinh viên");
        this.frame.setContentPane(this.panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    void createSv() {
        this.mssvField.setText(sv.getMssv());
        this.nameField.setText(sv.getHoTen());
        this.gioiField.setText(sv.getGioiTinh());
        this.cmndField.setText(sv.getCmnd());
        this.lopField.setText(sv.getMaLop());
    }

    void createTkb(List<LopHoc> list) {
        String[] colName = new String[]{
                "STT", "Lớp", "Mã môn", "Tên môn", "Phòng học"
        };
        String[][]colVal = new String[list.size()][5];

        for (int i = 0; i < list.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = list.get(i).getTkb().getMaLop();
            colVal[i][2] = list.get(i).getTkb().getMaMon();
            colVal[i][3] = list.get(i).getTkb().getMon().getTenMon();
            colVal[i][4] = list.get(i).getTkb().getMon().getPhong();
        }
        this.setTab(this.tkbPanel, this.tkbTab, this.tkbField, this.tkbLabel, colVal, colName);
    }

    void createDiem(List<LopHoc> list) {
        List<LopHoc> diem = list.stream()
                .filter(e -> e.getDiem() != null)
                .collect(Collectors.toList());
        if (diem.isEmpty())
            return;

        String[] colName = new String[]{
                "STT", "Mã Lớp", "Tên môn", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm Tổng", "Đậu/Rớt"
        };
        String[][]colVal = new String[list.size()][8];

        for (int i = 0; i < list.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = diem.get(i).getMaTkb();
            colVal[i][2] = diem.get(i).getTkb().getMon().getTenMon();
            colVal[i][3] = String.valueOf(diem.get(i).getDiem().getDiemGk());
            colVal[i][4] = String.valueOf(diem.get(i).getDiem().getDiemCk());
            colVal[i][5] = String.valueOf(diem.get(i).getDiem().getDiemKhac());
            colVal[i][6] = String.valueOf(diem.get(i).getDiem().getDiemTong());
            colVal[i][7] = (diem.get(i).getDiem().getDiemTong() >= 5 ? "Đậu" : "Rớt");
        }
        this.setTab(this.diemPanel, this.diemTab, this.diemField, this.diemLabel, colVal, colName);
    }

    void setTab(JScrollPane panel, JTable table, JTextField textField, JLabel lable, String[][]colVal, String[] colName) {
        TableModel tableModel = new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setModel(tableModel);
        table.setToolTipText("Sao chép");
        table.addMouseListener(MouseAction.getMouseCP(table));

        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(tableRowSorter);
        this.setSearch(textField, tableRowSorter);

        lable.setText("Tra cứu ");
        textField.setComponentPopupMenu(PopMenu.getCP());
        textField.setVisible(true);
        panel.setVisible(true);
    }

    void setSearch(JTextField textField, TableRowSorter<TableModel> tableRowSorter) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
            void search() {
                String text = textField.getText();
                text = (text == null || text.isBlank()) ? "." : "(?i)" + text;

                tableRowSorter.setRowFilter(RowFilter.regexFilter(text));
            }
        });
    }
}


