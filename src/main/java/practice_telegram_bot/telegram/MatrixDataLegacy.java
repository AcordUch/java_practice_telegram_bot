package practice_telegram_bot.telegram;

import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.matrix.Matrix;
import practice_telegram_bot.matrix.MatrixBuilderLegacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatrixDataLegacy {
    public final Operations operation;
    private final List<Matrix> matrices = new ArrayList<>();
    private final MatrixBuilderLegacy matrixBuilder = new MatrixBuilderLegacy();
    private int numberOfMatricesToEnter;

    public MatrixDataLegacy(Operations operation, int matricesNumber){
        this.operation = operation;
        numberOfMatricesToEnter = matricesNumber;
    }

    public int matrixCount(){
        return matrices.size();
    }

    public boolean matrixInputCompleted(){
        return numberOfMatricesToEnter < 1;
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
        numberOfMatricesToEnter--;
    }

    /**
     * Берет матрицу из MatrixBuilder и добавляет её в список матриц
     */
    public void buildMatrix(){
        addMatrix(matrixBuilder.buildMatrix());
    }

    public List<Matrix> getMatricesList(){
        return matrices;
    }

    public MatrixBuilderLegacy getMatrixBuilder(){
        return matrixBuilder;
    }

    public String lastMatrixSizeToString(){
        return matrices.isEmpty() ?
                "" :
                String.format(
                        "%s %s",
                        matrices.get(matrices.size() - 1).getVerticalSize(),
                        matrices.get(matrices.size() - 1).getHorizontalSize()
                );
    }
}
