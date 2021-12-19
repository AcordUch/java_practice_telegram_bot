package practice_telegram_bot.database.dao;

import practice_telegram_bot.database.UserDB;

import java.util.HashMap;
import java.util.Map;

public class UsersData {
    private final Map<Long, UserDB> users = new HashMap<>();

    private static UsersData instance = null;

    private UsersData(){}

    public static UsersData instance(){
        if(instance == null){
            instance = new UsersData();
        }
        return instance;
    }

    public static void clearInstance(){
        instance = null;
    }

    public UserDB getUser(Long id){
        return users.getOrDefault(id, null);
    }

    public void setUser(UserDB user){
       users.put(user.getId(), user);
    }

    public void deleteUser(Long id){
        users.remove(id);
    }
}
