package practice_telegram_bot.telegram.commands.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.database.dao.DAO;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.service.Keyboard;
import practice_telegram_bot.telegram.commands.AvailableCommands;

public abstract class ServiceCommand extends BotCommand {
    ServiceCommand(String identifier, String description) {
        super(identifier, description);
    }

    public void sendAnswer(AbsSender absSender, Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Ошибка в отправке сообщения");
            e.printStackTrace();
        }
    }

    public void sendMarkupAnswer(AbsSender absSender, Long chatId, String text, StateEnum state){
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        message.setReplyMarkup(Keyboard.createMarkUp(
                AvailableCommands.getInStringList(state)
        ));
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Ошибка в отправке сообщения");
            e.printStackTrace();
        }
    }
}
