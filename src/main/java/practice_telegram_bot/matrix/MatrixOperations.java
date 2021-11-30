package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.NotEqualSizesOfMatrixException;
import practice_telegram_bot.exceptions.NotSquareMatrixException;

import java.text.ParseException;

public class MatrixOperations {
    public static Matrix matrixAddition(Matrix firstMatrix, Matrix secondMatrix)
            throws NotEqualSizesOfMatrixException {
        if (firstMatrix.getHorizontalSize() != secondMatrix.getHorizontalSize() ||
                firstMatrix.getVerticalSize() != secondMatrix.getVerticalSize()) {
            throw new NotEqualSizesOfMatrixException();
        } else {
            Matrix resultMatrix = new Matrix(firstMatrix.getVerticalSize(), secondMatrix.getHorizontalSize());

            for (int i = 0; i < resultMatrix.getVerticalSize(); i++) {
                for (int j = 0; j < resultMatrix.getHorizontalSize(); j++) {
                    resultMatrix.setElement(firstMatrix.getElement(i, j) + secondMatrix.getElement(i, j), i, j);
                }
            }

            return resultMatrix;
        }
    }

    public static Matrix matrixSubtraction(Matrix firstMatrix, Matrix secondMatrix)
            throws NotEqualSizesOfMatrixException {
        if (firstMatrix.getHorizontalSize() != secondMatrix.getHorizontalSize() ||
                firstMatrix.getVerticalSize() != secondMatrix.getVerticalSize()) {
            throw new NotEqualSizesOfMatrixException();
        } else {
            Matrix resultMatrix = new Matrix(firstMatrix.getVerticalSize(), secondMatrix.getHorizontalSize());

            for (int i = 0; i < resultMatrix.getVerticalSize(); i++) {
                for (int j = 0; j < resultMatrix.getHorizontalSize(); j++) {
                    resultMatrix.setElement(firstMatrix.getElement(i, j) - secondMatrix.getElement(i, j), i, j);
                }
            }

            return resultMatrix;
        }
    }

    public static Matrix matrixMultiply(Matrix firstMatrix, Matrix secondMatrix)
            throws NotEqualSizesOfMatrixException {
        if (firstMatrix.getHorizontalSize() != secondMatrix.getVerticalSize()) {
            throw new NotEqualSizesOfMatrixException();
        } else {
            Matrix resultMatrix = new Matrix(firstMatrix.getVerticalSize(), secondMatrix.getHorizontalSize());
            double itemCount = 0.0;

            for (int i = 0; i < resultMatrix.getVerticalSize(); i++) {
                for (int j = 0; j < resultMatrix.getHorizontalSize(); j++) {
                    for (int k = 0; k < secondMatrix.getVerticalSize(); k++) {
                        itemCount += firstMatrix.getElement(i, k) * secondMatrix.getElement(k, j);
                    }
                    resultMatrix.setElement(itemCount, i, j);
                    itemCount = 0.0;
                }
            }

            return resultMatrix;
        }
    }

    public static Matrix countDeterminant(Matrix matrix)
            throws NotSquareMatrixException {
        if (matrix.getHorizontalSize() != matrix.getVerticalSize()) {
            throw new NotSquareMatrixException();
        } else {
            var matrixL = new Matrix(matrix.getVerticalSize(), matrix.getHorizontalSize());
            var matrixU = new Matrix(matrix.getVerticalSize(), matrix.getHorizontalSize());
            var tempParam = 0.0;

            for (int i = 0; i < matrix.getVerticalSize(); i++)
                for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                    matrixL.setElement(0, i, j);
                    matrixU.setElement(0, i, j);
                    matrixL.setElement(1, i, i);
                }

            for (int i = 0; i < matrix.getVerticalSize(); i++)
                for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                    if (i <= j) {
                        for (int k = 0; k < i; k++)
                            tempParam += matrixL.getElement(i, k) * matrixU.getElement(k, j);
                        matrixU.setElement(matrix.getElement(i, j) - tempParam, i, j);
                    } else {
                        for (int k = 0; k < j; k++)
                            tempParam += matrixL.getElement(i, k) * matrixU.getElement(k, j);
                        matrixL.setElement((matrix.getElement(i, j) - tempParam) / matrixU.getElement(j, j), i, j);
                    }
                    tempParam = 0.0;
                }

            double determinantL = 1.0;
            double determinantU = 1.0;

            for (int i = 0; i < matrix.getVerticalSize(); i++) {
                determinantL *= matrixL.getElement(i, i);
                determinantU *= matrixU.getElement(i, i);
            }

            var result = new Matrix(1);
            result.setElement(Math.round(determinantL * determinantU * 10.0) / 10.0, 0, 0);
            return result;
        }
    }
}
