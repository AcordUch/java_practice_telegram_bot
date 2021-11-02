package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.enums.CommandEnum;
import practice_telegram_bot.exceptions.TooLongSentenceExceptions;
import practice_telegram_bot.telegram.commands.AvailableCommands;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class CommandFormatter {
    private static final int MAX_COMMAND_LENGTH = 20;
    private static final Map<CommandEnum, List<String>> COMMAND_MAP = Map.ofEntries(
            entry(CommandEnum.MATRIX, Arrays.asList("матрица", "матрицы", "матрицу")),
            entry(CommandEnum.GAME, Arrays.asList("игра", "сыграем", "играем", "быки и коровы")),
            entry(CommandEnum.RETURN, Arrays.asList("меню", "начало")),
            entry(CommandEnum.OPERATIONS, Arrays.asList("гаусс", "определитель", "сложение", "вычитание", "умножение"))
    );

    public static Optional<CommandEnum> formatCommand(String command) throws TooLongSentenceExceptions {
        var tokens = command.split(" ");
        if(tokens.length > MAX_COMMAND_LENGTH){
            throw new TooLongSentenceExceptions();
        }
        for (var token : tokens){
            for(var kvPair : COMMAND_MAP.entrySet()){
                if(kvPair.getValue().contains(token)){
                    return Optional.of(kvPair.getKey());
                }
            }
        }
        return Optional.empty();
    }
}
