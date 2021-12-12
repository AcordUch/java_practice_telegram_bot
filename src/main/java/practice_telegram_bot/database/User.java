package practice_telegram_bot.database;

import org.hibernate.annotations.SelectBeforeUpdate;
import practice_telegram_bot.enums.StateEnum;

import javax.persistence.*;

@Entity
@Table(name = "usersData")
@SelectBeforeUpdate
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @OneToOne(targetEntity = MatrixDataDB.class, cascade = CascadeType.ALL)
    private MatrixDataDB matrixData;

    @Column
    private Long prev_id;

    public Long getPrev_id() {
        return prev_id;
    }

    public void setPrev_id(Long prev_id) {
        this.prev_id = prev_id;
    }

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

    public MatrixDataDB getMatrixData() {
        return matrixData;
    }

    public void setMatrixData(MatrixDataDB matrixData) {
        this.matrixData = matrixData;
    }
}
