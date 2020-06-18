package dbUtil;

import org.hibernate.Session;
import pojo.Diem;

import java.util.List;

/**
 * dbUtil
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 6:59 PM
 * @Description
 */
public class DiemDAO {

    public static List<Diem> getList() {
        String hql = "select Diem from Diem Diem";
        return QLSinhVienDAO.getList(hql);
    }

    public static Diem get(int maDiem) {
        Diem Diem = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();

            session.beginTransaction();
            Diem = session.get(Diem.class, maDiem);
            session.getTransaction().commit();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return Diem;
    }

    public static boolean add(Diem Diem) {
        if (DiemDAO.get(Diem.getMaDiem()) != null)
            return false;

        return QLSinhVienDAO.add(Diem);
    }

    public static boolean update(Diem Diem) {
        if (DiemDAO.get(Diem.getMaDiem()) == null)
            return false;

        return QLSinhVienDAO.update(Diem);
    }

    public static boolean remove(int maDiem) {
        Diem Diem = DiemDAO.get(maDiem);
        if (Diem == null)
            return false;

        return QLSinhVienDAO.remove(Diem);
    }
}
