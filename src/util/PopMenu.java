package util;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

/**
 * util
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 21-Jun-20 - 2:57 PM
 * @Description
 */
public class PopMenu {
    public static JPopupMenu getCP() {
        JPopupMenu menu = new JPopupMenu();

        Action copy = new DefaultEditorKit.CopyAction();
        copy.putValue(Action.NAME, "Copy");
        menu.add( copy );

        Action paste = new DefaultEditorKit.PasteAction();
        paste.putValue(Action.NAME, "Paste");
        menu.add( paste );

        return menu;
    }
}
