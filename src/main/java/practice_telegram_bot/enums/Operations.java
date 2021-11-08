package practice_telegram_bot.enums;

import java.util.Map;

import static java.util.Map.entry;

public enum Operations {
    ADDITION(2), SUBTRACTIONS(2), MULTIPLICATION(2),
    DETERMINANT(1), GAUSS_SOLUTION(2);
    private static final Map<String, Operations> INTERPRETATION_MAP = Map.ofEntries(
            entry("сложение", ADDITION),
            entry("вычитание", SUBTRACTIONS),
            entry("умножение", MULTIPLICATION),
            entry("определитель", DETERMINANT),
            entry("гаусс", GAUSS_SOLUTION)
    );

    public final int numOfArguments;

    Operations(int numArg){
        numOfArguments = numArg;
    }

    public static Operations fromString(String str){
        return INTERPRETATION_MAP.get(str);
    }
}
