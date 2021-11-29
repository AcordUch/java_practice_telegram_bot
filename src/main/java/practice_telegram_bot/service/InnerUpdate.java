package practice_telegram_bot.service;

public class InnerUpdate {
    private final Long chatId;
    private final String message;

    public InnerUpdate(Long chatId, String message){
        this.chatId = chatId;
        this.message = message;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getMessage() {
        return message;
    }
}
