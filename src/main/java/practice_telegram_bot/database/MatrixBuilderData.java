package practice_telegram_bot.database;

import javax.persistence.*;

@Entity
public class MatrixBuilderData {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = PlainMatrix.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PlainMatrix matrix;

    @Column(name = "rowToInput")
    private int rowToInput;

    public MatrixBuilderData() {
        rowToInput = 0;
    }

    public MatrixBuilderData(PlainMatrix matrix, int rowToInput){
        this.matrix = matrix;
        this.rowToInput = rowToInput;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlainMatrix getMatrix() {
        return matrix;
    }

    public void setMatrix(PlainMatrix matrix) {
        this.matrix = matrix;
    }

    public int getRowToInput() {
        return rowToInput;
    }

    public void setRowToInput(int rowToInput) {
        this.rowToInput = rowToInput;
    }
}
