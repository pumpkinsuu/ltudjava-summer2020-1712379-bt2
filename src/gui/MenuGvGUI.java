package gui;

import gui.lop.GetMaLopGUI;
import gui.lop.GetSvLopHocGUI;
import gui.sv.AddSvGUI;
import gui.sv.ListSvGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 11:19 PM
 * @Description
 */
public class MenuGvGUI {
    private JPanel menuGv;
    private JButton newPWsBtn;
    private JButton logoutBtn;
    private JButton listSvBtn;
    private JButton importSvBtn;
    private JButton addSvBtn;
    private JButton updateSvBtn;
    private JButton removeSvBtn;
    private JButton listTkbBtn;
    private JButton importTkbBtn;
    private JButton listSvLopBtn;
    private JButton listSvLopHocBtn;
    private JButton diemLopHocBtn;
    private JButton updateSvDiemBtn;
    private JButton importDiemBtn;
    private JButton addLopHocBtn;
    private JButton removeLopHocBtn;
    private JFrame frame;
    private final String username;

    public MenuGvGUI(String username) {
        this.username = username;

        logoutBtn.addActionListener(e -> {
            this.frame.dispose();

            LoginGUI loginGUI = new LoginGUI();
            loginGUI.init();
        });
        newPWsBtn.addActionListener(e -> {
            ChangePwGUI cPW = new ChangePwGUI(this.username, true);
            cPW.init();
        });
        importSvBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(0);
            importCsvGUI.init();
        });
        importTkbBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(1);
            importCsvGUI.init();
        });
        importDiemBtn.addActionListener(e -> {
            ImportCsvGUI importCsvGUI = new ImportCsvGUI(3);
            importCsvGUI.init();
        });
        listSvBtn.addActionListener(e -> {
            ListSvGUI listSvGUI = new ListSvGUI();
            listSvGUI.init("all", null);
        });
        listSvLopBtn.addActionListener(e -> {
            GetMaLopGUI getMaLopGUI = new GetMaLopGUI("sv_lop");
            getMaLopGUI.init();
        });
        listSvLopHocBtn.addActionListener(e -> {
            GetMaLopGUI getMaLopGUI = new GetMaLopGUI("sv_lopHoc");
            getMaLopGUI.init();
        });
        listTkbBtn.addActionListener(e -> {
            GetMaLopGUI getMaLopGUI = new GetMaLopGUI("tkb_lop");
            getMaLopGUI.init();
        });
        addSvBtn.addActionListener(e -> {
            AddSvGUI addSvGUI = new AddSvGUI();
            addSvGUI.init();
        });
        addLopHocBtn.addActionListener(e -> {
            GetSvLopHocGUI getSvLopHocGUI = new GetSvLopHocGUI("addLopHoc");
            getSvLopHocGUI.init();
        });
        removeLopHocBtn.addActionListener(e -> {
            GetSvLopHocGUI getSvLopHocGUI = new GetSvLopHocGUI("removeLopHoc");
            getSvLopHocGUI.init();
        });
    }

    public void init() {
        this.frame = new JFrame("Quản lý sinh viên");
        this.frame.setContentPane(menuGv);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(400, 400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}
