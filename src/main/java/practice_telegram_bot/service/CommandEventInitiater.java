package practice_telegram_bot.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class CommandEventInitiater implements CommandEventInitiaterInterface {
    protected final List<CommandEventListener> eventListeners = new ArrayList<>();

    public abstract void addListener(CommandEventListener listener);

    protected void notifyListeners(Long chatId, String message){
        for (var listener : eventListeners){
            listener.addUpdate(new InnerUpdate(chatId, message));
        }
    }

    protected void notifyListeners(Long chatId, File picture){
        for (var listener : eventListeners){
            listener.addUpdate(new InnerUpdate(chatId, picture));
        }
    }
}
