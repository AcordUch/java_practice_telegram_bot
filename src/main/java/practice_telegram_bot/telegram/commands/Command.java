package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.database.User;

public interface Command {
    String formAnswer();
    Command execute(Long chatId, String extraInfo, User userData);
}
