package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.database.dao.DAO;
import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.enums.CommandEnum;
import practice_telegram_bot.exceptions.TooLongSentenceExceptions;
import practice_telegram_bot.service.CommandEventInitiater;
import practice_telegram_bot.service.CommandEventListener;
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

    public String processCommand(Long userId, String input) {
        UserDB userDBData = findUserData(userId);

        try
        {
            var command = CommandFormatter.formatCommand(
                    input.toLowerCase(Locale.ROOT), userDBData.getState()
            );
            if(command.isPresent()){
                return COMMAND_MAP.get(command.get()).execute(userId, input, userDBData).formAnswer();
            } else {
                return "В нынешнем состоянии команда не доступна";
            }
        } catch (TooLongSentenceExceptions ex) {
            return "Вы ввели слишком длинное предложение, попробуйте сформулировать что вы хотите покороче";
        } finally {
            DAO.instance().update(userDBData);
        }
    }

    private UserDB findUserData(Long userId){
        UserDB userDBData = DAO.instance().findById(UserDB.class, userId);
        if (userDBData == null) {
            userDBData = new UserDB(userId);
            DAO.instance().save(userDBData);
        }
        if(userDBData.getMatrixData() != null){
            userDBData.setPrev_id(userDBData.getMatrixData().getId());
        }
        return userDBData;
    }

    public void addListenerForCommands(CommandEventListener listener){
        for(var command : COMMAND_MAP.values()){
            if(command instanceof CommandEventInitiater){
                ((CommandEventInitiater) command).addListener(listener);
            }
        }
    }
}
