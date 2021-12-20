package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.enums.CommandEnum;
import practice_telegram_bot.enums.StateEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static practice_telegram_bot.enums.StateEnum.*;

public class AvailableCommands {
    public static final String RETURN_TO_MENU_COMMAND = "меню";

    private static final Map<StateEnum, List<CommandEnum>> availableCommands = Map.ofEntries(
            entry(START, List.of(CommandEnum.GAME, CommandEnum.MATRIX)),
            entry(MATRIX_OPERATION_SELECT,
                    List.of(CommandEnum.RETURN, CommandEnum.MATRIX_OPERATIONS)),
            entry(GAME_START, List.of(CommandEnum.RETURN)),
            entry(MATRIX_NUMBER_INPUT, List.of(CommandEnum.RETURN, CommandEnum.MATRIX_SIZE)),
            entry(MATRIX_SIZE_INPUT, List.of(CommandEnum.RETURN, CommandEnum.MATRIX_SIZE)),
            entry(MATRIX_INPUT, List.of(CommandEnum.RETURN, CommandEnum.MATRIX_ROW)),
            entry(MATRIX_RESULT_OUTPUT, List.of(CommandEnum.RETURN, CommandEnum.MATRIX_RESULT))
    );

    public static List<CommandEnum> get(StateEnum state){
        try {
            return availableCommands.get(state);
        }
        catch(Exception ex){
            System.out.println("Ошибка в getAvailableCommands: значение ключа не найдено");
            return new ArrayList<>();
        }
    }

    public static String getInString(StateEnum state){
        var res = new StringBuilder();
        for(var command : get(state)){
            res.append(String.join("\n", command.getCommands())).append("\n");
        }
        return res.toString();
    }

    public static List<String> getInStringList(StateEnum state){
        var res = new ArrayList<String>();
        for(var command : get(state)){
            res.addAll(command.getCommands());
        }
        return res;
    }

    public static boolean isAvailable(StateEnum state, CommandEnum command){
        return get(state).contains(command);
    }
}
