package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.database.User;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.matrix.MatrixBuilder;
import practice_telegram_bot.telegram.MatrixData;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;


public class MatrixSizeInputCommand implements Command {
    private static final String ANSWER_SQUARE_MATRIX = """
            Построчно введите матрицу. Элементы через пробел
            Пример: 3 4 5 -1 14.34 1
                    1 22 4 42 44 111
            """;

    private String answer = ANSWER_SQUARE_MATRIX;

    @Override
    public String formAnswer() {
        return answer;
    }

    @Override
    public Command execute(Long chatId, String addInfo, User userData) {
//        var matrixData = UsersData.instance().getUserMatrixData(chatId);
//        var operation = userData.getMatrixData().getOperation();
        var input = addInfo.split(" ");
        answer = ANSWER_SQUARE_MATRIX;

        if(input.length != userData.getMatrixData().getOperation().numOfSizeArguments){
            answer = "Вы ввели неправильное количество элементов, попробуйте ещё раз";

            return this;
        }
        try {
//            var matrixBuilderData = userData.getMatrixData().getMatrixBuilder();
////            matrixData.getMatrixBuilder().createNewMatrix(input);
//
//            var matrixBuilder = MatrixBuilder.restoreFromDB(matrixBuilderData);
//            matrixBuilder.createNewMatrix(input);
//            userData.getMatrixData().setMatrixBuilder(matrixBuilder.packForDB());
            var matrixData = MatrixData.restoreFromDB(userData.getMatrixData());
            matrixData.getMatrixBuilder().createNewMatrix(input);
            userData.setMatrixData(matrixData.packForDB());

            userData.setState(StateEnum.MATRIX_INPUT);
            UsersData.instance().setUsersState(chatId, StateEnum.MATRIX_INPUT);
        } catch (IncorrectNumberOfElements e) {
            answer = "Произошла непонятная ошибка, попробуйте ещё раз";
        } catch (NumberFormatException e){
            answer = "Вы ввели некорректное значение, попробуйте ещё раз";
        }
        return this;
    }
}
