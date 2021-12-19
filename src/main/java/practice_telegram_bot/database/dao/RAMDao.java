package practice_telegram_bot.database.dao;

import practice_telegram_bot.database.UserDB;

public class RAMDao implements IDAO{
    @Override
    public <T> T findById(Class<T> clazz, Long id) {
        return clazz == UserDB.class ? (T)UsersData.instance().getUser(id) : null;
    }

    @Override
    public <T> boolean existInDatabase(Class<T> clazz, Long id) {
        return findById(clazz, id) != null;
    }

    @Override
    public <T> boolean absentInDatabase(Class<T> clazz, Long id) {
        return !existInDatabase(clazz, id);
    }

    @Override
    public <T> void save(T object) {
        if(object.getClass() == UserDB.class){
            UsersData.instance().setUser((UserDB) object);
        }
    }

    @Override
    public <T> void update(T object) {
        if(object.getClass() == UserDB.class){
            UsersData.instance().setUser((UserDB) object);
        }
    }

    @Override
    public <T> void delete(Class<T> clazz, Long id) {
        if(clazz == UserDB.class){
            UsersData.instance().deleteUser(id);
        }
    }
}
