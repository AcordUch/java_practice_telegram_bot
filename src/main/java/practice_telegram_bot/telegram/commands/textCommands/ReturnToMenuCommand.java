package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.Command;
import practice_telegram_bot.telegram.commands.service.StartCommand;

public class ReturnToMenuCommand implements Command {
    @Override
    public String formAnswer() {
        return StartCommand.GetAnswer();
    }

    @Override
    public Command execute(Long chatId, String addInfo) {
        UsersData.setUsersState(chatId, StateEnum.START);
        return this;
    }
}
