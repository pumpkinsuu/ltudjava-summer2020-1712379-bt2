package gui.tkb;

import gui.ImportCsvGUI;
import gui.lop.GetMaLopGUI;
import util.LayoutSwitch;

import javax.swing.*;

/**
 * gui.tkb
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 23-Jun-20 - 8:58 PM
 * @Description
 */
public class TkbOptionGUI {
    private JButton listTkbBtn;
    private JButton importTkbBtn;
    private JPanel panel;
    private final JPanel viewPanel;

    public TkbOptionGUI(JPanel viewPanel) {
        this.viewPanel = viewPanel;

        importTkbBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(this.viewPanel,1);
            importCsvGUI.init();
        });
        listTkbBtn.addActionListener(e -> {
            GetMaLopGUI getMaLopGUI = new GetMaLopGUI(this.viewPanel, "tkb_lop");
            getMaLopGUI.init();
        });
    }

    public void init() {
        LayoutSwitch.next(this.viewPanel, this.panel);
    }
}
