package gui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

import dbUtil.*;
import pojo.LopHoc;
import pojo.SinhVien;
import pojo.Tkb;
import util.ImportCsv;
import util.LayoutSwitch;

/**
 * gui
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 4:10 PM
 * @Description
 */
public class ImportCsvGUI {
    private JButton openBtn;
    private JPanel panel;
    private JButton closeBtn;
    private final JPanel viewPanel;
    private final String type;

    public ImportCsvGUI(JPanel viewPanel, String type) {
        this.viewPanel = viewPanel;
        this.type = type;

        openBtn.addActionListener(e -> {
            FileFilter filter = new FileNameExtensionFilter("CSV file", "csv");
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Chọn file");
            jFileChooser.setFileFilter(filter);

            if (jFileChooser.showOpenDialog(panel) != JFileChooser.APPROVE_OPTION) {
                System.out.println("Error!");
                return;
            }

            File file = jFileChooser.getSelectedFile();

            boolean flag = false;
            switch (this.type) {
                case "sv" -> flag = ImportCsv.importSv(file);
                case "tkb" -> flag = ImportCsv.importMon(file);
                case "lop" -> flag = ImportCsv.importLopHoc(file);
                case "diem" -> flag = ImportCsv.importDiem(file);
            }

            if (flag)
                JOptionPane.showMessageDialog(panel, "Import thành công!");
            else
                JOptionPane.showMessageDialog(panel, "Import thất bại!");
        });
        closeBtn.addActionListener(e -> LayoutSwitch.back(this.viewPanel, this.panel));
    }

    public void init() {
        if (this.check())
            LayoutSwitch.next(this.viewPanel, this.panel);
    }

    boolean check() {
        if (this.type.equals("lop")) {
            List<Tkb> tkbList = TkbDAO.getList();
            if (tkbList == null || tkbList.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Chưa có thời khóa biểu trong hệ thống!");
                return false;
            }
            List<SinhVien> sinhVienList = SinhVienDAO.getList();
            if (sinhVienList == null || sinhVienList.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Chưa có sinh viên trong hệ thống!");
                return false;
            }
        }
        if (this.type.equals("diem")) {
            List<LopHoc> lopHocList = LopHocDAO.getList();
            if (lopHocList == null || lopHocList.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Chưa có lớp học trong hệ thống!");
                return false;
            }
        }
        return true;
    }

}
