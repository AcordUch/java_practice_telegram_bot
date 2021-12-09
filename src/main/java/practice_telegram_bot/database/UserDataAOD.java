package practice_telegram_bot.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDataAOD {
    public static User findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().createEntityManager().find(User.class, id);
    }

    public static User findById(String id) {
        return findById(Long.parseLong(id));
    }

    public static void save(User user) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }

    public static void update(User user) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
        entityManager.close();
    }

    public static void delete(User user) {
        EntityManager entityManager = HibernateSessionFactoryUtil.getSessionFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();
        entityManager.close();
    }

    public static void delete(Long id){
        User user = findById(id);
        if(user != null){
            Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
            session.close();
        }
    }
}
