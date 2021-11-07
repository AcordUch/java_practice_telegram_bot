package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;

import java.util.Map;

public class MatrixResultOutputCommand implements Command {
    private static final String ANSWER = """
            Типо результат. Перенаправление на выбор операции
            """;

    private String answer = ANSWER;

    @Override
    public String formAnswer() {
        return answer;
    }

    @Override
    public Command execute(Long chatId, String addInfo) {
        var matrixData = UsersData.getUserMatrixData(chatId);
        UsersData.setUsersState(chatId, StateEnum.MATRIX_OPERATION_SELECT);
        return this;
    }
}
