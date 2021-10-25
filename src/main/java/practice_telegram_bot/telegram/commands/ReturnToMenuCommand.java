package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.states.StateEnum;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.service.StartCommand;

public class ReturnToMenuCommand implements Command {
    @Override
    public String formAnswer() {
        return StartCommand.GetAnswer();
    }

    @Override
    public Command execute(Long chatId) {
        UsersData.setUsersState(chatId, StateEnum.START);
        return this;
    }
}
