package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.IncorrectNumberOfElements;

import java.util.List;
import java.util.Optional;

public interface Operation {
    Optional<Matrix> apply(List<Matrix> matrices) throws IncorrectNumberOfElements;
}
