package dbUtil;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import pojo.LopHoc;

import java.util.List;

/**
 * dbUtil
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 6:50 PM
 * @Description
 */
public class LopHocDAO {

    public static List<LopHoc> getList() {
        String hql = "from LopHoc lopHoc";
        return QLSinhVienDAO.getList(hql);
    }

    @SuppressWarnings("unchecked")
    public static List<LopHoc> getAll(String hql) {
        List<LopHoc> list = null;
        try(Session session = HibernateUtil.getSession()) {
            list = session.createQuery(hql).list();
            Hibernate.initialize(list);

        } catch (Exception ex) {
            System.err.println(LopHoc.class + " DAO: " + ex.getMessage());
        }
        return list;
    }

    public static LopHoc get(String maLopHoc) {
        LopHoc lopHoc = null;
        try(Session session = HibernateUtil.getSession()) {
            lopHoc = session.get(LopHoc.class, maLopHoc);
            Hibernate.initialize(lopHoc.getDiem());
            Hibernate.initialize(lopHoc.getSinhVien());

        } catch (Exception ex) {
            System.err.println(LopHoc.class + " DAO: " + ex.getMessage());
        }
        return lopHoc;
    }

    public static boolean add(LopHoc lopHoc) {
        if (LopHocDAO.get(lopHoc.getMaLopHoc()) != null)
            return false;

        return QLSinhVienDAO.add(lopHoc);
    }

    public static boolean update(LopHoc lopHoc) {
        if (LopHocDAO.get(lopHoc.getMaLopHoc()) == null)
            return false;

        return QLSinhVienDAO.update(lopHoc);
    }

    public static boolean remove(String maLopHoc) {
        LopHoc lopHoc = LopHocDAO.get(maLopHoc);
        if (lopHoc == null)
            return false;

        return QLSinhVienDAO.remove(lopHoc);
    }
}
