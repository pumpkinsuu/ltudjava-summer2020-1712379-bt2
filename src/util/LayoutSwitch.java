package util;


import javax.swing.*;
import java.awt.*;

/**
 * util
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 23-Jun-20 - 8:27 PM
 * @Description
 */
public class LayoutSwitch {
    public static void back(JPanel viewPanel, JPanel curPanel) {
        viewPanel.remove(curPanel);

        CardLayout cardLayout = (CardLayout) viewPanel.getLayout();
        cardLayout.previous(viewPanel);
    }
    public static void next(JPanel viewPanel, JPanel nextPanel) {
        viewPanel.add(nextPanel);

        CardLayout cardLayout = (CardLayout) viewPanel.getLayout();
        cardLayout.next(viewPanel);
    }

    public static void setFrame(JFrame frame, JPanel panel, String title, int w, int h) {
        Dimension dimension = new Dimension(w,h);
        frame.setTitle(title);
        frame.setMinimumSize(dimension);
        frame.setSize(dimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
