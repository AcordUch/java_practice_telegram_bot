package practice_telegram_bot;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.database.dao.DAO;
import practice_telegram_bot.database.dao.PostgreSqlSessionFactory;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.commands.textCommands.ReturnToMenuCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChangingStatesTest {
    private static final UserDB plug = null;
    private static final Long firstId = 1L;
    private static final Long secondId = 2L;
    private UserDB userData1;
    private UserDB userData2;

    @BeforeClass
    public static void setUpClass(){
        PostgreSqlSessionFactory.instance();
        DAO.configureForPostgreSql();
    }

    @Before
    public void setUp(){
        userData1 = new UserDB(firstId, "first user from test");
        userData2 = new UserDB(secondId, "second user from test");

        DAO.instance().save(userData1);
        DAO.instance().save(userData2);
    }

    @After
    public void afterTest(){
    }

    @Test
    public void switchingStatesBase(){
        new StartMatrixCommand().execute(firstId, "", userData1);
        assertEquals(userData1.getState(), StateEnum.MATRIX_OPERATION_SELECT);
    }

    @Test
    public void switchingStates(){
        new StartMatrixCommand().execute(firstId, "", userData1);
        new ChooseOperationsCommand().execute(firstId, "сложение", userData1);
        assertEquals(userData1.getState(), StateEnum.MATRIX_NUMBER_INPUT);

        new MatrixNumberInputCommand().execute(firstId, "2", userData1);
        assertEquals(userData1.getState(), StateEnum.MATRIX_SIZE_INPUT);

        new MatrixSizeInputCommand().execute(firstId, "2 2", userData1);
        assertEquals(userData1.getState(), StateEnum.MATRIX_INPUT);

        new MatrixInputCommand().execute(firstId, "0 0", userData1);
        assertEquals(userData1.getState(), StateEnum.MATRIX_INPUT);
    }

    @Test
    public void switchingStatesWithDB(){
        new StartMatrixCommand().execute(firstId, "", userData1);
        update(userData1);
        new ChooseOperationsCommand().execute(firstId, "сложение", userData1);
        update(userData1);
        new MatrixSizeInputCommand().execute(firstId, "2 2", userData1);
        update(userData1);
        new MatrixInputCommand().execute(firstId, "0 0", userData1);
        update(userData1);

        assertEquals(load(firstId).getState(), StateEnum.MATRIX_INPUT);
    }

    @Test
    public void switchingStatesReturn(){
        new StartMatrixCommand().execute(firstId, "", userData1);
        new ChooseOperationsCommand().execute(firstId, "сложение", userData1);
        new MatrixSizeInputCommand().execute(firstId, "2 2", userData1);
        new MatrixInputCommand().execute(firstId, "0 0", userData1);
        new ReturnToMenuCommand().execute(firstId, "", userData1);

        assertEquals(userData1.getState(), StateEnum.START);
    }

    @Test
    public void switchingStatesTwoUsersWithDB(){
        new StartMatrixCommand().execute(firstId, "", userData1);
        update(userData1);
        new StartMatrixCommand().execute(secondId, "", userData2);
        update(userData2);
        new ChooseOperationsCommand().execute(secondId, "вычитание", userData2);
        update(userData2);
        new ChooseOperationsCommand().execute(firstId, "сложение", userData1);
        update(userData1);
        new MatrixNumberInputCommand().execute(firstId, "2", userData1);
        update(userData1);
        new MatrixSizeInputCommand().execute(firstId, "2 2", userData1);
        update(userData1);

        assertEquals(load(firstId).getState(), StateEnum.MATRIX_INPUT);
        assertEquals(load(secondId).getState(), StateEnum.MATRIX_NUMBER_INPUT);
    }

    private void update(UserDB userDB){
        DAO.instance().update(userDB);
    }

    private UserDB load(Long id){
        return DAO.instance().findById(UserDB.class, id);
    }
}
