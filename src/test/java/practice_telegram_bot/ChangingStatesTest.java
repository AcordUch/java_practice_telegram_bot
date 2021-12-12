package practice_telegram_bot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import practice_telegram_bot.database.User;
import practice_telegram_bot.enums.StateEnum;
import practice_telegram_bot.telegram.UsersData;
import practice_telegram_bot.telegram.commands.textCommands.ReturnToMenuCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.ChooseOperationsCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.MatrixInputCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.MatrixSizeInputCommand;
import practice_telegram_bot.telegram.commands.textCommands.matrixCommands.StartMatrixCommand;

import static org.junit.jupiter.api.Assertions.*;

public class ChangingStatesTest {
    private static final User plug = null;
    private static final Long firstId = 1L;
    private static final Long secondId = 2L;
    private UsersData usersData;

    @Before
    public void setUp(){
        usersData = UsersData.instance();
        usersData.setUsersState(firstId, StateEnum.START);
        usersData.setUsersState(secondId, StateEnum.START);
    }

    @After
    public void afterTest(){
        UsersData.clearInstance();
    }

    @Test
    public void switchingStatesBase(){
        new StartMatrixCommand().execute(firstId, "", null);
        assertEquals(usersData.getUserState(firstId), StateEnum.MATRIX_OPERATION_SELECT);
    }

    @Test
    public void switchingStates(){
        new StartMatrixCommand().execute(firstId, "", plug);
        new ChooseOperationsCommand().execute(firstId, "сложение", plug);
        assertEquals(usersData.getUserState(firstId), StateEnum.MATRIX_SIZE_INPUT);

        new MatrixSizeInputCommand().execute(firstId, "2 2", plug);
        assertEquals(usersData.getUserState(firstId), StateEnum.MATRIX_INPUT);

        new MatrixInputCommand().execute(firstId, "0 0", plug);
        assertEquals(usersData.getUserState(firstId), StateEnum.MATRIX_INPUT);
    }

    @Test
    public void switchingStatesReturn(){
        new StartMatrixCommand().execute(firstId, "", plug);
        new ChooseOperationsCommand().execute(firstId, "сложение", plug);
        new MatrixSizeInputCommand().execute(firstId, "2 2", plug);
        new MatrixInputCommand().execute(firstId, "0 0", plug);
        new ReturnToMenuCommand().execute(firstId, "", plug);

        assertEquals(usersData.getUserState(firstId), StateEnum.START);
    }

    @Test
    public void switchingStatesTwoUsers(){
        new StartMatrixCommand().execute(firstId, "", plug);
        new StartMatrixCommand().execute(secondId, "", plug);
        new ChooseOperationsCommand().execute(secondId, "вычитание", plug);
        new ChooseOperationsCommand().execute(firstId, "сложение", plug);
        new MatrixSizeInputCommand().execute(firstId, "2 2", plug);

        assertEquals(usersData.getUserState(firstId), StateEnum.MATRIX_INPUT);
        assertEquals(usersData.getUserState(secondId), StateEnum.MATRIX_SIZE_INPUT);
    }
}
