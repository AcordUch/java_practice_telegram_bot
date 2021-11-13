package practice_telegram_bot.enums;

import practice_telegram_bot.telegram.commands.AvailableCommands;

import java.util.ArrayList;
import java.util.List;

public enum CommandEnum {
    MATRIX(List.of("матрица")), GAME(List.of("игра")), RETURN(List.of(AvailableCommands.RETURN_COMMAND)),
    ARBITRARY(new ArrayList<>()), TEXT_SEND(new ArrayList<>()),
    MATRIX_OPERATIONS(List.of("гаусс", "определитель", "сложение", "вычитание", "умножение")),
    MATRIX_SIZE(new ArrayList<>()), MATRIX_ROW(new ArrayList<>()), MATRIX_RESULT(List.of("результат"));
    private final List<String> strComms;
    CommandEnum(List<String> strComms){
        this.strComms = strComms;
    }
    public List<String> getCommands(){
        return strComms;
    }
}
