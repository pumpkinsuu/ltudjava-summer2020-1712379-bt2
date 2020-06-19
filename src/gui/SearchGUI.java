package gui;

import dbUtil.LopDAO;
import dbUtil.LopHocDAO;
import dbUtil.TkbDAO;
import pojo.Lop;
import pojo.LopHoc;
import pojo.Tkb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * gui
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 19-Jun-20 - 11:21 PM
 * @Description
 */
public class SearchGUI {
    private JTextField searchField;
    private JComboBox<String> selectBox;
    private JButton searchBtn;
    private JButton closeBtn;
    private JPanel panel;
    private JFrame frame;
    private String type;
    private List<String> list;

    public SearchGUI() {

        closeBtn.addActionListener(e -> this.frame.dispose());
        selectBox.addActionListener(e -> {
            if (Objects.requireNonNull(this.selectBox.getSelectedItem()).toString() != null)
                this.searchField.setText(this.selectBox.getSelectedItem().toString());
        });

        searchBtn.addActionListener(e -> {
            if (this.searchField.getText() == null)
                return;
            if (!this.list.contains(this.searchField.getText())) {
                JOptionPane.showMessageDialog(this.searchField, "Không có trong danh sách!");
                return;
            }


        });
    }

    public void init(String type) {
        this.type = type;
        this.list = new ArrayList<>();
        this.createLopBox();

        if (this.list.isEmpty()) {
            JOptionPane.showMessageDialog(this.panel, "Danh sách rỗng!");
            return;
        }

        this.frame = new JFrame("Tìm kiếm");
        this.frame.setContentPane(panel);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    void createLopBox() {
        switch (this.type) {
            case "sv_lop", "tkb_lop" -> {
                List<Lop> lops = LopDAO.getList();
                for (Lop lop : lops) {
                    this.list.add(lop.getMaLop());
                    this.selectBox.addItem(lop.getMaLop());
                }
            }
            case "sv_lopHoc", "diem_lopHoc" -> {
                List<LopHoc> lopHocs = LopHocDAO.getList();
                for (LopHoc lopHoc : lopHocs) {
                    this.list.add(lopHoc.getMaLopHoc());
                    this.selectBox.addItem(lopHoc.getMaLopHoc());
                }
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(spacer1, gbc);
        searchField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(searchField, gbc);
        selectBox = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(selectBox, gbc);
        searchBtn = new JButton();
        searchBtn.setText("Tìm kiếm");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(searchBtn, gbc);
        closeBtn = new JButton();
        closeBtn.setText("Đóng");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(closeBtn, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
