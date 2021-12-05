package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.IncorrectNumberOfElements;

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

    public Matrix(double[] ... matrix){
        this.matrix = matrix;
    }

    public int getVerticalSize(){
        return matrix.length;
    }

    public int getHorizontalSize(){
        return matrix[0].length;
    }

    public int getHorizontalSize(int row){
        return matrix[row].length;
    }

    public double getElement(int row, int column){
        return matrix[row][column];
    }

    public void setElement(double value, int row, int column){
        matrix[row][column] = value;
    }

    public void setRow(double[] newRowValue, int row) throws IncorrectNumberOfElements {
        if(newRowValue.length != getHorizontalSize()){
            throw new IncorrectNumberOfElements();
        }
        matrix[row] = newRowValue;
    }

    public void setRow(double[] newRowValue, int row, boolean ignoreRowSize) {
        if(ignoreRowSize){
            matrix[row] = newRowValue;
        } else {
            try {
                setRow(newRowValue, row);
            } catch (IncorrectNumberOfElements e) {
                e.printStackTrace();
            }
        }
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

    public String sizeToString(){
        return String.format("%d %d", getVerticalSize(), getHorizontalSize());
    }
}
