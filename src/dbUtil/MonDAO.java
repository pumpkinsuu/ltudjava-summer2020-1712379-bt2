package dbUtil;

import org.hibernate.Session;
import pojo.Mon;

import java.util.List;

/**
 * dbUtil
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 4:49 PM
 * @Description
 */

public class MonDAO {

    public static List<Mon> getList() {
        String hql = "from Mon mon";
        return QLSinhVienDAO.getList(hql);
    }

    public static Mon get(String maMon) {
        Mon mon = null;
        try(Session session = HibernateUtil.getSession()) {
            mon = session.get(Mon.class, maMon);

        } catch (Exception ex) {
            System.err.println(Mon.class + " DAO: " + ex.getMessage());
        }
        return mon;
    }

    public static boolean add(Mon mon) {
        if (MonDAO.get(mon.getMaMon()) != null)
            return false;

        return QLSinhVienDAO.add(mon);
    }

    public static boolean update(Mon mon) {
        if (MonDAO.get(mon.getMaMon()) == null)
            return false;

        return QLSinhVienDAO.update(mon);
    }

    public static boolean remove(String maMon) {
        Mon mon = MonDAO.get(maMon);
        if (mon == null)
            return false;

        return QLSinhVienDAO.remove(mon);
    }
}
