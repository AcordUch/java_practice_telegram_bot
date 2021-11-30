package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.IncorrectNumberOfElements;
import practice_telegram_bot.exceptions.NoSolutionException;
import practice_telegram_bot.exceptions.IndexOutOfRangeException;

import java.util.List;
import java.util.Optional;

public class GaussianAlgorithmOperation {
    public static Matrix solve(Matrix initialMatrix) throws NoSolutionException, IndexOutOfRangeException {
        var matrix = new Matrix(
                initialMatrix.getVerticalSize() - 1,
                initialMatrix.getHorizontalSize() - 1
        );
        var freeMembers = new Matrix(initialMatrix.getVerticalSize(), 1);

        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            freeMembers.setElement(
                    initialMatrix.getElement(i, initialMatrix.getHorizontalSize() - 1),
                    i,
                    0
            );
            for (int j = 0; j < matrix.getHorizontalSize(); j++)
                matrix.setElement(initialMatrix.getElement(i, j), i, j);
        }

        var wasRowBeenUsed = new boolean[matrix.getVerticalSize()];

        for (int mColumn = 0; mColumn < matrix.getHorizontalSize(); mColumn++)
        {
            var activeRow = tryGetNotUsedRow(matrix, mColumn, wasRowBeenUsed).getSecond();

            if(tryGetNotUsedRow(matrix, mColumn, wasRowBeenUsed).getFirst())
                continue;

            for (int row = 0; row < matrix.getVerticalSize(); row++) {
                if (row == activeRow)
                    continue;

                var workingElement = matrix.getElement(activeRow, mColumn);
                var coefficient = matrix.getElement(row, mColumn) / workingElement * -1;

                for (int column = 0; column < matrix.getHorizontalSize(); column++) {
                    workingElement = matrix.getElement(activeRow, column);
                    matrix.setElement(column == mColumn ? 0 :
                            matrix.getElement(row, mColumn) + workingElement * coefficient, row, column);
                }
                freeMembers.setElement(
                        freeMembers.getElement(row, 0) +
                                freeMembers.getElement(activeRow, 0) * coefficient,
                        row,
                        0
                );
            }
        }

        return getValueUnknownVariables(matrix, freeMembers);
    }

    private static Matrix getValueUnknownVariables(Matrix matrix, Matrix freeMembers)
            throws IndexOutOfRangeException {
        var result = new Matrix(matrix.getHorizontalSize(), 1);
        var wasRowBeenUsed = new boolean[matrix.getVerticalSize()];
        var column = 0;
        var rowCounter = 0;

        while (rowCounter < matrix.getVerticalSize()) {
            rowCounter++;
            var row = getNotUsedRow(matrix, column, wasRowBeenUsed);

            if (column < matrix.getHorizontalSize() - 1) {
                column++; continue;
            }

            while (matrix.getElement(row, column) == 0)
                column++;

            result.setElement(
                    freeMembers.getElement(row, 0) / matrix.getElement(row, column),
                    column,
                    0
            );
            if (column < matrix.getHorizontalSize() - 1)
                column++;
        }

        return result;
    }

    private static Result tryGetNotUsedRow(Matrix matrix, int column, boolean[] wasRowBeenUsed)
    {
        int row;
        for (int mRow = 0; mRow < wasRowBeenUsed.length; mRow++) {
            if (matrix.getElement(mRow, column) != 0 && !wasRowBeenUsed[mRow]) {
                row = mRow;
                wasRowBeenUsed[mRow] = true;
                return new Result(false, row);
            }
        }

        row = -1;
        return new Result(true, row);
    }

    private static int getNotUsedRow(Matrix matrix, int column, boolean[] wasRowBeenUsed)
            throws IndexOutOfRangeException {
        var row = tryGetNotUsedRow(matrix, column, wasRowBeenUsed).getSecond();

        if (row != -1) return row;
        for (int mRow = 0; mRow < wasRowBeenUsed.length; mRow++) {
            if (!wasRowBeenUsed[mRow]) {
                wasRowBeenUsed[mRow] = true;
                return mRow;
            }
        }

        throw new IndexOutOfRangeException("Ошибка внутри метода GetNotUsedRow." +
                " Не использованная строка не найдета");
    }
}

final class Result {
    private final boolean first;
    private final int second;

    public Result(boolean first, int second) {
        this.first = first;
        this.second = second;
    }

    public boolean getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}