package practice_telegram_bot.database;

import practice_telegram_bot.enums.StateEnum;

import javax.persistence.*;

@Entity
@Table(name = "usersData")
public class User {
    @Id
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @OneToOne(targetEntity = MatrixData.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatrixData matrixData;

    public User(){}

    public User(Long id){
        this.id = id;
        state = StateEnum.START;
        matrixData = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public MatrixData getMatrixData() {
        return matrixData;
    }

    public void setMatrixData(MatrixData matrixData) {
        this.matrixData = matrixData;
    }
}
