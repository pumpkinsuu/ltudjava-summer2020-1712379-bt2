package gui.diem;

import gui.ImportCsvGUI;
import gui.lop.GetMaLopGUI;
import gui.lop.GetSvLopHocGUI;
import util.LayoutSwitch;

import javax.swing.*;

/**
 * gui.diem
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 23-Jun-20 - 9:07 PM
 * @Description
 */
public class DiemOptionGUI {
    private JButton diemLopHocBtn;
    private JButton importDiemBtn;
    private JButton updateSvDiemBtn;
    private JPanel panel;
    private final JPanel viewPanel;

    public DiemOptionGUI(JPanel viewPanel) {
        this.viewPanel = viewPanel;

        importDiemBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(this.viewPanel,3);
            importCsvGUI.init();
        });
        diemLopHocBtn.addActionListener(e -> {
            GetMaLopGUI getMaLopGUI = new GetMaLopGUI(this.viewPanel,"diem_lop");
            getMaLopGUI.init();
        });
        updateSvDiemBtn.addActionListener(e -> {
            GetSvLopHocGUI getSvLopHocGUI = new GetSvLopHocGUI(this.viewPanel,"updateDiem");
            getSvLopHocGUI.init();
        });
    }

    public void init() {
        LayoutSwitch.next(this.viewPanel, this.panel);
    }
}
