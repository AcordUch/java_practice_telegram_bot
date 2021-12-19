package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.database.UserDB;

public interface Command {
    String formAnswer();
    Command execute(Long chatId, String extraInfo, UserDB userDBData);
}
