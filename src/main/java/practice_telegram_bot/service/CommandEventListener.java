package practice_telegram_bot.service;

public interface CommandEventListener {
    void addUpdate(InnerUpdate innerUpdate);
}
