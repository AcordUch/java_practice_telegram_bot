package practice_telegram_bot.database.dao;

public class DAO {
    private static IDAO instance;

    private DAO(){}

    public static IDAO instance(){
        if(instance == null){
            configureForPostgreSql();
        }
        return instance;
    }

    public static void configureForPostgreSql(){
        PostgreSqlSessionFactory.instance();
        instance = new PostgreSqlDao();
    }

    public static void configureForMongoDb(){
        MongoDbSessionFactory.instance();
        instance = new MongoDbDao();
    }

    public static void configureForRAM(){
        UsersData.instance();
        instance = new RAMDao();
    }
}
