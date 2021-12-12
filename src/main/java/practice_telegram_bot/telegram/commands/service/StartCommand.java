package practice_telegram_bot.telegram.commands.service;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import practice_telegram_bot.database.MatrixBuilderData;
import practice_telegram_bot.database.MatrixDataDB;
import practice_telegram_bot.database.StateManagerAOD;
import practice_telegram_bot.database.UserDataAOD;
import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.matrix.Matrix;
import practice_telegram_bot.telegram.UsersData;

public class StartCommand extends ServiceCommand{
    private static final String ANSWER = """
            Приветствую вас, давайте начнём!
            Я могу:
               Провести для вас различные операции с матрицами: "матрицы", "матрица"
               Сыграть в игру "Быки и коровы": "игра", "Быки и коровы"
            Если Вам нужна помощь, нажмите /help
            """;
    public StartCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        Long chatId = chat.getId();
        UsersData.instance().setUsersState(chatId, StateEnum.START);
        StateManagerAOD.delete(chatId);
        StateManagerAOD.save(new practice_telegram_bot.database.User(chatId));
        /*var user2 = new practice_telegram_bot.database.User(chatId);
        user2.setMatrixData(new MatrixDataDB(Operations.ADDITION, 2));
        var matrix = new Matrix(2);
        user2.getMatrixData().setMatrixBuilder(new MatrixBuilderData(matrix.packForDB(), 0));
        StateManagerAOD.save(user2);*/
        sendAnswer(absSender, chat.getId(), ANSWER);
    }

    public static String GetAnswer(){
        return ANSWER;
    }
}
