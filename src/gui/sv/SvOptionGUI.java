package gui.sv;

import gui.ImportCsvGUI;
import gui.ListGUI;
import util.LayoutSwitch;

import javax.swing.*;

/**
 * gui.sv
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 23-Jun-20 - 8:17 PM
 * @Description
 */
public class SvOptionGUI {
    private JButton importBtn;
    private JButton addBtn;
    private JButton listBtn;
    private JPanel panel;
    private final JPanel viewPanel;

    public SvOptionGUI(JPanel viewPanel) {
        this.viewPanel = viewPanel;

        importBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(this.viewPanel,0);
            importCsvGUI.init();
        });
        listBtn.addActionListener(e -> {
            ListGUI listGUI = new ListGUI(this.viewPanel);
            listGUI.init("sv_all", null);
        });
        addBtn.addActionListener(e -> {
            AddSvGUI addSvGUI = new AddSvGUI(this.viewPanel);
            addSvGUI.init();
        });
    }

    public void init() {
        LayoutSwitch.next(this.viewPanel, this.panel);
    }
}
