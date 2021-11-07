package practice_telegram_bot.matrix;

import practice_telegram_bot.exceptions.IncorrectNumberOfElements;

public class MatrixBuilder {
    private Matrix matrix;
    private int rowToInput = 0;

    public MatrixBuilder createNewMatrix(String[] input) throws IncorrectNumberOfElements, NumberFormatException {
        return switch (input.length) {
            case (1) -> createNewMatrix(Integer.parseInt(input[0]));
            case (2) -> createNewMatrix(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            default -> throw new IncorrectNumberOfElements();
        };
    }

    public MatrixBuilder createNewMatrix(int size){
        return createNewMatrix(size, size);
    }

    public MatrixBuilder createNewMatrix(int row, int column){
        matrix = new Matrix(row, column);
        rowToInput = 0;
        return this;
    }

    public int getMatrixHorizontalSize(){
        return matrix.getHorizontalSize();
    }

    public MatrixBuilder addRow(String strRow) throws IncorrectNumberOfElements {
        addRow(rowToInput, strRow);
        rowToInput++;
        return this;
    }

    public MatrixBuilder addRow(int row, String strRow) throws IncorrectNumberOfElements {
        var elements = strRow.split(" ");
        if (elements.length != matrix.getHorizontalSize()){
            throw new IncorrectNumberOfElements();
        }
        for(var column = 0; column < elements.length; column++){
            matrix.setElement(Double.parseDouble(elements[column]), row, column);
        }
        return this;
    }

    public boolean checkForFilling(){
        return rowToInput >= matrix.getVerticalSize();
    }

    public Matrix buildMatrix(){
        return matrix;
    }
}
