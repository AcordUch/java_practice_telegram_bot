package practice_telegram_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import practice_telegram_bot.database.dao.DAO;
import practice_telegram_bot.database.dao.PostgreSqlSessionFactory;
import practice_telegram_bot.telegram.*;


import java.util.Map;

public class Main {
    private static Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {
        if(!getenv.containsKey("BOT_NAME")){
            getenv = TokenParser.GetEnv();
        }

        connectToDB();

        try{
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(getenv.get("BOT_NAME"), getenv.get("BOT_TOKEN")));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }

    private static void connectToDB(){
        try {
            DAO.configureForPostgreSql();
            // При отсутствии какой-либо базы данных на компьютере, заменить верхнюю строчку на эту
//            DAO.configureForRAM();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}