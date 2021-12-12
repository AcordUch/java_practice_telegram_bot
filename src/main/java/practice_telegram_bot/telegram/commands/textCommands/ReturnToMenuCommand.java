package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.database.StateManagerAOD;
import practice_telegram_bot.database.User;
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
    public Command execute(Long chatId, String addInfo, User userData) {
        if(userData == null){
            userData = StateManagerAOD.findById(chatId);
        }
        UsersData.instance().setUsersState(chatId, StateEnum.START);
        userData.setState(StateEnum.START);
        return this;
    }
}
