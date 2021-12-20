package practice_telegram_bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {
    public static InlineKeyboardButton createButton(String name){
        var inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(name);
        inlineKeyboardButton.setCallbackData(name);
        return inlineKeyboardButton;
    }

    public static InlineKeyboardMarkup createMarkUp(List<String> buttonNames){
        return createMarkUp(buttonNames,3);
    }
    public static InlineKeyboardMarkup createMarkUp(List<String> buttonNames, int rowCount){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        var keyboardRow = new ArrayList<InlineKeyboardButton>();
        for(var cnt = 0; cnt < buttonNames.size(); cnt++){
            if(cnt != 0 && cnt % rowCount == 0){
                keyboard.add(new ArrayList<>(keyboardRow));
                keyboardRow.clear();
            }
            keyboardRow.add((createButton(buttonNames.get(cnt))));
        }
        if(!keyboardRow.isEmpty()){
            keyboard.add(keyboardRow);
        }
        return new InlineKeyboardMarkup(keyboard);
    }
}
