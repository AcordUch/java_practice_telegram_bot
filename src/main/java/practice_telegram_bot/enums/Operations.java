package practice_telegram_bot.enums;

import java.util.Map;

import static java.util.Map.entry;

public enum Operations {
    ADDITION, SUBTRACTIONS, MULTIPLICATION,
    DETERMINANT, GAUSS_SOLUTION;
    private static final Map<String, Operations> INTERPRETATION_MAP = Map.ofEntries(
            entry("сложение", ADDITION),
            entry("вычитание", SUBTRACTIONS),
            entry("умножение", MULTIPLICATION),
            entry("определитель", DETERMINANT),
            entry("гаусс", GAUSS_SOLUTION)
    );
    public static Operations fromString(String str){
        return INTERPRETATION_MAP.get(str);
    }
}
