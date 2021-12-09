package practice_telegram_bot.database;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class MatrixPlain {
    @ElementCollection
    private List<Double> matrix;

    @Column(name = "rowLength")
    private int rowLength;

    @Column(name = "columnLength")
    private int columnLength;

    public MatrixPlain(){}

    public List<Double> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<Double>  matrix) {
        this.matrix = matrix;
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }

    public int getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(int columnLength) {
        this.columnLength = columnLength;
    }

    public double[][] getArMatrix(){
        var result = new double[rowLength][columnLength];
        for(var row = 0; row < rowLength; row++){
            for(var column = 0; column < columnLength; column++ ){
                result[row][column] = matrix.get(column + row * columnLength);
            }
        }
        return result;
    }

    public void setArMatrix(double[][] arMatrix){
        matrix = new ArrayList<>(arMatrix.length * arMatrix[0].length);
        rowLength = arMatrix.length;
        columnLength = arMatrix[0].length;
        for(var row = 0; row < rowLength; row++){
            for(var column = 0; column < columnLength; column++ ){
                matrix.add(arMatrix[row][column]);
            }
        }
    }
}
