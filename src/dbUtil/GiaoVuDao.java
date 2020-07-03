package dbUtil;

import org.hibernate.Session;
import pojo.GiaoVu;

/**
 * dbUtil
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 10:36 PM
 * @Description
 */
public class GiaoVuDao {

    public static GiaoVu get(String username) {
        GiaoVu gv = null;
        try(Session session = HibernateUtil.getSession()) {
            gv = session.get(GiaoVu.class, username);

        } catch (Exception ex) {
            System.err.println(GiaoVu.class + " DAO: " + ex.getMessage());
        }
        return gv;
    }

    public static boolean update(GiaoVu gv) {
        if (GiaoVuDao.get(gv.getUsername()) == null)
            return false;

        return QLSinhVienDAO.update(gv);
    }
}
