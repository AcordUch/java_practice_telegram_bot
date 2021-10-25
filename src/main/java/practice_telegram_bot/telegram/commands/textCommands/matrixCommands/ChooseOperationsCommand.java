package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.MatrixData;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;

import java.util.Arrays;
import java.util.List;

public class ChooseOperationsCommand implements Command {
    private static final List<String> operationsWithTwoMatrix = Arrays.asList("сложение", "вычитание", "умножение");

    private static final String ANSWER_SQUARE_MATRIX = """
            Введите размер матрицы в виде одного числа n
            Пример: 3
            """;
    private static final String ANSWER_COMMON_MATRIX = """
            Введите число строк и столбцов матрицы через пробел в виде m n
            Пример: 5 6
            """;

    private String _addInfo = "";

    @Override
    public String formAnswer() {
        return _addInfo.equals("гаусс") ? ANSWER_SQUARE_MATRIX : ANSWER_COMMON_MATRIX;
    }

    @Override
    public Command execute(Long chatId, String addInfo) {
        _addInfo = addInfo;
        UsersData.setUsersState(chatId, StateEnum.MATRIX_SIZE_INPUT);
        int matricesNumber = operationsWithTwoMatrix.contains(addInfo) ? 2 : 1;
        UsersData.setUsersMatrixData(chatId, new MatrixData(addInfo, matricesNumber));
        var temp = UsersData.getUserMatrixData(1L);
        return this;
    }
}
