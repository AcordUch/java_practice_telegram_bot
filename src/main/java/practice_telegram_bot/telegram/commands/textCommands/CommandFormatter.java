package practice_telegram_bot.telegram.commands.textCommands;

import practice_telegram_bot.GlobalConst;
import practice_telegram_bot.enums.CommandEnum;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.exceptions.TooLongSentenceExceptions;
import practice_telegram_bot.telegram.commands.AvailableCommands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class CommandFormatter {
    private static final int MAX_COMMAND_LENGTH = 20;
    private static final Map<StateEnum, Optional<CommandEnum>> STATE_WITH_ARBITRARY_INPUT = Map.ofEntries(
            entry(StateEnum.MATRIX_SIZE_INPUT, Optional.of(CommandEnum.MATRIX_SIZE)),
            entry(StateEnum.MATRIX_INPUT, Optional.of(CommandEnum.MATRIX_ROW))
    );
    private static final Map<CommandEnum, List<String>> COMMAND_MAP = Map.ofEntries(
            entry(CommandEnum.MATRIX, Arrays.asList("матрица", "матрицы", "матрицу")),
            entry(CommandEnum.GAME, Arrays.asList("игра", "сыграем", "играем", "быки и коровы")),
            entry(CommandEnum.RETURN, Arrays.asList("меню", "начало", "назад")),
            entry(CommandEnum.MATRIX_OPERATIONS, Arrays.asList("гаусс", "определитель", "сложение", "вычитание", "умножение")),
            entry(CommandEnum.MATRIX_RESULT, List.of("результат"))
    );

    public static Optional<CommandEnum> formatCommand(String command, StateEnum state) throws TooLongSentenceExceptions {
        var arbitraryInputCommand = STATE_WITH_ARBITRARY_INPUT
                                                            .getOrDefault(state, Optional.empty());
        if(arbitraryInputCommand.isPresent()){
            if(COMMAND_MAP.get(CommandEnum.RETURN).contains(command)){ //command.equals(GlobalConst.RETURN_COMMAND)
                return Optional.of(CommandEnum.RETURN);
            }
            return arbitraryInputCommand;
        }

        var tokens = command.split(" ");
        if(tokens[0].equals(TextSendCommand.TEXTCOMMPREFIX)){
            return Optional.of(CommandEnum.TEXT_SEND);
        }
        if(tokens.length > MAX_COMMAND_LENGTH){
            throw new TooLongSentenceExceptions();
        }
        for (var token : tokens){
            for(var kvPair : COMMAND_MAP.entrySet()){
                if(kvPair.getValue().contains(token) &&
                        AvailableCommands.checkingForAvailability(state, kvPair.getKey())){
                    return Optional.of(kvPair.getKey());
                }
            }
        }
        return Optional.empty();
    }
}
