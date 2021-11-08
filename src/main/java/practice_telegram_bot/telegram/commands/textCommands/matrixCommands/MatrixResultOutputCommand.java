package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.exceptions.NotEqualSizesOfMatrixException;
import practice_telegram_bot.matrix.MatrixOperations;
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
        if(matrixData.matrixCount() == 2){
            try {
                answer = MatrixOperations.matrixAddition(
                        matrixData.tryGetMatrix(0).get(),
                        matrixData.tryGetMatrix(1).get()
                ).toString();
            } catch (NotEqualSizesOfMatrixException e) {
                answer = "У матриц разные размеры";
            }
        }
        UsersData.setUsersState(chatId, StateEnum.MATRIX_OPERATION_SELECT);
        return this;
        //TODO: Добавить возможность ввести матрицы повторно
    }
}
