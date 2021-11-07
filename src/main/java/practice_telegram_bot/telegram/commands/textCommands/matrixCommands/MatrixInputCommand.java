package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;

public class MatrixInputCommand implements Command {
//    private static final String ANSWER_SQUARE_MATRIX = """
//            Построчно введите матрицу. Элементы через пробел
//            Пример: 3 4 5 -1 14.34 1
//                    1 22 4 42 44 111
//            """;

    private String answer = "";

    @Override
    public String formAnswer() {
        return answer;
    }

    @Override
    public Command execute(Long chatId, String addInfo) {
        var matrixData = UsersData.getUserMatrixData(chatId);
        var input = addInfo.split(" ");
        var builder = matrixData.getMatrixBuilder();
        if(input.length != builder.getMatrixHorizontalSize()){
            answer = "Вы ввели неправильное количество элементов, попробуйте ещё раз";
            return this;
        }
        try {
            builder.addRow(addInfo);
            answer = "";
            if(builder.checkForFilling()){
                matrixData.addMatrix(builder.buildMatrix());
                matrixData.reduceNumberOfMatricesToEnter();
                if(matrixData.numberOfMatricesToEnter() < 1){
                    UsersData.setUsersState(chatId, StateEnum.MATRIX_RESULT_OUTPUT);
                    answer = "Ввод матрицы завершен";
                }
                else{
                    UsersData.setUsersState(chatId, StateEnum.MATRIX_SIZE_INPUT);
                    answer = "Введите размер следующей матрицы";
                }
            }
        }
        catch (NumberFormatException e){
            answer = "Вы ввели некорректное значение, попробуйте ещё раз";
        } catch (IncorrectNumberOfElements e) {
            answer = "Произошла непонятная ошибка, попробуйте ещё раз";
        }
        return this;
    }
}
