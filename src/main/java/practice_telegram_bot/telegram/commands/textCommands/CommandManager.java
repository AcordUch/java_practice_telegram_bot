package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.exceptions.TooLongSentenceExceptions;
import practice_telegram_bot.telegram.commands.AvailableCommands;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;
import practice_telegram_bot.telegram.commands.textCommands.gameCommands.StartGameCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.ChooseOperationsCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.StartMatrixCommand;

import java.util.Map;

import static java.util.Map.entry;

public class CommandManager {
    private static final Map<String, Command> COMMAND_MAP = Map.ofEntries(
            entry("матрица", new StartMatrixCommand()),
            entry("игра", new StartGameCommand()),
            entry(AvailableCommands.RETURN_COMMAND, new ReturnToMenuCommand()),
            entry("операция", new ChooseOperationsCommand())
    );

    public String processCommand(Long usedId, String input) {
        var state = UsersData.getUserState(usedId);
        try
        {
            var command = CommandFormatter.formatCommand(input);
            if(AvailableCommands.checkingForAvailability(state, command)){
                return COMMAND_MAP.get(command).execute(usedId, input).formAnswer();
            }
            else{
                return "В нынешнем состоянии команда не доступна";
            }
        }
        catch (TooLongSentenceExceptions ex) {
            return "Вы ввели слишком длинное предложение, попробуйте сформулировать что вы хотите покороче";
        }
    }
}
