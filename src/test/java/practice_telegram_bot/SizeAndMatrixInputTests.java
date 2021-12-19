package practice_telegram_bot;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import practice_telegram_bot.database.UserDB;
import practice_telegram_bot.database.dao.DAO;
import practice_telegram_bot.database.dao.PostgreSqlSessionFactory;
import practice_telegram_bot.enums.CommandEnum;
import practice_telegram_bot.telegram.MatrixData;
import practice_telegram_bot.telegram.commands.Command;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.*;

import java.util.Map;

import static java.util.Map.entry;
import static practice_telegram_bot.enums.CommandEnum.MATRIX_SIZE;
import static practice_telegram_bot.enums.CommandEnum.MATRIX_ROW;
import static practice_telegram_bot.enums.CommandEnum.MATRIX_OPERATIONS;
import static org.junit.jupiter.api.Assertions.*;

public class SizeAndMatrixInputTests {
    private static final Map<CommandEnum, Command> COMMAND_MAP = Map.ofEntries(
            entry(CommandEnum.MATRIX_SIZE, new MatrixSizeInputCommand()),
            entry(CommandEnum.MATRIX_ROW, new MatrixInputCommand()),
            entry(CommandEnum.MATRIX_OPERATIONS, new ChooseOperationsCommand())
    );

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

        COMMAND_MAP.get(MATRIX_OPERATIONS).execute(firstId, "сложение", userData1);
        COMMAND_MAP.get(MATRIX_OPERATIONS).execute(secondId, "сложение", userData2);

        DAO.instance().save(userData1);
        DAO.instance().save(userData2);
    }

    @After
    public void afterTest(){
    }

    @Test
    public void input2_2Matrix(){
        var expected = "1.0 1.0\n0.0 0.0\n";
        COMMAND_MAP.get(MATRIX_SIZE).execute(firstId, "2 2", userData1);
        COMMAND_MAP.get(MATRIX_ROW).execute(firstId, "1.0 1.0", userData1);
        COMMAND_MAP.get(MATRIX_ROW).execute(firstId, "0.0 0.0", userData1);

        update(userData1);
        assertEquals(
                expected,
                buildMatrixAndGetInString(firstId, 0)
        );
    }

    @Test
    public void parallelInputTwo2_2Matrix(){
        var expected1 = "1.1 1.2\n0.1 0.2\n";
        var expected2 = "3.0 3.1 3.1\n1.0 1.1 1.1\n0.0 0.1 0.1\n";
        COMMAND_MAP.get(MATRIX_SIZE).execute(firstId, "2 2", userData1);
        COMMAND_MAP.get(MATRIX_ROW).execute(firstId, "1.1 1.2", userData1);
        COMMAND_MAP.get(MATRIX_SIZE).execute(secondId, "3 3", userData2);
        COMMAND_MAP.get(MATRIX_ROW).execute(secondId, "3.0 3.1 3.1", userData2);
        COMMAND_MAP.get(MATRIX_ROW).execute(firstId, "0.1 0.2", userData1);
        COMMAND_MAP.get(MATRIX_ROW).execute(secondId, "1.0 1.1 1.1", userData2);
        COMMAND_MAP.get(MATRIX_ROW).execute(secondId, "0.0 0.1 0.1", userData2);

        update(userData1);
        update(userData2);
        assertEquals(
                expected1,
                buildMatrixAndGetInString(firstId, 0)
        );
        assertEquals(
                expected2,
                buildMatrixAndGetInString(secondId, 0)
        );
    }

    @Test
    public void input4_4MatrixInOneLine(){
        var expected = "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n";
        COMMAND_MAP.get(MATRIX_SIZE).execute(firstId, "4 4", userData1);
        COMMAND_MAP.get(MATRIX_ROW).execute(
                firstId,
                "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n",
                userData1
        );

        update(userData1);
        assertEquals(
                expected,
                buildMatrixAndGetInString(firstId, 0)
        );
    }

    @Test
    public void input4_4MatrixInMultiInput(){
        var expected = "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n";
        COMMAND_MAP.get(MATRIX_SIZE).execute(firstId, "4 4", userData1);
        COMMAND_MAP.get(MATRIX_ROW).execute(
                firstId,
                "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n",
                userData1
        );
        COMMAND_MAP.get(MATRIX_ROW).execute(
                firstId, "0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n",
                userData1
        );

        update(userData1);
        assertEquals(
                expected,
                buildMatrixAndGetInString(firstId, 0)
        );
    }

    @Test
    public void input4_4MatrixInMultiInput2(){
        var expected = "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n";
        COMMAND_MAP.get(MATRIX_SIZE).execute(firstId, "4 4", userData1);
        COMMAND_MAP.get(MATRIX_ROW).execute(
                firstId,
                "2.0 2.1 2.2 2.3\n1.0 1.1 1.2 1.3\n0.0 0.1 0.2 0.3\n4.0 4.1 4.2 4.3\n",
                userData1
        );
        COMMAND_MAP.get(MATRIX_ROW).execute(firstId, "0.0 0.1 0.2 0.3\n", userData1);
        COMMAND_MAP.get(MATRIX_ROW).execute(firstId, "4.0 4.1 4.2 4.3\n", userData1);

        update(userData1);
        assertEquals(
                expected,
                buildMatrixAndGetInString(firstId, 0)
        );
    }

    private void update(UserDB userDB){
        DAO.instance().update(userDB);
    }

    private UserDB load(Long id){
        return DAO.instance().findById(UserDB.class, id);
    }

    private String buildMatrixAndGetInString(Long id, int index){
        var matrixData = MatrixData.restoreFromDB(load(id).getMatrixData());
        matrixData.buildMatrix();
        return matrixData.tryGetMatrix(index).get().toString();
    }

//    private void buildMatrix(){
//        usersData.getUserMatrixData(id).buildMatrix();
//    }
//
//    private String getMatrixInString(Long id, int index){
//        return usersData.getUserMatrixData(id).tryGetMatrix(index).get().toString();
//    }
}
