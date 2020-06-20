package dbUtil;

import org.hibernate.Session;
import pojo.SinhVien;

import java.util.List;

/**
 * PACKAGE_NAME
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 08-Jun-20 - 5:49 PM
 * @Description
 */
public class SinhVienDAO {

    public static List<SinhVien> getList() {
        String hql = "select sv from SinhVien sv";
        return QLSinhVienDAO.getList(hql);
    }

    public static SinhVien get(String mssv) {
        SinhVien sv = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();

            session.beginTransaction();
            sv = session.get(SinhVien.class, mssv);
            session.getTransaction().commit();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return sv;
    }

    public static SinhVien getByName(String name) {
        String hql = "select sv from SinhVien sv where sv.hoTen = '" + name + '\'';
        List<SinhVien> svs = QLSinhVienDAO.getList(hql);

        if (svs != null && !svs.isEmpty())
            return svs.get(0);

        return null;
    }

    public static boolean add(SinhVien sv) {
        if (SinhVienDAO.get(sv.getMssv()) != null)
            return false;

        return QLSinhVienDAO.add(sv);
    }

    public static boolean update(SinhVien sv) {
        if (SinhVienDAO.get(sv.getMssv()) == null)
            return false;

        return QLSinhVienDAO.update(sv);
    }

    public static boolean remove(String mssv) {
        SinhVien sv = SinhVienDAO.get(mssv);
        if (sv == null)
            return false;

        return QLSinhVienDAO.remove(sv);
    }
}
