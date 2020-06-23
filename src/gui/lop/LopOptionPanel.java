package gui.lop;

import util.LayoutSwitch;

import javax.swing.*;

/**
 * gui.lop
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 23-Jun-20 - 9:03 PM
 * @Description
 */
public class LopOptionPanel {
    private JButton listSvLopBtn;
    private JButton listSvLopHocBtn;
    private JButton addLopHocBtn;
    private JButton removeLopHocBtn;
    private JPanel panel;
    private final JPanel viewPanel;

    public LopOptionPanel(JPanel viewPanel) {
        this.viewPanel = viewPanel;

        listSvLopBtn.addActionListener(e -> {
            GetMaLopGUI getMaLopGUI = new GetMaLopGUI(this.viewPanel,"sv_lop");
            getMaLopGUI.init();
        });
        listSvLopHocBtn.addActionListener(e -> {
            GetMaLopGUI getMaLopGUI = new GetMaLopGUI(this.viewPanel,"sv_lopHoc");
            getMaLopGUI.init();
        });
        addLopHocBtn.addActionListener(e -> {
            GetSvLopHocGUI getSvLopHocGUI = new GetSvLopHocGUI(this.viewPanel,"addLopHoc");
            getSvLopHocGUI.init();
        });
        removeLopHocBtn.addActionListener(e -> {
            GetSvLopHocGUI getSvLopHocGUI = new GetSvLopHocGUI(this.viewPanel,"removeLopHoc");
            getSvLopHocGUI.init();
        });
    }

    public void init() {
        LayoutSwitch.next(this.viewPanel, this.panel);
    }
}
