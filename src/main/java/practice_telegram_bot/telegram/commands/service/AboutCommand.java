package practice_telegram_bot.telegram.commands.service;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import practice_telegram_bot.telegram.commands.AvailableCommands;
import practice_telegram_bot.telegram.UsersData;

public class AboutCommand extends ServiceCommand {
    public AboutCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendAnswer(absSender, chat.getId(), "Created by AcordUch & lGreenLightl");
    }
}
