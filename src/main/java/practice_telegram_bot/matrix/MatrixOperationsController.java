package practice_telegram_bot.matrix;

import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.telegram.MatrixData;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class MatrixOperationsController {
    private static final Map<Operations, Operation> OPERATIONS_MAP = Map.ofEntries(
            entry(Operations.ADDITION, new Addition()),
            entry(Operations.SUBTRACTIONS, new Subtraction()),
            entry(Operations.MULTIPLICATION, new Multiplication())
    );
    public static Optional<Matrix> makeOperation(MatrixData matrixData) throws IncorrectNumberOfElements {
        if(!OPERATIONS_MAP.containsKey(matrixData.operation)){
            return Optional.empty();
        }
        return OPERATIONS_MAP.get(matrixData.operation).apply(matrixData.getMatricesList());
    }
}
