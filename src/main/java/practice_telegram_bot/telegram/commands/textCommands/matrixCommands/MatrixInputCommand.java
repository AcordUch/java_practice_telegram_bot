package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.service.CommandEventInitiater;
import practice_telegram_bot.service.CommandEventListener;
import practice_telegram_bot.telegram.MatrixData;
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
    public Command execute(Long chatId, String addInfo, UserDB userDBData){
        var matrixData = MatrixData.restoreFromDB(userDBData.getMatrixData());
        var builder = matrixData.getMatrixBuilder();
        var lines = addInfo.split("\n");

        for(var line : lines){
            if(line.split(" ").length != builder.getMatrixHorizontalSize()){
                answer = "Вы ввели неправильное количество элементов, попробуйте ещё раз";
                return this;
            }

            try {
                builder.addRow(line);
                answer = "";
                if(builder.checkForFilling()){
                    matrixData.buildMatrix();

                    if(matrixData.matrixInputCompleted()) {
                        userDBData.setState(StateEnum.MATRIX_RESULT_OUTPUT);
                        notifyListeners(chatId, "результат");
                        answer = "Ввод матрицы завершен";
                    } else {
                        userDBData.setState(StateEnum.MATRIX_SIZE_INPUT);
                        if(matrixData.operation.sameMatrixSize) {
                            notifyListeners(chatId, matrixData.lastMatrixSizeToString());
                        } else {
                            answer = "Введите размер следующей матрицы";
                        }
                    }
                }
            } catch (NumberFormatException e){
                answer = "Вы ввели некорректное значение, попробуйте ещё раз";
                return this;
            } catch (IncorrectNumberOfElements e) {
                answer = "Произошла непонятная ошибка, попробуйте ещё раз";
                return this;
            }
        }

        userDBData.setMatrixData(matrixData.packForDB());
        return this;
    }
}
