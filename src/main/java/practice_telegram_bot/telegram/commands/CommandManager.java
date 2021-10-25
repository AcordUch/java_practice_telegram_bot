package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.states.AvailableCommands;
import practice_telegram_bot.telegram.UsersData;

public class CommandManager {
    public String processCommand(Long usedId, String command) {
        var state = UsersData.getUserState(usedId);
        if(AvailableCommands.checkingForAvailability(state, command)){
            //TODO
            return "//TODO: Команда доступна";
        }
        else{
            return "В нынешнем состоянии команда не доступна";
        }
    }
}
