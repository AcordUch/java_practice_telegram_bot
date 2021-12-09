package practice_telegram_bot.database;

import practice_telegram_bot.enums.Operations;
import practice_telegram_bot.matrix.Matrix;
import practice_telegram_bot.matrix.MatrixBuilderLegacy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matricesData")
public class MatrixData {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Operations operation;

    @ElementCollection
    private List<MatrixPlain> matrices;

    @Embedded
    private MatrixBuilderData matrixBuilder;

    private int matricesNumberToEnter;

    public MatrixData(){}

    public MatrixData(Operations operation, int matricesNumber){
        this.operation = operation;
        this.matricesNumberToEnter = matricesNumber;
        matrices = new ArrayList<>();
        matrixBuilder  = new MatrixBuilderData();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }

    public List<MatrixPlain> getMatrices() {
        return matrices;
    }

    public void setMatrices(List<MatrixPlain> matrices) {
        this.matrices = matrices;
    }

    public MatrixBuilderData getMatrixBuilder() {
        return matrixBuilder;
    }

    public void setMatrixBuilder(MatrixBuilderData matrixBuilder) {
        this.matrixBuilder = matrixBuilder;
    }

    public int getMatricesNumberToEnter() {
        return matricesNumberToEnter;
    }

    public void setMatricesNumberToEnter(int matricesNumberToEnter) {
        this.matricesNumberToEnter = matricesNumberToEnter;
    }
}
