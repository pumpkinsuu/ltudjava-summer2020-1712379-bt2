package dbUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * dbUtil
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 5:16 PM
 * @Description
 */
public class QLSinhVienDAO {

    @SuppressWarnings("unchecked")
    public static <T> List<T> getList(String hql) {
        List<T> list = null;
        try(Session session = HibernateUtil.getSession()) {
            list = session.createQuery(hql).list();

        } catch (Exception ex) {
            System.err.println("QLSV DAO getList(): " + ex.getMessage());
        }
        return list;
    }

    public static <T> boolean add(T obj) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();

            System.err.println("QLSV DAO add(): " + ex.getMessage());
            return false;
        }
        return true;
    }

    public static <T> boolean update(T obj) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(obj);
            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();

            System.err.println("QLSV DAO update(): " + ex.getMessage());
            return false;
        }
        return true;
    }

    public static <T> boolean remove(T obj) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.remove(obj);
            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();

            System.err.println("QLSV DAO remove(): " + ex.getMessage());
            return false;
        }
        return true;
    }
}
