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
}
