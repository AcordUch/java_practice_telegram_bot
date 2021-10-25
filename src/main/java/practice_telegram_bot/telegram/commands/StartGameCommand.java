package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.states.StateEnum;
import practice_telegram_bot.telegram.UsersData;

public class StartGameCommand implements Command {
    private static final String ANSWER = """
            Игра находится в разработке. Вернитесь в меню
            """;
    @Override
    public String formAnswer() {
        return ANSWER;
    }

    @Override
    public Command execute(Long chatId) {
        UsersData.setUsersState(chatId, StateEnum.GAME_START);
        return this;
    }
}
