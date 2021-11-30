package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.exceptions.NotSquareMatrixException;

import java.util.List;
import java.util.Optional;

public class Determinant implements Operation {
    @Override
    public Optional<Matrix> apply(List<Matrix> matrices) throws IncorrectNumberOfElements {
        if (matrices.size() == 0) {
            throw new IncorrectNumberOfElements("По какой-то причине было передано 0 матриц");
        }
        try {
            return Optional.of(MatrixOperations.countDeterminant(matrices.get(0)));
        } catch (NotSquareMatrixException e) {
            return Optional.empty();
        }
    }
}
