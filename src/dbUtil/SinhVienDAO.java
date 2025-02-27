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
        String hql = "from SinhVien sv";
        return QLSinhVienDAO.getList(hql);
    }

    public static SinhVien get(String mssv) {
        SinhVien sv = null;
        try(Session session = HibernateUtil.getSession()) {
            sv = session.get(SinhVien.class, mssv);

        } catch (Exception ex) {
            System.err.println(SinhVien.class + " DAO: " + ex.getMessage());
        }
        return sv;
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
