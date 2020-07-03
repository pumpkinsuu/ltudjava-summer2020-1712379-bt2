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

        if (type.equals("tkb") || type.equals("diem"))
            addBtn.setVisible(false);
        if (type.equals("lop") || type.equals("tkb"))
            updateBtn.setVisible(false);

        importBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(this.viewPanel);
            importCsvGUI.init(type);
        });
        listBtn.addActionListener(e -> {
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
        addBtn.addActionListener(e -> {
            switch (type) {
                case "sv" -> {
                    AddSvGUI addSvGUI = new AddSvGUI(this.viewPanel);
                    addSvGUI.init();
                }
                case "lop" -> {
                    ListGUI listGUI = new ListGUI(this.viewPanel);
                    listGUI.init("get_lop", "get_sv", 0);
                }
            }
        });
        updateBtn.addActionListener(e -> {
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
        removeBtn.addActionListener(e -> {
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
