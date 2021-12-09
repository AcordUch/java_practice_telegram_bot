package practice_telegram_bot.database;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MatrixPlain {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<Double> matrix;

    @Column(name = "rowLength")
    private int rowLength;

    @Column(name = "columnLength")
    private int columnLength;

    public MatrixPlain(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
