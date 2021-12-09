package practice_telegram_bot.database;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MatrixDb {
    @Column(name = "matrix")
    private String matrix;

    @Column(name = "size")
    private String matrixSize;

    public MatrixDb(){}

    public MatrixDb(String matrix, String matrixSize) {
        this.matrix = matrix;
        this.matrixSize = matrixSize;
    }

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    public String getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(String matrixSize) {
        this.matrixSize = matrixSize;
    }
}
