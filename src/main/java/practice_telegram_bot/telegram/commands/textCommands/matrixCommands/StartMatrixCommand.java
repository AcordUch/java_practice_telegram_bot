package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.commands.Command;

public class StartMatrixCommand implements Command {
    public static final String ANSWER = """
            Доступны следующие операции с матрицами:
               Решить систему методом Гаусса: "Гаусс"
               Нахождение определителя: "определитель"
               Сложение и вычитание матриц: "сложение" или "вычитание"
               Умножение матриц: "умножение"
            """;

    @Override
    public String formAnswer() {
        return ANSWER;
    }

    @Override
    public Command execute(Long chatId, String addInfo, UserDB userDBData) {
//        UsersData.instance().setUsersState(chatId, StateEnum.MATRIX_OPERATION_SELECT);
//        UsersData.instance().clearUsersMatrixData(chatId);
        userDBData.setState(StateEnum.MATRIX_OPERATION_SELECT);
//        userData.setMatrixData(null);
        return this;
    }
}
