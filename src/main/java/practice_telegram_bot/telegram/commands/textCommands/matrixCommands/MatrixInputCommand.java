package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.service.CommandEventInitiater;
import practice_telegram_bot.service.CommandEventListener;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;

public class MatrixInputCommand extends CommandEventInitiater implements Command  {
    private String answer = "";

    @Override
    public void addListener(CommandEventListener listener) {
        eventListeners.add(listener);
    }

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
                    notifyListeners(chatId, "результат");
                    answer = "Ввод матрицы завершен";
                }
                else{
                    UsersData.setUsersState(chatId, StateEnum.MATRIX_SIZE_INPUT);
                    if(matrixData.operation.sameMatrixSize){
                        notifyListeners(chatId, matrixData.getLastMatrixSizeAsString());
                    }
                    else{
                        answer = "Введите размер следующей матрицы";
                    }
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
