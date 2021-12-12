package practice_telegram_bot.telegram.commands.service;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import practice_telegram_bot.database.dao.DAO;
import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.commands.AvailableCommands;

public class HelpCommand extends ServiceCommand {
    private static final String ANSWER_1 = """
            Я бот, который поможет вам провести различные операции с матрицами.
            Я могу подсчитать определитель, решить систему линейных уравнений методом Гаусса, умножить и
            умножить или вычесть матрицы.
            
            Список команд, доступных в нынешнем состоянии:
            """;
    private static final String ANSWER_2 = """
            Не стоит вводить супер большие числа и особо большие матрица, возможны ошибки. Вы предупреждены!
            
            Желаем удачи!
            """;

    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        Long chatId = chat.getId();
        var state = DAO.instance().findById(UserDB.class, chatId).getState();
        sendAnswer(absSender, chatId, makeHelpMessage(state));
    }

    private String makeHelpMessage(StateEnum state){
        return ANSWER_1 + "\n" +
                AvailableCommands.getAvailableCommandsAsString(state) + "\n" +
                ANSWER_2;
    }

    private String lookAvailableCommands(StateEnum state){
        var res = new StringBuilder();
        for(var command : AvailableCommands.getAvailableCommands(state)){
            res.append(command).append("\n");
        }
        return res.toString();
    }
}
