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
        instance = new PostgreSqlDao();
    }

    public static void configureForMongoDb(){
        instance = new MongoDbDao();
    }
}
