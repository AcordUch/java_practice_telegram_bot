package practice_telegram_bot.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import practice_telegram_bot.telegram.commands.service.AboutCommand;
import practice_telegram_bot.telegram.commands.service.HelpCommand;
import practice_telegram_bot.telegram.commands.textCommands.CommandManager;
import practice_telegram_bot.telegram.commands.service.StartCommand;
import practice_telegram_bot.telegram.commands.service.StateCommand;

import java.util.List;
import java.util.Locale;

public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final CommandManager commandManager = new CommandManager();

    public Bot(String botName, String botToken)
    {
        BOT_NAME = botName;
        BOT_TOKEN = botToken;
        register(new StartCommand("start", "Начало общения"));
        register(new StateCommand("state", "Показывает текущее положение"));
        register(new AboutCommand("about", "Показывает информацию о создателях бота"));
        register(new HelpCommand("help", "Показывает информацию о боте и доступные команды"));
    }
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        System.out.println("\nnew Update Received");
        for (var update : updates) {
            Message message = update.getMessage();
            Long chatID = message.getChatId();
            String userName =getUserName(message);
            System.out.printf("Пользователь: %s\nChatID: %s\nСообщение: %s\n", userName, chatID.toString(), message.getText());
        }
        super.onUpdatesReceived(updates);
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        Long chatID = message.getChatId();
        String userName =getUserName(message);
        var answer = commandManager.processCommand(chatID, message.getText().toLowerCase(Locale.ROOT));
        sendAnswer(chatID, answer);
    }

    private String getUserName(Message msg){
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

    private void sendAnswer(Long chatId, String text){
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try{
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
