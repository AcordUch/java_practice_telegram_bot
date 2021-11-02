package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.enums.CommandEnum;
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
    private static final Map<CommandEnum, Command> COMMAND_MAP = Map.ofEntries(
            entry(CommandEnum.MATRIX, new StartMatrixCommand()),
            entry(CommandEnum.GAME, new StartGameCommand()),
            entry(CommandEnum.RETURN, new ReturnToMenuCommand()),
            entry(CommandEnum.OPERATIONS, new ChooseOperationsCommand())
    );

    public String processCommand(Long usedId, String input) {
        try
        {
            var command = CommandFormatter.formatCommand(input);
            if(command.isPresent()){
                return COMMAND_MAP.get(command.get()).execute(usedId, input).formAnswer();
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
