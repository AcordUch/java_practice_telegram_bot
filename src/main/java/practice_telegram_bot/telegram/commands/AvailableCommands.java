package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.enums.StateEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import static java.util.Map.entry;
import static practice_telegram_bot.enums.StateEnum.*;

public class AvailableCommands {
    public static final String RETURN_COMMAND = "меню";

    private static final Map<StateEnum, List<String>> availableCommands = Map.ofEntries(
            entry(START, Arrays.asList("игра", "матрица")),
            entry(MATRIX_OPERATION_SELECT,
                    Arrays.asList(RETURN_COMMAND, "операция")),
            entry(GAME_START, Arrays.asList(RETURN_COMMAND))
    );

    public static List<String> getAvailableCommands(StateEnum state){
        try {
            return availableCommands.get(state);
        }
        catch(Exception ex){
            System.out.println("Ошибка в getAvailableCommands: значение ключа не найдено");
            return new ArrayList<String>();
        }
    }

    public static boolean checkingForAvailability(StateEnum state, String command){
        return getAvailableCommands(state).contains(command);
    }

    public static String getAvailableCommandsAsString(StateEnum state){
        return String.join("\n", getAvailableCommands(state));
    }
}
