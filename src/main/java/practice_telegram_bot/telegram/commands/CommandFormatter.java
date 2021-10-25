package practice_telegram_bot.telegram.commands;

import practice_telegram_bot.exceptions.TooLongSentenceExceptions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class CommandFormatter {
    private static final int MAX_COMMAND_LENGTH = 20;
    private static final Map<String, List<String>> COMMAND_MAP = Map.ofEntries(
            entry("матрица", Arrays.asList("матрица", "матрицы", "матрицу"))
    );

    public static String formatCommand(String command) throws TooLongSentenceExceptions {
        var tokens = command.split(" ");
        if(tokens.length > MAX_COMMAND_LENGTH){
            throw new TooLongSentenceExceptions();
        }
        for (var token : tokens){
            for(var kvPair : COMMAND_MAP.entrySet()){
                if(kvPair.getValue().contains(token)){
                    return kvPair.getKey();
                }
            }
        }
        return "";
    }
}
