package practice_telegram_bot.telegram;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.matrix.Matrix;

import java.util.HashMap;
import java.util.Map;

public class UsersData {
    private static final Map<Long, StateEnum> usersState = new HashMap<>();
    private static final Map<Long, MatrixData> usersMatrixData = new HashMap<>();

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
        return usersMatrixData.containsKey(chatId);
    }

    public static MatrixData getUserMatrixData(Long userId){
        var res = usersMatrixData.get(userId);
        if(res == null){
            System.out.printf("method getUserMatrix; usedId %s не найдено", userId);
        }
        return res;
    }

    public static void setUsersMatrixData(Long userId, MatrixData matrixData){
        usersMatrixData.put(userId, matrixData);
    }

    public static void clearUsersMatrixData(Long userId){
        usersMatrixData.remove(userId);
    }
}
