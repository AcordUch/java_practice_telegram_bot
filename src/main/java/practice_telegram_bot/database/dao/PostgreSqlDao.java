package practice_telegram_bot.database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import practice_telegram_bot.database.MatrixDataDB;
import practice_telegram_bot.database.UserDB;

public class PostgreSqlDao {
    public static <T> T findById(Class<T> clazz, Long id) {
        Session session = PostgreSqlSessionFactory.instance().openSession();
        var item = session.get(clazz, id);
        session.close();
        return item;
    }

    public static <T> boolean existInDatabase(Class<T> clazz, Long id){
        return findById(clazz, id) != null;
    }

    public static <T> boolean absentInDatabase(Class<T> clazz, Long id) {
        return !existInDatabase(clazz, id);
    }

    public static <T> void save(T object) {
        try{
            Session session = PostgreSqlSessionFactory.instance().openSession();
            Transaction tx1 = session.beginTransaction();
            session.saveOrUpdate(object);
            tx1.commit();
            session.close();
        } catch (Exception e){
            System.out.println("Error!\n");
            e.printStackTrace();
        }
    }

    public static <T> void update(T object) {
        try{
            Session session = PostgreSqlSessionFactory.instance().openSession();
            Transaction tx1 = session.beginTransaction();
            session.merge(object);
            tx1.commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        if(object.getClass() == UserDB.class){
            onUserSave((UserDB)object);
        }
    }

    private static void onUserSave(UserDB userDB){
        if(userDB.getPrev_id() != null &&
                (userDB.getMatrixData() == null || !userDB.getPrev_id().equals(userDB.getMatrixData().getId()))
        ){
            delete(MatrixDataDB.class, userDB.getPrev_id());
        }
    }

    public static <T> void delete(Class<T> clazz, Long id){
        var object = findById(clazz, id);
        if(object != null){
            Session session = PostgreSqlSessionFactory.instance().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(object);
            tx1.commit();
            session.close();
        }
    }
}
