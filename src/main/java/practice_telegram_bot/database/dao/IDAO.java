package practice_telegram_bot.database.dao;

public interface IDAO {
    <T> T findById(Class<T> clazz, Long id);
    <T> boolean existInDatabase(Class<T> clazz, Long id);
    <T> boolean absentInDatabase(Class<T> clazz, Long id);
    <T> void save(T object);
    <T> void update(T object);
    <T> void delete(Class<T> clazz, Long id);
}
