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

        backBtn.addActionListener(e -> LayoutSwitch.back(this.viewPanel, this.panel));
        table.setToolTipText("Sao chép");
        table.addMouseListener(MouseAction.getMouseCP(table));
        textField.setComponentPopupMenu(PopMenu.getCP());
    }

    public void init(String type, String id, int mode) {
        List<Lop> lops = LopDAO.getList();
        if (lops.isEmpty()) {
            JOptionPane.showMessageDialog(this.panel, "Danh sách rỗng!");
            return;
        }

        this.lopBox.addItem("Tất cả");
        for (Lop lop : lops)
            this.lopBox.addItem(lop.getMaLop());

        boolean flag = false;
        switch (type) {
            case "lop" -> {
                List<LopHoc> lopHocList = TkbDAO.get(id).getLopHoc();
                List<SinhVien> list = new ArrayList<>();
                for (LopHoc lopHoc : lopHocList)
                    list.add(lopHoc.getSinhVien());

                flag = setTabSv(list, mode);
            }
            case "sv" -> {
                List<SinhVien> list = SinhVienDAO.getList();
                flag = setTabSv(list, mode);
            }
            case "tkb" -> {
                List<Tkb> list = TkbDAO.getList();
                flag = setTabTkb(list, mode);
            }
            case "diem" -> {
                this.lopBox.setVisible(false);
                flag = setDiemLopGUI(id, mode);
            }
        }

        if (!flag) {
            JOptionPane.showMessageDialog(this.panel, "Danh sách rỗng!");
            return;
        }
        LayoutSwitch.next(this.viewPanel, this.panel);
    }

    boolean setDiemLopGUI(String id, int mode) {
        String hql = "from LopHoc lopHoc where lopHoc.maTkb = '" + id + "'";

        List<LopHoc> list = LopHocDAO.getAll(hql);
        if (list == null)
            return false;

        list.removeIf(lh -> lh.getDiem() == null);
        if (list.isEmpty())
            return false;


        TableListDiem.setTab(this.table, this.button, list, mode);
        this.setTabFilter();
        return true;
    }

    boolean setTabSv(List<SinhVien> list, int mode) {
        if (!list.isEmpty()) {
            TableListSv.setTab(this.table, this.button, list, mode);
            this.setTabFilter();
            return true;
        }
        return false;
    }

    boolean setTabTkb(List<Tkb> list, int mode) {
        if (!list.isEmpty()) {
            TableListTkb.setTab(this.table, this.button, list, mode);
            this.setTabFilter();
            return true;
        }
        return false;
    }

    void setTabFilter() {
        this.tableRowSorter = new TableRowSorter<>(this.table.getModel());
        this.table.setRowSorter(this.tableRowSorter);
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
        });
        lopBox.addActionListener(e -> search());
    }

    void search() {
        String lop = Objects.requireNonNull(lopBox.getSelectedItem()).toString();
        if (lop.equals("Tất cả")) lop = ".";
        String text = textField.getText();
        text = (text == null || text.isBlank()) ? "." : "(?i)" + text;

        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        filters.add(RowFilter.regexFilter(lop));
        filters.add(RowFilter.regexFilter(text));

        tableRowSorter.setRowFilter(RowFilter.andFilter(filters));
    }
}