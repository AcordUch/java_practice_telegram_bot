package practice_telegram_bot.telegram.commands;

public interface Command {
    String formAnswer();
    Command execute(Long chatId, String extraInfo);
}
