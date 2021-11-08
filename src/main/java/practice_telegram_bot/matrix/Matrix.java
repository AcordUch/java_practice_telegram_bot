package practice_telegram_bot.matrix;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Matrix {
    private final double[][] matrix;

    public Matrix(int size){
        this(size, size);
    }
    public Matrix(int rows, int columns){
        matrix = new double[rows][columns];
    }

    public int getVerticalSize(){
        return matrix.length;
    }

    public int getHorizontalSize(){
        return matrix[0].length;
    }

    public double getElement(int row, int column){
        return matrix[row][column];
    }

    public void setElement(double value, int row, int column){
        matrix[row][column] = value;
    }

    @Override
    public String toString() {
        var strBuilder = new StringBuilder();
        for (double[] row : matrix) {
            strBuilder.append(
                    Arrays.stream(row)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(" "))
            );
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }
}
