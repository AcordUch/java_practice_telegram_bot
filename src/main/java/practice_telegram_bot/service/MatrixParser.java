package practice_telegram_bot.service;

import practice_telegram_bot.database.MatrixDb;
import practice_telegram_bot.matrix.Matrix;

import java.util.Arrays;

public class MatrixParser {
    public static Matrix parse(MatrixDb rawMatrix){
        var rows = rawMatrix.getMatrix().split("\n");
        var size = rawMatrix.getMatrixSize().split(" ");
        var matrix = new double[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
        for(var i = 0; i < rows.length; i++){
            matrix[i] = Arrays.stream(rows[i].split(" "))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
        }
        return new Matrix(matrix);
    }
}
