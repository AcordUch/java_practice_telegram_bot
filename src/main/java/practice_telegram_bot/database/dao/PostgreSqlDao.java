package practice_telegram_bot.database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import practice_telegram_bot.database.MatrixDataDB;
import practice_telegram_bot.database.UserDB;

public class PostgreSqlDao implements IDAO {
    private Session session;
    private Transaction tx1;

    private void openSession(){
        session = PostgreSqlSessionFactory.instance().openSession();
        tx1 = session.beginTransaction();
    }

    private void closeSession(){
        tx1.commit();
        session.close();
        tx1 = null;
        session = null;
    }

    @Override
    public <T> T findById(Class<T> clazz, Long id) {
        Session session = PostgreSqlSessionFactory.instance().openSession();
        var item = session.get(clazz, id);
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
        try{
            openSession();

            session.saveOrUpdate(object);

            closeSession();
        } catch (Exception e){
            System.out.println("Error!\n");
            e.printStackTrace();
        }
    }

    @Override
    public <T> void update(T object) {
        try{
            openSession();

            session.merge(object);

            closeSession();
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
        var object = findById(clazz, id);
        if(object != null){
            openSession();

            session.delete(object);

            closeSession();
        }
    }
}
