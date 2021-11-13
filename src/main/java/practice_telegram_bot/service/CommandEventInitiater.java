package practice_telegram_bot.service;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandEventInitiater implements CommandEventInitiaterInterface {
    protected final List<CommandEventListener> eventListeners = new ArrayList<>();

    public abstract void addListener(CommandEventListener listener);

    protected void notifyListeners(Long chatId, String message){
        for (var listener : eventListeners){
            listener.executeNextCommand(new InnerUpdate(chatId, message));
        }
    }
}
