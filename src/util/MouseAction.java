package util;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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

                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                if (row == -1 || col == -1)
                    return;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    String text = "";
                    if (table.getValueAt(row, col) != null)
                        text = table.getValueAt(row, col).toString();

                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(new StringSelection(text), null);
                }
                else if (SwingUtilities.isRightMouseButton(e)) {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    try {
                        table.setValueAt(clipboard.getData(DataFlavor.stringFlavor), table.getSelectedRow(), table.getSelectedColumn());
                    } catch (UnsupportedFlavorException | IOException ex) {
                        System.err.println("Table mouse paste: " + ex.getMessage());
                    }
                }
            }
        };
    }
}
