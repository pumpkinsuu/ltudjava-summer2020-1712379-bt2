package gui;

import dbUtil.LopHocDAO;
import pojo.LopHoc;
import pojo.SinhVien;
import util.TableSearch;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
    private JTextField searchTkbField;
    private JTable tkbTable;
    private JTextField searchDiemField;
    private JTable diemTable;
    private JPanel panel;
    private JLabel tkbLable;
    private JLabel diemLabel;
    private JScrollPane tkbPanel;
    private JScrollPane diemPanel;
    private JPanel accPanel;
    private final JFrame frame;
    private final SinhVien sv;
    private final List<LopHoc> lopHoc;

    public MenuSvGUI(JFrame frame, SinhVien sv) {
        this.frame = frame;
        this.sv = sv;
        String hql = "from LopHoc lopHoc where lopHoc.mssv = '" + this.sv.getMssv() + "'";
        this.lopHoc = LopHocDAO.getAll(hql);

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
        this.createTkb();
        this.createDiem();

        this.frame.setTitle("Quản lý sinh viên");
        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    void createSv() {
        this.mssvField.setText(this.sv.getMssv());
        this.nameField.setText(this.sv.getHoTen());
        this.gioiField.setText(this.sv.getGioiTinh());
        this.cmndField.setText(this.sv.getCmnd());
        this.lopField.setText(this.sv.getMaLop());
    }

    void createTkb() {
        if (this.lopHoc == null || this.lopHoc.isEmpty())
            return;

        String[] colName = new String[]{
                "STT", "Mã môn", "Tên môn", "Phòng học", "Lớp"
        };
        String[][]colVal = new String[this.lopHoc.size()][5];

        for (int i = 0; i < this.lopHoc.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = this.lopHoc.get(i).getTkb().getMaMon();
            colVal[i][2] = this.lopHoc.get(i).getTkb().getMon().getTenMon();
            colVal[i][3] = this.lopHoc.get(i).getTkb().getMon().getPhong();
            colVal[i][4] = this.lopHoc.get(i).getTkb().getMaLop();
        }

        TableModel tableModel = new DefaultTableModel(colVal, colName) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        TableSearch.set(this.tkbTable, tableModel, this.searchTkbField);

        this.tkbLable.setText("Tra cứu ");
        this.searchTkbField.setVisible(true);
        this.tkbPanel.setVisible(true);
    }

    void createDiem() {
        if (this.lopHoc == null || this.lopHoc.isEmpty())
            return;

        List<LopHoc> list = this.lopHoc.stream()
                .filter(e -> e.getDiem() != null)
                .collect(Collectors.toList());
        if (list.isEmpty())
            return;

        String[] colName = new String[]{
                "STT", "Mã Lớp", "Tên môn", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm Tổng", "Đậu/Rớt"
        };
        String[][]colVal = new String[list.size()][8];

        for (int i = 0; i < list.size(); ++i) {
            colVal[i][0] = String.valueOf(i + 1);
            colVal[i][1] = list.get(i).getMaTkb();
            colVal[i][2] = list.get(i).getTkb().getMon().getTenMon();
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
        TableSearch.set(this.diemTable, tableModel, this.searchDiemField);

        this.diemLabel.setText("Tra cứu ");
        this.searchDiemField.setVisible(true);
        this.diemPanel.setVisible(true);
    }
}
