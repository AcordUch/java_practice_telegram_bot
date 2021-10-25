package practice_telegram_bot.matrix;

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
}
