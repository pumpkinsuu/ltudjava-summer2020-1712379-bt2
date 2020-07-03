package util;

import javax.swing.*;

/**
 * util
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 01-Jul-20 - 12:39 PM
 * @Description
 */
public class OptionMsg {
    public static void infoMsg(java.awt.Component parentComponent, String text) {
        Object[] option = {"Đóng"};
        JOptionPane.showOptionDialog(parentComponent, text, "Thông báo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
    }
    public static boolean confirmMsg(java.awt.Component parentComponent, String text) {
        Object[] option = {"Xác nhận", "Hủy"};
        return JOptionPane.showOptionDialog(parentComponent, text, "Thông báo",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]) != 0;
    }
    public static void errMsg(java.awt.Component parentComponent, String text) {
        Object[] option = {"Đóng"};
        JOptionPane.showOptionDialog(parentComponent, text, "Lỗi",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, option, option[0]);
    }
    public static void checkMsg(java.awt.Component parentComponent, String text, boolean check) {
        if (check)
            OptionMsg.infoMsg(parentComponent, text + " thành công!");
        else
            OptionMsg.errMsg(parentComponent, text + " thất bại!");
    }
}
