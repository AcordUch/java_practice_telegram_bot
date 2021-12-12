package practice_telegram_bot.database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import practice_telegram_bot.database.UserDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MongoDbDao {
    public static UserDB findById(Long id) {
        return MongoDbSessionFactory.instance().createEntityManager().find(UserDB.class, id);
    }

    public static UserDB findById(String id) {
        return findById(Long.parseLong(id));
    }

    public static void save(UserDB userDB) {
        EntityManager entityManager = MongoDbSessionFactory.instance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(userDB);
        transaction.commit();
        entityManager.close();
    }

    public static void update(UserDB userDB) {
        EntityManager entityManager = MongoDbSessionFactory.instance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(userDB);
        transaction.commit();
        entityManager.close();
    }

    public static void delete(UserDB userDB) {
        EntityManager entityManager = MongoDbSessionFactory.instance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(userDB);
        transaction.commit();
        entityManager.close();
    }

    public static void delete(Long id){
        UserDB userDB = findById(id);
        if(userDB != null){
            Session session = PostgreSqlSessionFactory.instance().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(userDB);
            tx1.commit();
            session.close();
        }
    }
}
