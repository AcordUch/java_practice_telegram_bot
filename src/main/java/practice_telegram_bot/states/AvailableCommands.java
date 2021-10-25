package practice_telegram_bot.states;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

import static java.util.Map.entry;
import static practice_telegram_bot.states.StateEnum.*;

public class AvailableCommands {
    private static final String BACK_COMMAND = "назад";
    private static final Map<StateEnum, List<String>> availableCommands = Map.ofEntries(
            entry(START, Arrays.asList("игра", "быки и коровы", "матрицы", "матрица")),
            entry(MATRIX_START, Arrays.asList(BACK_COMMAND, "решить", "определитель", "сложение", "вычитание", "умножение"))
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
