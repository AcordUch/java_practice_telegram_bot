package practice_telegram_bot.service;

public interface CommandEventListener {
    void executeNextCommand(Long chatId, String message);
}
