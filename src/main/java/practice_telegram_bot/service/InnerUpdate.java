package practice_telegram_bot.service;

import java.io.File;

public class InnerUpdate {
    private final Long chatId;
    private final String message;
//    private final File picture;

    public InnerUpdate(Long chatId, String message){
        this.chatId = chatId;
        this.message = message;
//        this.picture = null;
    }

//    public InnerUpdate(Long chatId, File picture){
//        this.chatId = chatId;
//        this.picture = picture;
//        this.message = null;
//    }

    public Long getChatId() {
        return chatId;
    }

    public String getMessage() {
        return message;
    }

//    public File getPicture() {
//        return picture;
//    }
}
