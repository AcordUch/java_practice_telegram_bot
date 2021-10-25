package practice_telegram_bot.telegram;

import practice_telegram_bot.states.*;
import practice_telegram_bot.matrix.Matrix;

import java.util.HashMap;
import java.util.Map;

public class UsersData {
    private static final Map<Long, StateEnum> usersState = new HashMap<>();
    private static final Map<Long, Matrix> usersMatrix = new HashMap<>();

    public static StateEnum getUserState(Long chatId){
        var res = usersState.get(chatId);
        if(res == null){
            System.out.printf("method getUserState; usedId %s не найдено", chatId);
        }
        return res;
    }

    public static void setUsersState(Long chatId, StateEnum state){
        usersState.put(chatId, state);
    }

    public static boolean containUserState(Long chatId){
        return usersState.containsKey(chatId);
    }

    public static boolean containUserMatrix(Long chatId){
        return usersMatrix.containsKey(chatId);
    }

    public static Matrix getUserMatrix(Long userId){
        var res = usersMatrix.get(userId);
        if(res == null){
            System.out.printf("method getUserMatrix; usedId %s не найдено", userId);
        }
        return res;
    }

    public static void setUsersMatrix(Long userId, Matrix matrix){
        usersMatrix.put(userId, matrix);
    }
}
