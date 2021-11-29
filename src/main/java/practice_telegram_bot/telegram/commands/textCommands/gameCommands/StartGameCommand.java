package practice_telegram_bot.telegram.commands.textCommands.gameCommands;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;

public class StartGameCommand implements Command {
    private static final String ANSWER = """
            Игра находится в разработке. Вернитесь в меню
            """;
    @Override
    public String formAnswer() {
        return ANSWER;
    }

    @Override
    public Command execute(Long chatId, String addInfo) {
        UsersData.setUsersState(chatId, StateEnum.GAME_START);
        return this;
    }
}
