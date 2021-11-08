package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.exceptions.NotEqualSizesOfMatrixException;
import practice_telegram_bot.matrix.MatrixOperations;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;

public class TextSendCommand implements Command {
    private String answer = "";

    @Override
    public String formAnswer() {
        return answer;
    }

    @Override
    public Command execute(Long chatId, String addInfo) {
        //TODO эта должна быть внутренняя служебная команда для разбиения сообщения на несколько
        return this;
    }
}
