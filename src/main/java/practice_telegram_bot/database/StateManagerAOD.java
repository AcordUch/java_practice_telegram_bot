package practice_telegram_bot.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StateManagerAOD {
    public static User findById(Long id) {
        Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
        var item = session.get(User.class, id);
        session.close();
        return item;
    }

    public static <T> T findById(Class<T> clazz, Long id) {
        Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
        var item = session.get(clazz, id);
        session.close();
        return item;
    }

    public static User findById(String id) {
        return findById(Long.parseLong(id));
    }

    public static void save(User stateManager) {
        try{
            Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.saveOrUpdate(stateManager);
            tx1.commit();
            session.close();
        } catch (Exception e){
            System.out.println("Error!\n");
            e.printStackTrace();
        }
    }

    public static void update(User stateManager) {
        try{
            Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.merge(stateManager);
            tx1.commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(stateManager.getPrev_id() != null && !stateManager.getPrev_id().equals(stateManager.getMatrixData().getId())){
            delete(MatrixDataDB.class, stateManager.getPrev_id());
        }
    }

    public static void evict(Long id){
        User user = findById(id);
        if(user != null){
            Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.evict(user);
            tx1.commit();
            session.close();
        }
    }

    public static void delete(User stateManager) {
        Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(stateManager);
        tx1.commit();
        session.close();
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

    public static <T> void delete(Class<T> clazz, Long id){
        var user = findById(clazz, id);
        if(user != null){
            Session session = HibernateSessionFactoryUtil2.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
            session.close();
        }
    }
}
