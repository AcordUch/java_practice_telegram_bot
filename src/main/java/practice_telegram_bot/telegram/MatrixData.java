package practice_telegram_bot.telegram;

import practice_telegram_bot.matrix.Matrix;

import java.util.List;

public class MatrixData {
    public final String operation;
    private List<Matrix> matrices;
    private int numberOfMatricesToEnter;

    public MatrixData(String operation, int matricesNumber){
        this.operation = operation;
        numberOfMatricesToEnter = matricesNumber;
    }

    public int matrixCount(){
        return matrices.size();
    }

    public int numberOfMatricesToEnter(){
        return numberOfMatricesToEnter;
    }

    public void reduceNumberOfMatricesToEnter(){
        numberOfMatricesToEnter--;
    }

    public Matrix tryGetMatrix(int index){
        try{
            return matrices.get(index);
        }
        catch (Exception ex){
            return null;
        }
    }

    public void addMatrix(Matrix matrix){
        matrices.add(matrix);
    }
}
