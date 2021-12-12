package practice_telegram_bot.database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import practice_telegram_bot.database.MatrixDataDB;
import practice_telegram_bot.database.UserDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MongoDbDao implements IDAO {
    @Override
    public <T> T findById(Class<T> clazz, Long id) {
        var session = MongoDbSessionFactory.instance().createEntityManager();
        var item = session.find(clazz, id);
        session.close();
        return item;
    }

    @Override
    public <T> boolean existInDatabase(Class<T> clazz, Long id){
        return findById(clazz, id) != null;
    }

    @Override
    public <T> boolean absentInDatabase(Class<T> clazz, Long id) {
        return !existInDatabase(clazz, id);
    }

    @Override
    public <T> void save(T object) {
        try {
            EntityManager entityManager = MongoDbSessionFactory.instance().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(object);
            transaction.commit();
            entityManager.close();
        } catch (Exception e){
            System.out.println("Error!\n");
            e.printStackTrace();
        }
    }

    @Override
    public <T> void update(T object) {
        try {
            EntityManager entityManager = MongoDbSessionFactory.instance().createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(object);
            transaction.commit();
            entityManager.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        if(object.getClass() == UserDB.class){
            onUserSave((UserDB)object);
        }
    }

    private void onUserSave(UserDB userDB){
        if(userDB.getPrev_id() != null &&
                (userDB.getMatrixData() == null || !userDB.getPrev_id().equals(userDB.getMatrixData().getId()))
        ){
            delete(MatrixDataDB.class, userDB.getPrev_id());
        }
    }

    @Override
    public <T> void delete(Class<T> clazz, Long id){
        T object = findById(clazz, id);
        if(object != null){
            Session session = PostgreSqlSessionFactory.instance().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(object);
            tx1.commit();
            session.close();
        }
    }
}
