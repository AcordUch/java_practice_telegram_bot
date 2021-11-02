package practice_telegram_bot.telegram;

import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.matrix.Matrix;
import practice_telegram_bot.matrix.MatrixBuilder;

import java.util.List;

public class MatrixData {
    public final Operations operation;
    private List<Matrix> matrices;
//    private MatrixBuilder matrixBuilder;
    private int numberOfMatricesToEnter;

    public MatrixData(Operations operation, int matricesNumber){
        this.operation = operation;
        numberOfMatricesToEnter = matricesNumber;
//        matrixBuilder = new MatrixBuilder();
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
