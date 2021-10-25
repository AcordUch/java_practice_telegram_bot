package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.states.StateEnum;
import practice_telegram_bot.telegram.UsersData;

public class StartMatrixCommand implements Command {
    private static final String ANSWER = """
            Доступны следующие операции с матрицами:
               Решить систему методом Гауса: "Гаус", "решить", "решить систему"
               Нахождение определителя: "определитель"
               Сложение и вычитание матриц: "сложение" или "вычитание"
               Умножение матриц: "умножение"
            """;
    @Override
    public String formAnswer() {
        return ANSWER;
    }

    @Override
    public Command execute(Long chatId) {
        UsersData.setUsersState(chatId, StateEnum.MATRIX_START);
        return this;
    }
}
