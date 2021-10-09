package practice_telegram_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import practice_telegram_bot.telegram.*;


import java.util.Map;

public class Main {
    private static Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {
        if(!getenv.containsKey("BOT_NAME")){
            getenv = TokenParser.GetEnv();
        }
        try{
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(getenv.get("BOT_NAME"), getenv.get("BOT_TOKEN")));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}