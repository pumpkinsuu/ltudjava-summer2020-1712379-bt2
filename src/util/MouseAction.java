package util;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * util
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 21-Jun-20 - 3:26 PM
 * @Description
 */
public class MouseAction {
    public static MouseAdapter getMouseCP(JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String text = "";

                if (table.getSelectedRow() != -1 && table.getSelectedColumn() != -1) {
                    if (table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) != null)
                        if (table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).getClass() == String.class)
                        text = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
                }

                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(text), null);
            }
        };
    }
}
