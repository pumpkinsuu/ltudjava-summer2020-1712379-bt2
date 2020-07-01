package gui;

import gui.ImportCsvGUI;
import gui.ListGUI;
import gui.lop.GetMaLopGUI;
import gui.sv.AddSvGUI;
import util.LayoutSwitch;

import javax.swing.*;

/**
 * gui.sv
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 23-Jun-20 - 8:17 PM
 * @Description
 */
public class OptionGUI {
    private JButton importBtn;
    private JButton addBtn;
    private JButton listBtn;
    private JPanel panel;
    private JButton updateBtn;
    private JButton removeBtn;
    private final JPanel viewPanel;

    public OptionGUI(JPanel viewPanel, String type) {
        this.viewPanel = viewPanel;

        if (type.equals("lop"))
            updateBtn.setVisible(false);

        importBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(this.viewPanel, type);
            importCsvGUI.init();
        });
        listBtn.addActionListener(e -> {
            switch (type) {
                case "sv", "tkb" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init(type, null, 0);
                }
                case "lop", "diem" -> {
                    GetMaLopGUI getMaLopGUI = new GetMaLopGUI(this.viewPanel);
                    getMaLopGUI.init(type, 0);
                }
            }
        });
        addBtn.addActionListener(e -> {
            switch (type) {
                case "sv" -> {
                    AddSvGUI addSvGUI = new AddSvGUI(this.viewPanel);
                    addSvGUI.init();
                }
                case "tkb", "lop", "diem" -> {}
            }
        });
        updateBtn.addActionListener(e -> {
            switch (type) {
                case "sv", "tkb" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init(type, null, 1);
                }
                case "diem" -> {
                    GetMaLopGUI getMaLopGUI = new GetMaLopGUI(this.viewPanel);
                    getMaLopGUI.init(type, 1);
                }
            }
        });
        removeBtn.addActionListener(e -> {
            switch (type) {
                case "sv", "tkb" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init(type, null, 2);
                }
                case "lop", "diem" -> {
                    GetMaLopGUI getMaLopGUI = new GetMaLopGUI(this.viewPanel);
                    getMaLopGUI.init(type, 2);
                }
            }
        });
    }

    public void init() {
        LayoutSwitch.next(this.viewPanel, this.panel);
    }
}
