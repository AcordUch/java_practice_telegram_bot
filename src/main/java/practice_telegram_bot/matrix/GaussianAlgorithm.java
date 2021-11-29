package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.NoSolutionException;

public class GaussianAlgorithm {
    public Matrix solve(Matrix initialMatrix) throws NoSolutionException {
        var matrix = new Matrix(initialMatrix.getVerticalSize() - 1,
                initialMatrix.getHorizontalSize() - 1);
        var freeMembers = new Matrix(initialMatrix.getVerticalSize(), 1);
        var rowCount = matrix.getVerticalSize();
        var columCount = matrix.getHorizontalSize();
        var answer = new Matrix(columCount, 1);

        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            freeMembers.setElement(
                    initialMatrix.getElement(i, initialMatrix.getHorizontalSize() - 1), i, 0);
            for (int j = 0; j < matrix.getHorizontalSize(); j++)
                matrix.setElement(initialMatrix.getElement(i, j), i, j);
        }

        doGaussMethod(matrix, freeMembers, rowCount, columCount);
        countingValues(matrix, freeMembers, rowCount, columCount, answer);

        return answer;
    }

    private static void doGaussMethod
            (Matrix matrix, Matrix freeMembers, int rowCount, int columCount)
    {
        for (var i = 0; i < rowCount; i++)
        {
            if (i < matrix.getVerticalSize() && i < matrix.getHorizontalSize() && i >= 1)
            {
                for (var j = i - 1; j >= 0; j--)
                {
                    cycle(matrix, freeMembers, columCount, i, j);
                }
            }

            if (i < matrix.getVerticalSize() && i < matrix.getHorizontalSize() && i < rowCount - 1)
            {
                for (var j = i + 1; j < rowCount; j++)
                {
                    cycle(matrix, freeMembers, columCount, i, j);
                }
            }
        }
    }

    private static void cycle(Matrix matrix, Matrix freeMembers, int columCount, int i, int j) {
        if (matrix.getElement(i, i) != 0.0)
        {
            double element = matrix.getElement(j, i) / matrix.getElement(i, i);
            for (var k = i; k < columCount; k++)
                matrix.setElement(matrix.getElement(j, k) - matrix.getElement(i, k) * element, j, k);
            freeMembers.setElement(
                    freeMembers.getElement(j, 0) -
                            freeMembers.getElement(i, 0) * element, j, 0);
        }
    }

    private static void countingValues
            (Matrix matrix, Matrix freeMembers, int rowCount, int columCount, Matrix answer)
            throws NoSolutionException {
        for (var i = 0; i < rowCount; i++)
        {
            if (i <= matrix.getVerticalSize() && i <= matrix.getHorizontalSize())
            {
                var count = 0;

                for (var j = 0; j < columCount; j++)
                    if (matrix.getElement(i, j) == 0)
                        count++;

                if (count == matrix.getHorizontalSize() && freeMembers.getElement(i, 0) == 0)
                    continue;

                if (count == matrix.getHorizontalSize() && freeMembers.getElement(i, 0) != 0)
                    throw new NoSolutionException("No solution exception");

                answer.setElement(freeMembers.getElement(i, 0) / matrix.getElement(i, i), i, 0);

                if (Double.isNaN(answer.getElement(i ,0)) || Double.isInfinite(answer.getElement(i , 0)))
                    answer.setElement(freeMembers.getElement(i , 0), i, 0);

                count = 0;
            }
        }
    }
}
