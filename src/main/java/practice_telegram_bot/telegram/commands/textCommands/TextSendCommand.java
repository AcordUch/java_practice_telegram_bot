package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.telegram.commands.Command;

public class TextSendCommand implements Command {
    public static final String TEXTCOMMPREFIX = "@tcmd";
    private String answer = "";

    public static String formText(String text){
        return TEXTCOMMPREFIX + " " + text;
    }

    @Override
    public String formAnswer() {
        return answer;
    }

    @Override
    public Command execute(Long chatId, String addInfo) {
        answer = addInfo.split(" ", 2)[1];
        return this;
    }
}
