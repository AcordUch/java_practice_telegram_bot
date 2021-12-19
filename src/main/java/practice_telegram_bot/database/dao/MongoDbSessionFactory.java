package practice_telegram_bot.database.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MongoDbSessionFactory {
    private static EntityManagerFactory entityManagerFactory;

    private MongoDbSessionFactory() {}

    public static EntityManagerFactory instance() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory( "userPU" );
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return entityManagerFactory;
    }
}
