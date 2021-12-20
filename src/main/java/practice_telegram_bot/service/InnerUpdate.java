package practice_telegram_bot.service;

import java.io.File;

public class InnerUpdate {
    private final Long chatId;
    private final String message;
    private final File picture;
    public final boolean setMarkup;

    public InnerUpdate(Long chatId, String message){
        this(chatId, message, false);
    }

    public InnerUpdate(Long chatId, File picture){
        this(chatId, picture, false);
    }

    public InnerUpdate(Long chatId, String message, boolean setMarkup){
        this.chatId = chatId;
        this.message = message;
        this.picture = null;
        this.setMarkup = setMarkup;
    }

    public InnerUpdate(Long chatId, File picture, boolean setMarkup){
        this.chatId = chatId;
        this.picture = picture;
        this.message = null;
        this.setMarkup = setMarkup;
    }

    public Long getChatId() {
        return chatId;
    }

    public TryWrapper<String> tryGetMessage(){
        return new TryWrapper<>(message != null, message);
    }

    public TryWrapper<File> tryGetPicture(){
        return new TryWrapper<>(picture != null, picture);
    }
}
