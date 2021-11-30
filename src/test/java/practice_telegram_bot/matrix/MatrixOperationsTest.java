package practice_telegram_bot.matrix;

import org.junit.jupiter.api.Test;
import practice_telegram_bot.exceptions.NotEqualSizesOfMatrixException;
import practice_telegram_bot.exceptions.NotSquareMatrixException;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOperationsTest {

    @Test
    void matrixAdditionTest() throws NotEqualSizesOfMatrixException {
        var firstMatrix = new Matrix(3, 2);
        var secondMatrix = new Matrix(3, 2);

        for (int i = 0; i < firstMatrix.getVerticalSize(); i++) {
            for (int j = 0; j < firstMatrix.getHorizontalSize(); j++) {
                firstMatrix.setElement(i + j, i, j);
                secondMatrix.setElement(j - i, i, j);
            }
        }

        var expectedMatrix = """
                0.0 2.0
                0.0 2.0
                0.0 2.0
                """;
        var resultMatrix = MatrixOperations.matrixAddition(firstMatrix, secondMatrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void matrixAdditionTestNotEqualSizes() {
        var firstMatrix = new Matrix(5, 6);
        var secondMatrix = new Matrix(3, 8);

        assertThrows(NotEqualSizesOfMatrixException.class, () ->
                MatrixOperations.matrixAddition(firstMatrix, secondMatrix));
    }

    @Test
    void matrixSubtractionTest() throws NotEqualSizesOfMatrixException {
        var firstMatrix = new Matrix(2, 3);
        var secondMatrix = new Matrix(2, 3);

        for (int i = 0; i < firstMatrix.getVerticalSize(); i++) {
            for (int j = 0; j < firstMatrix.getHorizontalSize(); j++) {
                firstMatrix.setElement((i + 1) * j, i, j);
                secondMatrix.setElement(i * (j + 2), i, j);
            }
        }

        var expectedMatrix = """
                0.0 1.0 2.0
                -2.0 -1.0 0.0
                """;
        var resultMatrix = MatrixOperations.matrixSubtraction(firstMatrix, secondMatrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void matrixMultiplyTest() throws NotEqualSizesOfMatrixException {
        var firstMatrix = new Matrix(5, 3);
        var secondMatrix = new Matrix(3, 4);

        for (int i = 0; i < firstMatrix.getVerticalSize(); i++) {
            for (int j = 0; j < firstMatrix.getHorizontalSize(); j++) {
                firstMatrix.setElement((i * 2) + (j * 3), i, j);
            }
        }

        for (int i = 0; i < secondMatrix.getVerticalSize(); i++) {
            for (int j = 0; j < secondMatrix.getHorizontalSize(); j++) {
                secondMatrix.setElement((i * 4) + (j * 6), i, j);
            }
        }

        var expectedMatrix = """
                60.0 114.0 168.0 222.0
                84.0 174.0 264.0 354.0
                108.0 234.0 360.0 486.0
                132.0 294.0 456.0 618.0
                156.0 354.0 552.0 750.0
                """;
        var resultMatrix = MatrixOperations.matrixMultiply(firstMatrix, secondMatrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void matrixMultiplyTestNotEqualSizes() {
        var firstMatrix = new Matrix(4, 3);
        var secondMatrix = new Matrix(2, 9);

        assertThrows(NotEqualSizesOfMatrixException.class, () ->
                MatrixOperations.matrixMultiply(firstMatrix, secondMatrix));
    }

    @Test
    void matrixDeterminantTest1() throws NotSquareMatrixException {
        var matrix = new Matrix(4, 4);

        var initialMatrix = """
                7.0 8.0 1.0 4.0
                7.0 5.0 1.0 2.0
                2.0 7.0 6.0 4.0
                8.0 2.0 4.0 5.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                -640.0
                """;
        var resultMatrix = MatrixOperations.countDeterminant(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void matrixDeterminantTest2() throws NotSquareMatrixException {
        var matrix = new Matrix(1, 1);

        var initialMatrix = """
                1
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                1.0
                """;
        var resultMatrix = MatrixOperations.countDeterminant(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void matrixZeroDeterminantTest() throws NotSquareMatrixException {
        var matrix = new Matrix(2, 2);

        var initialMatrix = """
                0.0 0.0
                0.0 0.0
                """;

        var parseRow = initialMatrix.split("\n");

        for (int i = 0; i < matrix.getVerticalSize(); i++)
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(Double.parseDouble(parseRow[i].split(" ")[j]), i, j);
            }

        var expectedMatrix = """
                0.0
                """;
        var resultMatrix = MatrixOperations.countDeterminant(matrix).toString();

        assertEquals(expectedMatrix, resultMatrix);
    }

    @Test
    void matrixDeterminantTestNotSquareMatrix() {
        var matrix = new Matrix(5, 4);

        assertThrows(NotSquareMatrixException.class, () ->
                MatrixOperations.countDeterminant(matrix));
    }
}
