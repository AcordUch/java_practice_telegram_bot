package practice_telegram_bot.database;

import practice_telegram_bot.matrix.Matrix;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MatrixBuilderData {
    @Column(name = "matrix")
    private MatrixPlain matrix;
    @Column(name = "rowToInput")
    private int rowToInput;

    public MatrixBuilderData() {
        rowToInput = 0;
    }

    public MatrixPlain getMatrix() {
        return matrix;
    }

    public void setMatrix(MatrixPlain matrix) {
        this.matrix = matrix;
    }

    public int getRowToInput() {
        return rowToInput;
    }

    public void setRowToInput(int rowToInput) {
        this.rowToInput = rowToInput;
    }
}
