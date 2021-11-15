package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.enums.CommandEnum;
import practice_telegram_bot.exceptions.TooLongSentenceExceptions;
import practice_telegram_bot.service.CommandEventInitiater;
import practice_telegram_bot.service.CommandEventListener;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;
import practice_telegram_bot.telegram.commands.textCommands.gameCommands.StartGameCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.*;

import java.util.Locale;
import java.util.Map;

import static java.util.Map.entry;

public class CommandManager {
    public static final ReturnToMenuCommand RETURN_TO_MENU_COMMAND = new ReturnToMenuCommand();
    private static final Map<CommandEnum, Command> COMMAND_MAP = Map.ofEntries(
            entry(CommandEnum.MATRIX, new StartMatrixCommand()),
            entry(CommandEnum.GAME, new StartGameCommand()),
            entry(CommandEnum.RETURN, RETURN_TO_MENU_COMMAND),
            entry(CommandEnum.MATRIX_OPERATIONS, new ChooseOperationsCommand()),
            entry(CommandEnum.MATRIX_SIZE, new MatrixSizeInputCommand()),
            entry(CommandEnum.MATRIX_ROW, new MatrixInputCommand()),
            entry(CommandEnum.MATRIX_RESULT, new MatrixResultOutputCommand()),
            entry(CommandEnum.TEXT_SEND, new TextSendCommand())
    );
    //TODO: Добавить возможность во время ввода матрицы возвратиться к выбору операции
    public String processCommand(Long usedId, String input) {
        try
        {
            var command = CommandFormatter.formatCommand(
                    input.toLowerCase(Locale.ROOT), UsersData.getUserState(usedId)
            );
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

    public void addListenerForCommands(CommandEventListener listener){
        for(var command : COMMAND_MAP.values()){
            if(command instanceof CommandEventInitiater){
                ((CommandEventInitiater) command).addListener(listener);
            }
        }
    }
}
