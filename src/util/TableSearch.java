package util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * util
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 21-Jun-20 - 3:28 PM
 * @Description
 */
public class TableSearch {
    public static void set(JTable table, TableModel tableModel, JTextField textField) {
        TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(tableModel);
        table.setModel(tableModel);
        table.setRowSorter(tableRowSorter);
        table.setToolTipText("Sao chép");
        table.addMouseListener(MouseAction.getMouseCP(table));

        textField.setComponentPopupMenu(PopMenu.getCP());
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    tableRowSorter.setRowFilter(null);
                } else {
                    tableRowSorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
    }
}
