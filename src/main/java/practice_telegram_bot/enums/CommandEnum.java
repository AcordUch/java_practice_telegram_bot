package practice_telegram_bot.enums;

import practice_telegram_bot.telegram.commands.AvailableCommands;

import java.util.List;

public enum CommandEnum {
    MATRIX(List.of("матрица")), GAME(List.of("игра")), RETURN(List.of(AvailableCommands.RETURN_COMMAND)),
    OPERATIONS(List.of("гаусс", "определитель", "сложение", "вычитание", "умножение")),;
    private final List<String> strComms;
    CommandEnum(List<String> strComms){
        this.strComms = strComms;
    }
    public List<String> getCommands(){
        return strComms;
    }
}
