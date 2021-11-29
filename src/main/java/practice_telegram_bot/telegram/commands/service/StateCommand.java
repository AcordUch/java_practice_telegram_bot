package practice_telegram_bot.telegram.commands.service;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import practice_telegram_bot.telegram.commands.AvailableCommands;
import practice_telegram_bot.telegram.UsersData;

public class StateCommand extends ServiceCommand {
    public StateCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        Long chatId = chat.getId();
        var state = UsersData.getUserState(chatId);
        sendAnswer(absSender, chatId,
                String.format("Текущее состояние: %s\nДоступные команды: \n%s",
                        state,
                        AvailableCommands.getAvailableCommandsAsString(state)));
    }
}
