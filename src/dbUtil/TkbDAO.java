package dbUtil;

import org.hibernate.Session;
import pojo.Tkb;

import java.util.List;

/**
 * dbUtil
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 7:58 PM
 * @Description
 */
public class TkbDAO {

    public static List<Tkb> getList() {
        String hql = "select tkb from Tkb tkb";
        return QLSinhVienDAO.getList(hql);
    }

    public static Tkb get(String maTkb) {
        Tkb tkb = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();

            session.beginTransaction();
            session.get(Tkb.class, maTkb);
            session.getTransaction().commit();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return tkb;
    }

    public static boolean add(Tkb tkb) {
        if (TkbDAO.get(tkb.getMaTkb()) != null)
            return false;

        return QLSinhVienDAO.add(tkb);
    }

    public static boolean update(Tkb tkb) {
        if (TkbDAO.get(tkb.getMaTkb()) == null)
            return false;

        return QLSinhVienDAO.update(tkb);
    }

    public static boolean remove(String maTkb) {
        Tkb tkb = TkbDAO.get(maTkb);
        if (tkb == null)
            return false;

        return QLSinhVienDAO.remove(tkb);
    }
}