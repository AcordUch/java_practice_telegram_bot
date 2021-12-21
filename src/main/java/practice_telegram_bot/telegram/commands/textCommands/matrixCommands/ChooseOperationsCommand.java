package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.database.MatrixDataDB;
import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.commands.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ChooseOperationsCommand implements Command {
    private static final List<Operations> operationsWithTwoMatrix = Arrays.asList(
            Operations.ADDITION, Operations.SUBTRACTIONS, Operations.MULTIPLICATION
    );

    private static final String ANSWER_SQUARE_MATRIX = """
            Введите размер матрицы в виде одного числа n
            Пример: 3
            """;
    private static final String ANSWER_COMMON_MATRIX = """
            Введите число строк и столбцов матрицы через пробел в виде m n
            Пример: 5 6
            """;

    private String answer;

    @Override
    public String formAnswer() {
        return answer;
    }

    public static String formAnswerByOperation(Operations operation){
        return operation.numOfSizeArguments == 1 ? ANSWER_SQUARE_MATRIX : ANSWER_COMMON_MATRIX;
    }

    @Override
    public Command execute(Long chatId, String addInfo, UserDB userDBData) {
        Operations operation = Operations.fromString(addInfo.toLowerCase(Locale.ROOT));
        int matricesNumber = operationsWithTwoMatrix.contains(operation) ? 2 : 1;

        if(operationsWithTwoMatrix.contains(operation)){
            userDBData.setState(StateEnum.MATRIX_NUMBER_INPUT);
            answer = MatrixNumberInputCommand.ANSWER;
        }else{
            userDBData.setState(StateEnum.MATRIX_SIZE_INPUT);
            answer = ChooseOperationsCommand.formAnswerByOperation(operation);
        }

        userDBData.setMatrixData(new MatrixDataDB(operation, matricesNumber));
        return this;
    }
}
