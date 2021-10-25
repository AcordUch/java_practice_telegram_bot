package practice_telegram_bot.telegram.commands.service;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import practice_telegram_bot.states.StateEnum;
import practice_telegram_bot.telegram.UsersData;

public class StartCommand extends ServiceCommand{
    public StartCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        Long chatId = chat.getId();
        if(!UsersData.containUserState(chatId)){
            UsersData.setUsersState(chatId, StateEnum.START);
        }
        sendAnswer(absSender, chat.getId(),
                "Давайте начнём! Если Вам нужна помощь, нажмите /help");
    }
}
