package dbUtil;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import pojo.Lop;
import pojo.SinhVien;

import java.util.List;

/**
 * dbUtil
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 3:41 PM
 * @Description
 */
public class LopDAO {

    public static List<Lop> getList() {
        String hql = "select lop from Lop lop";
        return QLSinhVienDAO.getList(hql);
    }

    public static Lop get(String maLop) {
        Lop lop = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();

            session.beginTransaction();
            lop = session.get(Lop.class, maLop);
            Hibernate.initialize(lop.getSinhVien());
            session.getTransaction().commit();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return lop;
    }

    public static boolean add(Lop lop) {
        if (LopDAO.get(lop.getMaLop()) != null)
            return false;

        return QLSinhVienDAO.add(lop);
    }

    public static boolean update(Lop lop) {
        if (LopDAO.get(lop.getMaLop()) == null)
            return false;

        return QLSinhVienDAO.update(lop);
    }

    public static boolean remove(String maLop) {
        Lop lop = LopDAO.get(maLop);
        if (lop == null)
            return false;

        return QLSinhVienDAO.remove(lop);
    }
}
