package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.enums.CommandEnum;
import practice_telegram_bot.exceptions.TooLongSentenceExceptions;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;
import practice_telegram_bot.telegram.commands.textCommands.gameCommands.StartGameCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.*;

import java.util.Map;

import static java.util.Map.entry;

public class CommandManager {
    private static final Map<CommandEnum, Command> COMMAND_MAP = Map.ofEntries(
            entry(CommandEnum.MATRIX, new StartMatrixCommand()),
            entry(CommandEnum.GAME, new StartGameCommand()),
            entry(CommandEnum.RETURN, new ReturnToMenuCommand()),
            entry(CommandEnum.MATRIX_OPERATIONS, new ChooseOperationsCommand()),
            entry(CommandEnum.MATRIX_SIZE, new MatrixSizeInputCommand()),
            entry(CommandEnum.MATRIX_ROW, new MatrixInputCommand()),
            entry(CommandEnum.MATRIX_RESULT, new MatrixResultOutputCommand())
    );

    public String processCommand(Long usedId, String input) {
        try
        {
            var command = CommandFormatter.formatCommand(input, UsersData.getUserState(usedId));
            if(command.isPresent()){
                var temp = COMMAND_MAP.get(command.get());
                return temp.execute(usedId, input).formAnswer();
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
