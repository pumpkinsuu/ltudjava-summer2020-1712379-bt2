package gui;

import dbUtil.LopDAO;
import dbUtil.LopHocDAO;
import dbUtil.SinhVienDAO;
import dbUtil.TkbDAO;
import gui.diem.TableListDiem;
import gui.sv.TableListSv;
import gui.tkb.TableListTkb;
import pojo.Lop;
import pojo.LopHoc;
import pojo.SinhVien;
import pojo.Tkb;
import util.LayoutSwitch;
import util.MouseAction;
import util.OptionMsg;
import util.PopMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * gui
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 10:31 PM
 * @Description
 */
public class ListGUI {
    private JTextField textField;
    private JPanel panel;
    private JTable table;
    private JButton backBtn;
    private JButton button;
    private JComboBox<String> lopBox;
    private final JPanel viewPanel;
    private TableRowSorter<TableModel> tableRowSorter;

    public ListGUI(JPanel viewPanel) {
        this.viewPanel = viewPanel;

        this.table.setToolTipText("Sao chép");
        this.table.addMouseListener(MouseAction.getMouseCP(table));
        this.textField.setComponentPopupMenu(PopMenu.getCP());

        this.backBtn.addActionListener(e -> LayoutSwitch.back(this.viewPanel, this.panel));
    }

    public void init(String type, String id, int mode) {
        List<Lop> lops = LopDAO.getList();
        if (lops.isEmpty()) {
            OptionMsg.infoMsg(this.panel, "Danh sách rỗng!");
            return;
        }

        this.lopBox.addItem("Tất cả");
        for (Lop lop : lops)
            this.lopBox.addItem(lop.getMaLop());

        boolean flag = false;
        switch (type) {
            case "sv" -> {
                List<SinhVien> list = SinhVienDAO.getList();
                flag = setTabSv(list, mode);
            }
            case "tkb" -> {
                List<Tkb> list = TkbDAO.getList();
                flag = setTabTkb(list, mode);
            }
            case "lop" -> {
                List<LopHoc> lopHocList = TkbDAO.get(id).getLopHoc();
                List<SinhVien> list = new ArrayList<>();
                for (LopHoc lopHoc : lopHocList)
                    list.add(lopHoc.getSinhVien());

                flag = setTabSv(list, mode);
            }
            case "diem" -> {
                this.lopBox.setVisible(false);
                flag = setTabDiem(id, mode);
            }
            case "get_sv" -> {
                List<SinhVien> list = SinhVienDAO.getList();
                flag = setTabSv(list, mode);
                if (flag)
                    this.setGetSvBtn(id);
            }
            case "get_lop" -> {
                List<Tkb> list = TkbDAO.getList();
                flag = setTabTkb(list, mode);
                if (flag)
                    this.setGetTkbBtn(id, mode);
            }
        }

        if (!flag) {
            OptionMsg.infoMsg(this.panel, "Danh sách rỗng!");
            return;
        }
        LayoutSwitch.next(this.viewPanel, this.panel);
    }

    private boolean setTabDiem(String id, int mode) {
        String hql = "from LopHoc lopHoc where lopHoc.maTkb = '" + id + "'";
        List<LopHoc> list = LopHocDAO.getAll(hql);
        if (list == null)
            return false;

        list.removeIf(e -> e.getDiem() == null);
        if (list.isEmpty())
            return false;

        TableListDiem.setTab(this.table, this.button, list, mode);
        this.setTabFilter();
        return true;
    }

    private boolean setTabSv(List<SinhVien> list, int mode) {
        if (!list.isEmpty()) {
            TableListSv.setTab(this.table, this.button, list, mode);
            this.setTabFilter();
            return true;
        }
        return false;
    }

    private boolean setTabTkb(List<Tkb> list, int mode) {
        if (!list.isEmpty()) {
            TableListTkb.setTab(this.table, this.button, list, mode);
            this.setTabFilter();
            return true;
        }
        return false;
    }

    private void setTabFilter() {
        this.tableRowSorter = new TableRowSorter<>(this.table.getModel());
        this.table.setRowSorter(this.tableRowSorter);
        this.textField.getDocument().addDocumentListener(new DocumentListener() {
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
        });
        this.lopBox.addActionListener(e -> search());
    }

    private void search() {
        String lop = Objects.requireNonNull(this.lopBox.getSelectedItem()).toString();
        if (lop.equals("Tất cả")) lop = ".";

        String text = textField.getText();
        text = (text == null || text.isBlank()) ? "." : "(?i)" + text;

        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        filters.add(RowFilter.regexFilter(lop));
        filters.add(RowFilter.regexFilter(text));

        this.tableRowSorter.setRowFilter(RowFilter.andFilter(filters));
    }

    private void setGetSvBtn(String id) {
        this.button.setText("Chọn");
        this.button.setVisible(true);
        this.button.addActionListener(e -> {
            int row = this.table.getSelectedRow();
            if (row == -1) {
                OptionMsg.infoMsg(this.panel, "Chọn sinh viên!");
                return;
            }
            String mssv = this.table.getValueAt(row, 1).toString();
            String maLopHoc = id.substring(id.indexOf('-') + 1)
                    + '-' + mssv;
            if (LopHocDAO.get(maLopHoc) != null) {
                OptionMsg.infoMsg(this.panel, "Sinh viên đã đăng ký môn học!");
                return;
            }

            LopHoc lopHoc = new LopHoc();
            lopHoc.setMaLopHoc(maLopHoc);
            lopHoc.setMaTkb(id);
            lopHoc.setMssv(mssv);

            OptionMsg.checkMsg(this.panel, "Đăng ký", LopHocDAO.add(lopHoc));
        });
    }

    private void setGetTkbBtn(String type, int mode) {
        this.button.setText("Chọn");
        this.button.setVisible(true);
        this.button.addActionListener(e -> {
            int row = this.table.getSelectedRow();
            if (row == -1) {
                OptionMsg.infoMsg(this.panel, "Chọn lớp học!");
                return;
            }
            String maTkb = this.table.getValueAt(row, 1).toString()
                    + '-' + this.table.getValueAt(row, 2).toString();

            ListGUI listGUI = new ListGUI(this.viewPanel);
            listGUI.init(type, maTkb, mode);
        });
    }
}