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
import util.OptionMsg;

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

    public ImportCsvGUI(JPanel viewPanel) {
        this.viewPanel = viewPanel;

        this.closeBtn.addActionListener(e -> LayoutSwitch.back(this.viewPanel, this.panel));
    }

    public void init(String type) {
        if (this.check(type))
            LayoutSwitch.next(this.viewPanel, this.panel);
    }

    private boolean check(String type) {
        if (type.equals("lop")) {
            List<Tkb> tkbList = TkbDAO.getList();
            if (tkbList == null || tkbList.isEmpty()) {
                OptionMsg.infoMsg(this.panel, "Chưa có thời khóa biểu trong hệ thống!");
                return false;
            }
            List<SinhVien> sinhVienList = SinhVienDAO.getList();
            if (sinhVienList == null || sinhVienList.isEmpty()) {
                OptionMsg.infoMsg(this.panel, "Chưa có sinh viên trong hệ thống!");
                return false;
            }
        }
        if (type.equals("diem")) {
            List<LopHoc> lopHocList = LopHocDAO.getList();
            if (lopHocList == null || lopHocList.isEmpty()) {
                OptionMsg.infoMsg(this.panel, "Chưa có lớp học trong hệ thống!");
                return false;
            }
        }

        this.openBtn.addActionListener(e -> {
            FileFilter filter = new FileNameExtensionFilter("CSV file", "csv");
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Chọn file");
            jFileChooser.setFileFilter(filter);

            if (jFileChooser.showOpenDialog(this.panel) != JFileChooser.APPROVE_OPTION)
                return;

            File file = jFileChooser.getSelectedFile();

            boolean flag = false;
            switch (type) {
                case "sv" -> flag = ImportCsv.importSv(file);
                case "tkb" -> flag = ImportCsv.importTkb(file);
                case "lop" -> flag = ImportCsv.importLopHoc(file);
                case "diem" -> flag = ImportCsv.importDiem(file);
            }
            OptionMsg.checkMsg(this.panel, "Import", flag);
        });
        return true;
    }

}
