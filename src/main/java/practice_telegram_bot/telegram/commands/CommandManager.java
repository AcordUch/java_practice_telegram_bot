package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.exceptions.TooLongSentenceExceptions;
import practice_telegram_bot.states.AvailableCommands;
import practice_telegram_bot.telegram.UsersData;

import java.util.Map;

import static java.util.Map.entry;

public class CommandManager {
    private static final Map<String, Command> COMMAND_MAP = Map.ofEntries(
            entry("матрица", new StartMatrixCommand())
    );

    public String processCommand(Long usedId, String command) {
        var state = UsersData.getUserState(usedId);
        try
        {
            command = CommandFormatter.formatCommand(command);
        }
        catch (TooLongSentenceExceptions ex) {
            return "Вы ввели слишком длинное предложение, попробуйте сформулировать что вы хотите покороче";
        }
        if(AvailableCommands.checkingForAvailability(state, command)){
            return COMMAND_MAP.get(command).execute(usedId).formAnswer();
        }
        else{
            return "В нынешнем состоянии команда не доступна";
        }
    }
}
