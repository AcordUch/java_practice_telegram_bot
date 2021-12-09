package practice_telegram_bot.database;

import practice_telegram_bot.matrix.Matrix;

import javax.persistence.*;

@Entity
public class MatrixBuilderData {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(targetEntity = MatrixPlain.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MatrixPlain matrix;
    @Column(name = "rowToInput")
    private int rowToInput;

    public MatrixBuilderData() {
        rowToInput = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
