package practice_telegram_bot.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StateManagerAOD {
    public static User findById(Long id) {
        return HibernateSessionFactoryUtil2.getSessionFactory().openSession().get(User.class, id);
    }

    public static User findById(String id) {
        return findById(Long.parseLong(id));
    }

    public static void save(User stateManager) {
        Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(stateManager);
        tx1.commit();
        session.close();
    }

    public static void update(User stateManager) {
        Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(stateManager);
        tx1.commit();
        session.close();
    }

    public static void delete(User stateManager) {
        Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(stateManager);
        tx1.commit();
        session.close();
    }
}
