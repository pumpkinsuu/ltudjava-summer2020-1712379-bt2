package dbUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * PACKAGE_NAME
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 08-Jun-20 - 5:36 PM
 * @Description
 */
public class HibernateUtil {
    private static final SessionFactory sf;
    static {
        try {
            sf = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("HibernateUtil: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sf.openSession();
    }
}
