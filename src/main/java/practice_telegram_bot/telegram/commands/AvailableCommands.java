package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.enums.CommandEnum;
import practice_telegram_bot.enums.StateEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import static java.util.Map.entry;
import static practice_telegram_bot.enums.StateEnum.*;

public class AvailableCommands {
    public static final String RETURN_COMMAND = "меню";

    private static final Map<StateEnum, List<CommandEnum>> availableCommands = Map.ofEntries(
            entry(START, Arrays.asList(CommandEnum.GAME, CommandEnum.MATRIX)),
            entry(MATRIX_OPERATION_SELECT,
                    Arrays.asList(CommandEnum.RETURN, CommandEnum.OPERATIONS)),
            entry(GAME_START, Arrays.asList(CommandEnum.RETURN))
    );

    public static List<CommandEnum> getAvailableCommands(StateEnum state){
        try {
            return availableCommands.get(state);
        }
        catch(Exception ex){
            System.out.println("Ошибка в getAvailableCommands: значение ключа не найдено");
            return new ArrayList<CommandEnum>();
        }
    }

    public static String getAvailableCommandsAsString(StateEnum state){
        var res = new StringBuilder();
        for(var command : getAvailableCommands(state)){
            res.append(String.join("\n", command.getCommands())).append("\n");
        }
        return res.toString();
    }

    public static boolean checkingForAvailability(StateEnum state, CommandEnum command){
        return getAvailableCommands(state).contains(command);
    }
}
