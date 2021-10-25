package practice_telegram_bot.states;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

import static java.util.Map.entry;
import static practice_telegram_bot.states.StateEnum.*;

public class AvailableCommands {
    public static final String RETURN_COMMAND = "меню";
    private static final Map<StateEnum, List<String>> availableCommands = Map.ofEntries(
            entry(START, Arrays.asList("игра", "матрица")),
            entry(MATRIX_START,
                    Arrays.asList(RETURN_COMMAND, "решить", "определитель", "сложение", "вычитание", "умножение")),
            entry(GAME_START, Arrays.asList(RETURN_COMMAND))
    );

    public static List<String> getAvailableCommands(StateEnum state){
        return availableCommands.get(state);
    }

    public static boolean checkingForAvailability(StateEnum state, String command){
        return getAvailableCommands(state).contains(command);
    }

    public static String getAvailableCommandsAsString(StateEnum state){
        return String.join("\n", getAvailableCommands(state));
    }
}
