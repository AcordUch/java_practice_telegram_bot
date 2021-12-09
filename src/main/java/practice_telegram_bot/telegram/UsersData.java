package practice_telegram_bot.telegram;

import practice_telegram_bot.enums.StateEnum;

import java.util.HashMap;
import java.util.Map;

public class UsersData {
    private final Map<Long, StateEnum> usersState = new HashMap<>();
    private final Map<Long, MatrixDataLegacy> usersMatrixData = new HashMap<>();

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

    public StateEnum getUserState(Long chatId){
        var res = usersState.get(chatId);
        if(res == null){
            System.out.printf("method getUserState; usedId %s не найдено", chatId);
        }
        return res;
    }

    public void setUsersState(Long chatId, StateEnum state){
        usersState.put(chatId, state);
    }

    public boolean containUserState(Long chatId){
        return usersState.containsKey(chatId);
    }

    public boolean containUserMatrix(Long chatId){
        return usersMatrixData.containsKey(chatId);
    }

    public MatrixDataLegacy getUserMatrixData(Long userId){
        var result = usersMatrixData.get(userId);
        if(result == null){
            System.out.printf("method getUserMatrix; usedId %s не найдено", userId);
        }
        return result;
    }

    public void setUsersMatrixData(Long userId, MatrixDataLegacy matrixData){
        usersMatrixData.put(userId, matrixData);
    }

    public void clearUsersMatrixData(Long userId){
        usersMatrixData.remove(userId);
    }
}
