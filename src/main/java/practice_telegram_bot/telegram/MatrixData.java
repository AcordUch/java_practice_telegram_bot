package practice_telegram_bot.telegram;

import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.matrix.Matrix;
import practice_telegram_bot.matrix.MatrixBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatrixData {
    public final Operations operation;
    private final List<Matrix> matrices = new ArrayList<>();
    private final MatrixBuilder matrixBuilder = new MatrixBuilder();
    private int numberOfMatricesToEnter;

    public MatrixData(Operations operation, int matricesNumber){
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

    public Optional<Matrix> tryGetMatrix(int index){
        try{
            return Optional.of(matrices.get(index));
        }
        catch (Exception ex){
            return Optional.empty();
        }
    }

    public void addMatrix(Matrix matrix){
        matrices.add(matrix);
    }

    public List<Matrix> getMatricesList(){
        return matrices;
    }

    public MatrixBuilder getMatrixBuilder(){
        return matrixBuilder;
    }

    public String getLastMatrixSizeAsString(){
        return matrices.isEmpty() ?
                "" :
                String.format(
                        "%s %s",
                        matrices.get(matrices.size() - 1).getVerticalSize(),
                        matrices.get(matrices.size() - 1).getHorizontalSize()
                );
    }
}
