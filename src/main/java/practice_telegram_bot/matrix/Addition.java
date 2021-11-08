package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.exceptions.NotEqualSizesOfMatrixException;

import java.util.List;
import java.util.Optional;

public class Addition implements Operation{
    @Override
    public Optional<Matrix> apply(List<Matrix> matrices) throws IncorrectNumberOfElements {
        switch (matrices.size()){
            case 1:
                return Optional.of(matrices.get(0));
            case 0:
                throw new IncorrectNumberOfElements("По какой-то причине было передано 0 матриц"); 
        }
        var accumulator = Optional.of(matrices.get(0));
        for(int i = 1; i < matrices.size(); i++){
            try {
                accumulator = Optional.of(MatrixOperations.matrixAddition(accumulator.get(), matrices.get(i)));
            } catch (NotEqualSizesOfMatrixException e) {
                return Optional.empty();
            }
        }
        return accumulator;
    }
}
