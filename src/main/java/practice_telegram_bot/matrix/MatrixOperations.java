package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.NotEqualSizesOfMatrixException;

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
        if (firstMatrix.getHorizontalSize() != secondMatrix.getVerticalSize()){
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
}
