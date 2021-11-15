package practice_telegram_bot.enums;

import java.util.Map;

import static java.util.Map.entry;

public enum Operations {
    ADDITION(2, true), SUBTRACTIONS(2, true),
    MULTIPLICATION(2, false),
    DETERMINANT(1, false), GAUSS_SOLUTION(2, false);
    private static final Map<String, Operations> INTERPRETATION_MAP = Map.ofEntries(
            entry("сложение", ADDITION),
            entry("вычитание", SUBTRACTIONS),
            entry("умножение", MULTIPLICATION),
            entry("определитель", DETERMINANT),
            entry("гаусс", GAUSS_SOLUTION)
    );

    public final int numOfSizeArguments;
    public final boolean sameMatrixSize;

    Operations(int numSizeArg, boolean sameMatrixSize){
        numOfSizeArguments = numSizeArg;
        this.sameMatrixSize = sameMatrixSize;
    }

    public static Operations fromString(String str){
        return INTERPRETATION_MAP.get(str);
    }
}
