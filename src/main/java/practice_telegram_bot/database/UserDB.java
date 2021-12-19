package practice_telegram_bot.database;

import org.hibernate.annotations.SelectBeforeUpdate;
import practice_telegram_bot.enums.StateEnum;

import javax.persistence.*;

@Entity
@Table(name = "usersData")
@SelectBeforeUpdate
public class UserDB {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column
    private String userName;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @OneToOne(targetEntity = MatrixDataDB.class, cascade = CascadeType.ALL)
    private MatrixDataDB matrixData;

    @Column
    private Long prev_id;

    public UserDB(){}

    public UserDB(Long id){
        this(id, "unnamed");
    }

    public UserDB(Long id, String userName){
        this.id = id;
        this.userName = userName;
        state = StateEnum.START;
        matrixData = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getPrev_id() {
        return prev_id;
    }

    public void setPrev_id(Long prev_id) {
        this.prev_id = prev_id;
    }
}
