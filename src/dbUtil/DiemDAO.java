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
        String hql = "from Diem diem";
        return QLSinhVienDAO.getList(hql);
    }

    public static Diem get(String maDiem) {
        Diem diem = null;
        try (Session session = HibernateUtil.getSession()) {
            diem = session.get(Diem.class, maDiem);

        } catch (Exception ex) {
            System.err.println(Diem.class + " DAO: " + ex.getMessage());
        }
        return diem;
    }

    public static boolean add(Diem diem) {
        if (DiemDAO.get(diem.getMaLopHoc()) != null)
            return false;

        return QLSinhVienDAO.add(diem);
    }

    public static boolean update(Diem diem) {
        if (DiemDAO.get(diem.getMaLopHoc()) == null)
            return false;

        return QLSinhVienDAO.update(diem);
    }

    public static boolean remove(String maDiem) {
        Diem diem = DiemDAO.get(maDiem);
        if (diem == null)
            return false;

        return QLSinhVienDAO.remove(diem);
    }
}
