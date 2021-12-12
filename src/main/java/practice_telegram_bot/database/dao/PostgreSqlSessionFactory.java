package practice_telegram_bot.database.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import practice_telegram_bot.database.MatrixBuilderData;
import practice_telegram_bot.database.MatrixDataDB;
import practice_telegram_bot.database.PlainMatrix;
import practice_telegram_bot.database.UserDB;

public class PostgreSqlSessionFactory {
    private static SessionFactory sessionFactory;

    private PostgreSqlSessionFactory() {}

    public static SessionFactory instance() {
        if (sessionFactory == null) {
            try {
                Configuration configuration =
                        new Configuration().configure();

                configuration.addAnnotatedClass(UserDB.class);
                configuration.addAnnotatedClass(MatrixDataDB.class);
                configuration.addAnnotatedClass(PlainMatrix.class);
                configuration.addAnnotatedClass(MatrixBuilderData.class);

                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                sessionFactory = new Configuration().configure().buildSessionFactory();

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
