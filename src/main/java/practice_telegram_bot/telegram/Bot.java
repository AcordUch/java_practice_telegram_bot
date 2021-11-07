package practice_telegram_bot.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import practice_telegram_bot.service.CommandEventListener;
import practice_telegram_bot.service.InnerUpdate;
import practice_telegram_bot.telegram.commands.service.AboutCommand;
import practice_telegram_bot.telegram.commands.service.HelpCommand;
import practice_telegram_bot.telegram.commands.textCommands.CommandManager;
import practice_telegram_bot.telegram.commands.service.StartCommand;
import practice_telegram_bot.telegram.commands.service.StateCommand;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Locale;


public class Bot extends TelegramLongPollingCommandBot implements CommandEventListener {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final CommandManager commandManager = new CommandManager();
    private final Deque<InnerUpdate> updateToProcess = new ArrayDeque<>();

    public Bot(String botName, String botToken)
    {
        BOT_NAME = botName;
        BOT_TOKEN = botToken;
        commandManager.addListenerForCommands(this);
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
            Long chatId = message.getChatId();
            String userName =getUserName(message);
            System.out.printf("Пользователь: %s\nChatID: %s\nСообщение: %s\n", userName, chatId.toString(), message.getText());
        }
        super.onUpdatesReceived(updates);
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        var answer = commandManager.processCommand(chatId, message.getText().toLowerCase(Locale.ROOT));
        if(!answer.isEmpty()){
            sendAnswer(chatId, answer);
        }
        checkOnInnerUpdateAndProcess();
    }

    public void processNonCommandUpdate(InnerUpdate update){
        String message = update.getMessage();
        Long chatId = update.getChatId();
        var answer = commandManager.processCommand(chatId, message.toLowerCase(Locale.ROOT));
        if(!answer.isEmpty()){
            sendAnswer(chatId, answer);
        }
        checkOnInnerUpdateAndProcess();
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

    @Override
    public void executeNextCommand(Long chatId, String message) {
        updateToProcess.add(new InnerUpdate(chatId, message));
    }

    public void checkOnInnerUpdateAndProcess(){
        if(!updateToProcess.isEmpty()){
            processNonCommandUpdate(updateToProcess.pop());
        }
    }
}
