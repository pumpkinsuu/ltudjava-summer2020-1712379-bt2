package gui;

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

        switch (type) {
            case "tkb" -> {
                this.addBtn.setVisible(false);
                this.updateBtn.setVisible(false);
            }
            case "lop" -> this.updateBtn.setVisible(false);
            case "diem" -> this.addBtn.setVisible(false);
        }

        this.importBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(this.viewPanel);
            importCsvGUI.init(type);
        });
        this.listBtn.addActionListener(e -> {
            switch (type) {
                case "sv", "tkb" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init(type, null, 0);
                }
                case "lop", "diem" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init("get_lop", type, 0);
                }
            }
        });
        this.addBtn.addActionListener(e -> {
            switch (type) {
                case "sv" -> {
                    AddSvGUI addSvGUI = new AddSvGUI(this.viewPanel);
                    addSvGUI.init();
                }
                case "lop" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init("get_lop", "add_lop", 0);
                }
            }
        });
        this.updateBtn.addActionListener(e -> {
            switch (type) {
                case "sv" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init(type, null, 1);
                }
                case "diem" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init("get_lop", type, 1);
                }
            }
        });
        this.removeBtn.addActionListener(e -> {
            switch (type) {
                case "sv", "tkb" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init(type, null, 2);
                }
                case "lop", "diem" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init("get_lop", type, 2);
                }
            }
        });
    }

    public void init() {
        LayoutSwitch.next(this.viewPanel, this.panel);
    }
}
