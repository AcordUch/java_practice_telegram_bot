package practice_telegram_bot.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class HibernateSessionFactoryUtil {
//    @PersistenceUnit(unitName = "userPU")
    private static EntityManagerFactory entityManagerFactory;

    private HibernateSessionFactoryUtil() {}

    public static EntityManagerFactory getSessionFactory() {
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
