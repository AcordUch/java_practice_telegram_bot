package practice_telegram_bot.telegram.commands.textCommands.matrixCommands;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.matrix.MatrixOperationsController;
import practice_telegram_bot.service.CommandEventInitiater;
import practice_telegram_bot.service.CommandEventListener;
import practice_telegram_bot.telegram.MatrixImageCreator;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;
import practice_telegram_bot.telegram.commands.textCommands.TextSendCommand;

public class MatrixResultOutputCommand extends CommandEventInitiater implements Command{
    private static final String ANSWER = """
            Типо результат. Перенаправление на выбор операции
            """;

    private String answer = ANSWER;

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
        var userData = UsersData.getUserMatrixData(chatId);
        try {
            var matrix = MatrixOperationsController.makeOperation(userData);
            if(matrix.isPresent()) {
                answer = matrix.get().toString();
                var picture = MatrixImageCreator.instance().createImage(matrix.get()).getImage();
                notifyListeners(chatId, picture);
            }
            else{
                answer = "Матрицы не совпадают по размеру\nWIP: или данная операция ещё не реализована";
            }
        } catch (IncorrectNumberOfElements e) {
            answer = e.toString();
        }
        UsersData.setUsersState(chatId, StateEnum.MATRIX_OPERATION_SELECT);
        notifyListeners(chatId, TextSendCommand.formText(StartMatrixCommand.ANSWER));

        return this;
        //TODO: Добавить возможность ввести матрицы повторно
    }
}
