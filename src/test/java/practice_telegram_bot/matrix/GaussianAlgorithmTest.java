package practice_telegram_bot.matrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practice_telegram_bot.exceptions.IndexOutOfRangeException;
import practice_telegram_bot.exceptions.NoSolutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GaussianAlgorithmTest {
//    private GaussianAlgorithmOperation solver;
//
//    @BeforeEach
//    void setUp() {
//        solver = new GaussianAlgorithmOperation();
//    }

    @Test
    void GaussianAlgorithm2x2() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(2, 2);

        var initialMatrix = """
                1.0 1.0
                1.0 1.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                1.0
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm2x2_2() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(2, 2);

        var initialMatrix = """
                1.0 2.0
                1.0 2.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                2.0
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm2x2_3() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(2, 2);

        var initialMatrix = """
                2.0 1.0
                2.0 1.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.5
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm2x3() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(2, 3);

        var initialMatrix = """
                2.0 2.0 16.0
                1.0 1.0 8.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.0
                0.0
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm3x3() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(3, 3);

        var initialMatrix = """
                2.0 1.0 5.0
                -2.0 1.0 1.0
                1.0 6.0 12.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.0
                6.0
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm3x3_2() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(3, 3);

        var initialMatrix = """
                1.0 2.0 4.0
                5.0 1.0 2.0
                3.0 -1.0 1.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.0
                1.8
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm3x4_2() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(3, 4);

        var initialMatrix = """
                3.0 2.0 -5.0 -1.0
                2.0 -1.0 3.0 13.0
                1.0 2.0 -1.0 9.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.0
                0.0
                0.0
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm3x4_3() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(3, 4);

        var initialMatrix = """
                1.0 1.0 1.0 0.0
                4.0 2.0 1.0 1.0
                9.0 3.0 1.0 3.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.0
                0.0
                0.0
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm4x5() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(4, 5);

        var initialMatrix = """
                4.0 3.0 8.0 12.0 36.0
                -2.0 -4.0 1.0 4.0 10.0
                12.0 -14.0 16.0 8.0 -6.0
                4.0 -6.0 3.0 5.0 8.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.0
                0.0
                0.0
                0.0
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void GaussianAlgorithm5x5() throws IndexOutOfRangeException, NoSolutionException {
        var matrix = new Matrix(5, 5);

        var initialMatrix = """
                2.0 1.0 5.0 1.0 12.0
                -2.0 1.0 -1.0 12.0 -23.0
                1.0 6.0 12.0 23.0 56.0
                2.0 -3.0 -4.0 4.0 14.0
                -4.0 -2.0 6.0 -12.0 20.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.0
                0.0
                0.0
                -98.0
                """;
        var resultMatrix = GaussianAlgorithmOperation.solve(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }
}