package practice_telegram_bot.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil2 {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil2() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration =
                        new Configuration().configure();

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(MatrixData.class);
                configuration.addAnnotatedClass(MatrixPlain.class);
                configuration.addAnnotatedClass(MatrixBuilderData.class);
                configuration.addAnnotatedClass(MatrixDb.class);

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
