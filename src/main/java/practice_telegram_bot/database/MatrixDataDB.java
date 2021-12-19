package practice_telegram_bot.database;

import practice_telegram_bot.enums.Operations;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matricesData")
public class MatrixDataDB {
    @Id
    @Column(name = "matricesData_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Operations operation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<PlainMatrix> matrices;

    @OneToOne(targetEntity = MatrixBuilderData.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MatrixBuilderData matrixBuilder;

    private int matricesNumberToEnter;

    public MatrixDataDB(){}

    public MatrixDataDB(Operations operation, int matricesNumber){
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

    public List<PlainMatrix> getMatrices() {
        return matrices;
    }

    public void setMatrices(List<PlainMatrix> matrices) {
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
