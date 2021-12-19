package practice_telegram_bot.telegram;

import com.google.common.collect.ImmutableCollection;
import org.hibernate.collection.internal.PersistentList;
import practice_telegram_bot.database.MatrixBuilderData;
import practice_telegram_bot.database.MatrixDataDB;
import practice_telegram_bot.database.PlainMatrix;
import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.matrix.Matrix;
import practice_telegram_bot.matrix.MatrixBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatrixData {
    public final Operations operation;
    private List<Matrix> matrices;
    private MatrixBuilder matrixBuilder;
    private int numberOfMatricesToEnter;

    public MatrixData(Operations operation, int matricesNumber){
        this.operation = operation;
        numberOfMatricesToEnter = matricesNumber;
        matrices = new ArrayList<>();
        matrixBuilder = new MatrixBuilder();
    }

    public static MatrixData restoreFromDB(MatrixDataDB matrixDataDB){
        return new MatrixData(
                matrixDataDB.getOperation(),
                matrixDataDB.getMatricesNumberToEnter()
        ).setUp(matrixDataDB);
    }

    public MatrixData setUp(MatrixDataDB matrixDataDB){
        matrixBuilder = MatrixBuilder.restoreFromDB(matrixDataDB.getMatrixBuilder());
        var test = matrixDataDB.getMatrices();
        matrices = matrixDataDB.getMatrices().stream()
                .map(x -> new Matrix(x.getArMatrix()))
                .collect(Collectors.toList());
        return this;
    }

    public MatrixDataDB packForDB(){
        var matrixDataDB = new MatrixDataDB(operation, numberOfMatricesToEnter);
        var plainMatrices = matrices.stream()
                .map(Matrix::packForDB)
                .toList();
        matrixDataDB.setMatrixBuilder(matrixBuilder.packForDB());
        matrixDataDB.setMatrices(plainMatrices);
        return matrixDataDB;
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

    public MatrixBuilder getMatrixBuilder(){
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
