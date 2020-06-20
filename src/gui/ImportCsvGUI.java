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

/**
 * gui
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 4:10 PM
 * @Description
 */
public class ImportCsvGUI {
    private JButton openBtn;
    private JPanel importPanel;
    private JButton closeBtn;
    private JFrame frame;
    private final int type;

    public ImportCsvGUI(int type) {
        this.type = type;

        openBtn.addActionListener(e -> {
            FileFilter filter = new FileNameExtensionFilter("CSV file", "csv");
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Chọn file");
            jFileChooser.setFileFilter(filter);

            if (jFileChooser.showOpenDialog(importPanel) != JFileChooser.APPROVE_OPTION) {
                System.out.println("Error!");
                return;
            }

            File file = jFileChooser.getSelectedFile();

            boolean flag = false;
            switch (this.type) {
                case 0 -> flag = ImportCsv.importSv(file);
                case 1 -> flag = ImportCsv.importMon(file);
                case 2 -> flag = ImportCsv.importLopHoc(file);
                case 3 -> flag = ImportCsv.importDiem(file);
            }

            if (flag)
                JOptionPane.showMessageDialog(importPanel, "Import thành công!");
            else
                JOptionPane.showMessageDialog(importPanel, "Import thất bại!");
        });
        closeBtn.addActionListener(e -> this.frame.dispose());
    }

    public void init() {
        if (!check()) return;

        String name = "";
        switch (this.type) {
            case 0 -> name = "sinh viên";
            case 1 -> name = "thời khóa biểu";
            case 2 -> name = "lớp học";
            case 3 -> name = "điểm";
        }

        frame = new JFrame("Import " + name);
        frame.setContentPane(importPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    boolean check() {
        if (this.type == 2) {
            List<Tkb> tkbList = TkbDAO.getList();
            if (tkbList == null || tkbList.isEmpty()) {
                JOptionPane.showMessageDialog(importPanel, "Chưa có thời khóa biểu trong hệ thống!");
                return false;
            }
            List<SinhVien> sinhVienList = SinhVienDAO.getList();
            if (sinhVienList == null || sinhVienList.isEmpty()) {
                JOptionPane.showMessageDialog(importPanel, "Chưa có sinh viên trong hệ thống!");
                return false;
            }
        }
        if (this.type == 3) {
            List<LopHoc> lopHocList = LopHocDAO.getList();
            if (lopHocList == null || lopHocList.isEmpty()) {
                JOptionPane.showMessageDialog(importPanel, "Chưa có lớp học trong hệ thống!");
                return false;
            }
        }
        return true;
    }

}
